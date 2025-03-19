package com.danielcaita.tests;

import com.danielcaita.cache.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.*;

public class CacheTests {

    private LFUCache<String, String> lfuCache;
    private LRUCache<String, String> lruCache;

    @BeforeEach
    void setUp() {
        lfuCache = new LFUCache<>(3, 5000); // Small cache for testing
        lruCache = new LRUCache<>(3, 5000);
    }

    @Test
    void testLFUCacheInsertionAndRetrieval() {
        lfuCache.put("A", "Alpha");
        lfuCache.put("B", "Beta");
        lfuCache.put("C", "Gamma");
        assertEquals("Alpha", lfuCache.get("A"));
        assertEquals("Beta", lfuCache.get("B"));
        assertEquals("Gamma", lfuCache.get("C"));
    }

    @Test
    void testLFUCacheEviction() {
        lfuCache.put("A", "Alpha");
        lfuCache.put("B", "Beta");
        lfuCache.put("C", "Gamma");
        lfuCache.get("A"); // Increase frequency of A
        lfuCache.put("D", "Delta"); // Should evict the least used (B or C)
        assertNull(lfuCache.get("B")); // B or C should be removed
    }

    @Test
    void testLRUCacheInsertionAndRetrieval() {
        lruCache.put("A", "Alpha");
        lruCache.put("B", "Beta");
        lruCache.put("C", "Gamma");
        assertEquals("Alpha", lruCache.get("A"));
        assertEquals("Beta", lruCache.get("B"));
        assertEquals("Gamma", lruCache.get("C"));
    }

    @Test
    void testLRUCacheEviction() {
        lruCache.put("A", "Alpha");
        lruCache.put("B", "Beta");
        lruCache.put("C", "Gamma");
        lruCache.get("A"); // A is now most recently used
        lruCache.put("D", "Delta"); // Should evict the least recently used (B or C)
        assertNull(lruCache.get("B")); // B or C should be removed
    }

    @Test
    void testCacheExpiration() throws InterruptedException {
        lfuCache.put("X", "ExpireMe");
        Thread.sleep(6000); // Wait for expiration time
        assertNull(lfuCache.get("X")); // Should be expired
    }
}