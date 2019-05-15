package concurrent.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier用法演示
 *
 * @author dager
 * @date 2019/5/15
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) {
        CyclicBarrier cyclicbarrier = new CyclicBarrier(7,() -> System.out.println("召唤神龙！"));

        for (int i = 1; i < 8; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "\t 收集到第" + index + "颗龙珠");
                    cyclicbarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
