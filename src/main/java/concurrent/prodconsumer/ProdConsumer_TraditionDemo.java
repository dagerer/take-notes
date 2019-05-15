package concurrent.prodconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统办生产者消费者模式
 *
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 * <p>
 * <p>
 * 1. 线程    操作         资源类
 * 2. 判断    生产/消费    通知
 * 3. 防止虚假唤醒机制
 *
 * @author dager
 * @date 2019/5/15
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AB").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BD").start();

    }
}

/**
 * 资源类
 */
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            /*此处如果用if判断 多个线程（两个以上）下会导致出现虚假唤醒
            if (number != 0) {
                // 2.等待，停止生产
                condition.await();
            }
            */

            // 1. 判断
            while (number != 0) {
                // 2.等待，停止生产
                condition.await();
            }

            // 3.开始生产
            number++;
            System.out.println(Thread.currentThread().getName() + "\t " + number);

            // 4.通知唤醒
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            // 1. 判断
            while (number == 0) {
                // 2.等待，停止消费
                condition.await();
            }

            // 3.开始消费
            number--;
            System.out.println(Thread.currentThread().getName() + "\t " + number);

            // 4.通知唤醒
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}