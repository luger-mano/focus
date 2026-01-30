package com.br.com.nava.focus.domain.algorithms;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

public interface SearchStrategy<T, K> {
    Optional<T> binarySearch(T[] array, K target, Function<T, K> keyExtract, Comparator<K> keyComparator);
}
