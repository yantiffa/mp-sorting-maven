package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using Quicksort. This class is implemented for CSC207 fall24
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Tiffany Yan
 */
public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)
  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
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
    quickSort(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * The quickSort method is implemented by calling the DNF method.
   * @param arr
   * @param low
   * @param high
   */
  public void quickSort(T[] arr, int low, int high) {
    if (high <= low) {
      return;
    } //if
    int[] copyofcopy = dnf(arr, low, high);
    int lower = copyofcopy[0];
    int higher = copyofcopy[1];

    quickSort(arr, low, lower - 1);
    quickSort(arr, higher + 1, high);
  } //quickSort(T[], int, int)

  /**
   * The base algorithm is designed based on the DNF algorithm covered during class.
   * @param arr
   * @param lower
   * @param upper
   * @return the position of the white and blue
   */
  public int[] dnf(T[] arr, int lower, int upper) {
    T pivot = arr[upper];
    int white = lower;
    int red = lower;
    int blue = upper;
    while (red <= blue) {
      if (order.compare(arr[red], pivot) < 0) {
        swap(arr, white, red);
        white++;
        red++;
      } else if (order.compare(arr[red], pivot) == 0) {
        red++;
      } else {
        swap(arr, blue, red);
        blue--;
      } // if
    } // while
    int[] info = {white, blue};
    return info;
  } // DNF(T[], int, int)

  /**
   * Swaps around the positions of two elements.
   * @param arr
   * @param i
   * @param j
   */
  public void swap(T[] arr, int i, int j) {
    T copy = arr[i];
    arr[i] = arr[j];
    arr[j] = copy;
  } // swap(T[], int, int)
} // class Quicksorter
