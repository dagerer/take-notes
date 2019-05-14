package algorithm;

import algorithm.base.AbstractSort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author dager
 * @date 2019/4/21
 */
public class InsertionSort extends AbstractSort {


    @Override
    protected int[] sort() {
        int[] in = super.getRandomElements(10);

        for (int i = 1; i < in.length; i++) {
            int temp = in[i];
            int j = i - 1;
            // 插入
            for (; j >= 0; j--) {
                if (temp < in[j]) {
                    in[j + 1] = in[j];
                } else {
                    break;
                }
            }
            in[j + 1] = temp;
        }

        return in;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new InsertionSort().sort()));
    }
}
