package com.danielcaita.cache;

public class CacheEntry<V> {
        private final V value;
        private long lastAccessTime;

        public CacheEntry(V value) {
            this.value = value;
            this.lastAccessTime = System.currentTimeMillis();
        }

        public V getValue() {
            return value;
        }

        public long getLastAccessTime() {
            return lastAccessTime;
        }

        public void setLastAccessTime(long time) {
            this.lastAccessTime = time;
        }
    }