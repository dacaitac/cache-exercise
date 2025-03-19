package com.danielcaita.tests;

import com.danielcaita.algorithms.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class AlgorithmTests {

    private static final int LARGE_ARRAY_SIZE = 100_000;
    private static final int BENCHMARK_ITERATIONS = 10;
    private int[] sortedArray;
    private int[] randomArray;
    
    @BeforeEach
    void setUp() {
        sortedArray = new int[LARGE_ARRAY_SIZE];
        randomArray = new int[LARGE_ARRAY_SIZE];
        Random random = new Random();
        
        for (int i = 0; i < LARGE_ARRAY_SIZE; i++) {
            sortedArray[i] = i;
            randomArray[i] = random.nextInt(LARGE_ARRAY_SIZE);
        }
    }

    @Test
    void testBinarySearch() {
        assertEquals(500, BinarySearch.binarySearchIterative(sortedArray, 500));
        assertEquals(-1, BinarySearch.binarySearchIterative(sortedArray, -1));
        assertEquals(500, BinarySearch.binarySearchRecursive(sortedArray, 500));
        assertEquals(-1, BinarySearch.binarySearchRecursive(sortedArray, -1));
    }

    @Test
    void testMergeSort() {
        int[] array = randomArray.clone();
        MergeSort.mergeSort(array);
        assertTrue(isSorted(array));
    }

    @Test
    void testInsertionSort() {
        int[] array = randomArray.clone();
        InsertionSort.insertionSort(array);
        assertTrue(isSorted(array));
    }
    
    @Test
    void benchmarkSorting() {
        long mergeSortTime = benchmark(() -> MergeSort.mergeSort(randomArray.clone()));
        long insertionSortTime = benchmark(() -> InsertionSort.insertionSort(randomArray.clone()));
        
        System.out.println("MergeSort time: " + mergeSortTime + "ms");
        System.out.println("InsertionSort time: " + insertionSortTime + "ms");
    }
    
    private long benchmark(Runnable algorithm) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < BENCHMARK_ITERATIONS; i++) {
            algorithm.run();
        }
        return (System.currentTimeMillis() - start) / BENCHMARK_ITERATIONS;
    }

    private boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
