package concurrent.collections.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态
 *
 *
 * SynchronousQueue演示
 * @author dager
 * @date 2019/5/15
 */
public class SynchronousQueueDemo {



    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println("加入 1");
                blockingQueue.put("1");


                System.out.println("加入 2");
                blockingQueue.put("2");

                System.out.println("加入 3");
                blockingQueue.put("3");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"AA").start();

        new Thread(() -> {

            try {
                try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                blockingQueue.take();
                System.out.println("1 已取出");

                try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                blockingQueue.take();
                System.out.println("2 已取出");

                try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                blockingQueue.take();
                System.out.println("3 已取出");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BB").start();

    }

}
