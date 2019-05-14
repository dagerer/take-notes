package algorithm;

import algorithm.base.AbstractSort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author dager
 * @date 2019/4/21
 */
public class QuickSorting extends AbstractSort {

    @Override
    public int[] sort() {
        int[] randomElements = super.getRandomElements(50);
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(randomElements, randomElements.length);
        return quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        int index = left + 1;


        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[left]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, left, index - 1);
        return index - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        QuickSorting q = new QuickSorting();
        System.out.println(Arrays.toString(q.sort()));
    }
}
