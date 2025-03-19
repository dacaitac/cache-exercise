package com.danielcaita.cache;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class LFUCache<K, V> {
    private static final Logger LOGGER = Logger.getLogger(LFUCache.class.getName());
    private final int maxSize;
    private final long expirationTime;
    private final Map<K, CacheEntry<V>> cache;
    private final Map<K, Integer> frequencies;
    private final ReentrantLock lock = new ReentrantLock();

    public LFUCache(int maxSize, long expirationTime) {
        this.maxSize = maxSize;
        this.expirationTime = expirationTime;
        this.cache = new ConcurrentHashMap<>(maxSize);
        this.frequencies = new ConcurrentHashMap<>();
    }

    public V get(K key) {
        lock.lock();
        try {
            CacheEntry<V> entry = cache.get(key);
            if (entry == null || isExpired(entry)) {
                cache.remove(key);
                frequencies.remove(key);
                return null;
            }
            frequencies.put(key, frequencies.getOrDefault(key, 0) + 1);
            entry.setLastAccessTime(System.currentTimeMillis());
            return entry.getValue();
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            if (cache.size() >= maxSize) {
                evictLFUEntry();
            }
            cache.put(key, new CacheEntry<>(value));
            frequencies.put(key, 1);
        } finally {
            lock.unlock();
        }
    }

    private void evictLFUEntry() {
        K lfuKey = Collections.min(frequencies.entrySet(), Map.Entry.comparingByValue()).getKey();
        cache.remove(lfuKey);
        frequencies.remove(lfuKey);
        LOGGER.info("Evicted entry: " + lfuKey);
    }

    private boolean isExpired(CacheEntry<V> entry) {
        return (System.currentTimeMillis() - entry.getLastAccessTime()) > expirationTime;
    }
}