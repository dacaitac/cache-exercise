package com.danielcaita.algorithms;

public class BinarySearch {

    // Iterative binary search
    public static int binarySearchIterative(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Recursive binary search
    public static int binarySearchRecursive(int[] array, int target) {
        return binarySearchRecursiveHelper(array, target, 0, array.length - 1);
    }

    private static int binarySearchRecursiveHelper(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return binarySearchRecursiveHelper(array, target, mid + 1, right);
        } else {
            return binarySearchRecursiveHelper(array, target, left, mid - 1);
        }
    }
}