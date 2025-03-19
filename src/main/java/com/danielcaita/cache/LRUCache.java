package com.danielcaita.cache;

import com.google.common.cache.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class LRUCache<K, V> {
    private static final Logger LOGGER = Logger.getLogger(LRUCache.class.getName());
    private final Cache<K, V> cache;

    public LRUCache(int maxSize, long expirationTime) {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterAccess(expirationTime, TimeUnit.MILLISECONDS)
                .removalListener((RemovalListener<K, V>) notification ->
                        LOGGER.info("Evicted entry: " + notification.getKey()))
                .build();
    }

    public V get(K key) {
        return cache.getIfPresent(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public long size() {
        return cache.size();
    }
}