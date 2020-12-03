package ru.akaleganov.cyberforum.algoritm;

/**
 * Class QuickSort
 * быстрая сортировка
 * @author Kaleganov Alexander
 * @since 03 дек. 20
 */
public class QuickSort {


    public void quickSort(int[] mass, int low, int height) {
        if (mass.length == 0) {
            return;
        }
        if (low >= height) {
            return;
        }
        int opora = mass[low + (height - low)];
        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = height;
        while (i <= j) {
            while (mass[i] < opora) {
                i++;
            }
            while (mass[j] > opora) {
                j--;
            }
            if (i <= j) {//меняем местами
                int temp = mass[i];
                mass[i] = mass[j];
                mass[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j) {
            quickSort(mass, low, j);
        }
        if (height > i) {
            quickSort(mass, i, height);
        }
    }
}
