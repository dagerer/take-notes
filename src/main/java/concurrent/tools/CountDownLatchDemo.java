package concurrent.tools;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch用法演示
 *
 * @author dager
 * @date 2019/5/15
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(7);

        for (int i = 7; i > 0; i--) {
            countDownLatch.countDown();
            System.out.println("倒计时: " + i);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("火箭升空！");
    }
}
