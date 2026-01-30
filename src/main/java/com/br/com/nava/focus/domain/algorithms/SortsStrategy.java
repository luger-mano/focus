package com.br.com.nava.focus.domain.algorithms;

import java.util.Comparator;
import java.util.function.Function;

public interface SortsStrategy<T, k> {

   <K> T[] insertionSort(T[] stores, Function<T, K> keyExtract, Comparator<K> keyComparator);
}
