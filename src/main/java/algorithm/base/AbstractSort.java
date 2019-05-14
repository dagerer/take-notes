package algorithm.base;

import java.util.Random;

/**
 * @author dager
 * @date 2019/4/21
 */
public abstract class AbstractSort {


    protected int[] getRandomElements(int number) {
        int[] elements;
        Random random = new Random();
        if (number > 0) {
            elements = new int[number];
        } else {
            elements = new int[10];
        }
        for (int i = 0 ; i < number; i++){
            elements[i] = random.nextInt(100);
        }
        return elements;
    }

    protected abstract int[] sort();

}
