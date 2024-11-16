package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort. This class is implemented for CSC207 fall24
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Tiffany Yan
 * @author Samuel A. Rebelsky
 */
public class MergeSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)
  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using pair-based sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if (values.length <= 1) {
      return;
    } // if

    int mid = values.length / 2;
    T[] left = (T[]) new Object[mid];
    T[] right = (T[]) new Object[values.length - mid];

    for (int i = 0; i < mid; i++) {
      left[i] = values[i];
    } // for

    int inde = 0;
    for (int i = mid; i < values.length; i++) {
      right[inde++] = values[i];
    } // for
    sort(left);
    sort(right);
    merge(values, left, right);
  } // sort(T[])

  /**
   * Uses the merge approach that combines elements/arrays by comparing.
   * @param arr
   * @param left
   * @param right
   */

  public void merge(T[] arr, T[] left, T[] right) {
    int arrindex = 0;
    int leftindex = 0;
    int rightindex = 0;

    while (leftindex < left.length && rightindex < right.length) {
      if (order.compare(left[leftindex], right[rightindex]) <= 0) {
        arr[arrindex++] = left[leftindex++];
      } else {
        arr[arrindex++] = right[rightindex++];
      } // if
    } // while
    if (arrindex < arr.length) {
      if (leftindex < left.length) {
        while (arrindex < arr.length) {
          arr[arrindex++] = left[leftindex++];
        } // while
      } else {
        while (arrindex < arr.length) {
          arr[arrindex++] = right[rightindex++];
        } // while
      } // if
    } // if
  } // merge (T[], T[], T[])
} // sorter
