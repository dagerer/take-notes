package concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @author dager
 * @date 2019/5/14
 */
public class SpinLockDemo {

    /**
     * 原子引用类型 初始化为null
     */
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " locking");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
        System.out.println(Thread.currentThread().getName() + " lock success!!");
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " unlocking");
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + " unlock success!!");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.lock();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "t2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {

            spinLockDemo.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "t1").start();


    }

}
