package algorithm;

import algorithm.base.AbstractSort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author dager
 * @date 2019/4/21
 */
public class BubbleSort extends AbstractSort {


    @Override
    protected int[] sort() {
        int[] in = super.getRandomElements(10);
        for (int i = 0; i < in.length - 1; i++) {
            for (int j = 0; j < in.length - 1 - i; j++) {
                if (in[j] > in[j + 1]) {
                    int temp = in[j];
                    in[j] = in[j + 1];
                    in[j + 1] = temp;
                }
            }
        }
        return in;
    }

    public static void main(String[] args) {
        BubbleSort b = new BubbleSort();
        int[] sort = b.sort();
        System.out.println(Arrays.toString(sort));
    }
}
