# Cache & Algorithm Implementations

## Overview
This project provides implementations for:
- **Cache Service**
    - Java-based **LFU cache** with expiration (5s) and concurrency support.
    - **Guava-based LRU cache** with expiration (5s).
- **Algorithms**
    - **Binary Search** (Iterative & Recursive)
    - **Merge Sort** (O(N log N))
    - **Insertion Sort** (O(N²) worst-case, O(1) space complexity)
    - **Binary Tree Traversal** (In-order, Pre-order, Post-order, Level-order)
- **Unit Tests & Benchmarks**
    - Performance testing for sorting algorithms.
    - Correctness tests for search and sorting algorithms.

## Setup
### Prerequisites
- Java 17+
- Maven 3+

### Build & Run Tests
```sh
mvn clean install
mvn test
```

### Cache Usage Example
#### LFU Cache (Java-based)
```java
LFUCache<String, String> cache = new LFUCache<>(100_000, 5000);
cache.put("key1", "value1");
System.out.println(cache.get("key1")); // Should print "value1"
```

#### LRU Cache (Guava-based)
```java
LRUCache<String, String> cache = new LRUCache<>(100_000, 5000);
cache.put("key1", "value1");
System.out.println(cache.get("key1")); // Should print "value1"
```

### Benchmark Sorting Performance
```sh
mvn test -Dtest=AlgorithmTests#benchmarkSorting
```

## Project Structure
```
com.danielcaita
│── cache
│   ├── LFUCache.java
│   ├── LRUCache.java
│── algorithms
│   ├── BinarySearch.java
│   ├── MergeSort.java
│   ├── InsertionSort.java
│── datastructures
│   ├── BinaryTreeTraversal.java
│── tests
│   ├── AlgorithmTests.java
```

## Notes
- **Binary Search**: O(log N) complexity.
- **Merge Sort**: Efficient for large datasets.
- **Insertion Sort**: Best for small datasets or nearly sorted arrays.
- **Binary Tree Traversal**: Demonstrates basic tree operations.

## License
MIT
