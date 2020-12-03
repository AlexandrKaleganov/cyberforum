package ru.akaleganov.cyberforum.algoritm;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Log4j2
class QuickSortTest {
    @Test
    public void quickSortTest() {
        int[] k = new int[]{5, 6, 18, 25, 46, 5, 2, 8, 12, 4, 8, 5, 2, 4};
        new QuickSort().quickSort(k, 0, k.length - 1);
        for (int j : k) {
            log.info(j);
        }
    }
}
