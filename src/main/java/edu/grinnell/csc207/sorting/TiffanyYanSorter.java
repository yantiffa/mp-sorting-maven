package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * A sorting algorithm that attempts to do better than all of the prior O(nlogn) sorting algorithms.
 * This class is implemented for CSC207 fall24.
 *
 * @param <T> The types of values that are sorted.
 * @author Tiffany
 *
 */
public class TiffanyYanSorter<T> implements Sorter<T> {
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
  public TiffanyYanSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // TiffanyYanSorter(Comparator)
  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Either insertion sort or quicksort based on the array size.
   *
   * @param arr array being sorted
   */
  public void sort(T[] arr) {
    if (arr == null || arr.length <= 1) {
      return;
    } //if

    int gap = arr.length;
    boolean sorted = false;

    while (!sorted) {
      gap = Math.max(gap / 2, 1);
      sorted = true;

      for (int i = 0; i + gap < arr.length; i++) {
        if (order.compare(arr[i], arr[i + gap]) > 0) {
          T copy = arr[i];
          arr[i] = arr[i + gap];
          arr[i + gap] = copy;
          sorted = false;
        } // if
      } // for

      if (gap == 1 && sorted) {
        break;
      } // if
    } // while
  } // sort(T[])
} //TiffanyYanSorter()
