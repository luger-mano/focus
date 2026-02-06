package com.br.com.nava.focus.domain.algorithms.sorter;

import java.util.Comparator;
import java.util.function.Function;

public class Sorter<T, k> implements SortsStrategy<T, k> {

    @Override
    public <K> T[] insertionSort(T[] array, Function<T, K> keyExtract, Comparator<K> keyComparator) {

        for (int i = 1; i < array.length; i++) {

            T keyElement = array[i];
            K keyValue = keyExtract.apply(keyElement);

            int j = i - 1;

            while (j >= 0) {
                K currentKey = keyExtract.apply(array[j]);

                if (keyComparator.compare(currentKey, keyValue) > 0) {
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = keyElement;
        }
        return array;
    }
}
