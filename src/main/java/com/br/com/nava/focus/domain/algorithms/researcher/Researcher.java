package com.br.com.nava.focus.domain.algorithms.researcher;

import com.br.com.nava.focus.domain.algorithms.sorter.Sorter;
import com.br.com.nava.focus.domain.algorithms.sorter.SortsStrategy;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

public class Researcher<T, K> implements SearchStrategy<T, K> {

    @Override
    public Optional<T> binarySearch(T[] array, K target, Function<T, K> keyExtract, Comparator<K> keyComparator) {
        SortsStrategy<T, K> sorter = new Sorter<>();
        System.out.println("BINARY Key Extract: " + keyExtract.toString() + ", KeyComparator: " + keyComparator.toString());

        T[] arraySorted = sorter.insertionSort(array, keyExtract, keyComparator);

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {

            int middle = (left + right) / 2;

            K keyMiddle = keyExtract.apply(arraySorted[middle]);
            System.out.println("KeyMiddle: " + keyMiddle);
            int result = keyComparator.compare(target, keyMiddle);
            System.out.println("Result: " + result);

            if (result == 0) {

                return Optional.of(array[middle]);
            }

            if (result > 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return Optional.empty();

    }
}
