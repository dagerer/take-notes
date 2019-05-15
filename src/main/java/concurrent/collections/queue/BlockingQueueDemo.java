package concurrent.collections.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue: 是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出）原则对元素进行排序
 * LinkedBlockingQueue： 一个基于链表结构的阻塞队列，此队列按FIFA（先进先出）排序元素，吞吐量通常要高于ArrayBlockingQueue
 *
 *
 *
 * 1 队列
 *
 *
 * 2 阻塞队列
 *     当阻塞队列是空时，从队列获取元素的操作将会被阻塞
 *     当阻塞队列是满时，往队列中添加元素的操作将会被阻塞
 *
 *   1）阻塞队列有没有好的一面
 *
 *   2）不得不阻塞，该如何管理
 *
 *
 *
 * @author dager
 * @date 2019/5/15
 */
public class BlockingQueueDemo {


    public static void main(String[] args) throws InterruptedException {
        /*
         * 基本api
         */
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // Exception in thread "main" java.lang.IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("d"));

        // get first element
        System.out.println(blockingQueue.element());
        // remove element
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // offer
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // if queue full return false
        System.out.println(blockingQueue.offer("x"));
        // peek
        System.out.println(blockingQueue.peek());
        // poll
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        // return null
        System.out.println(blockingQueue.poll());


        /*
         * 阻塞api
         */
        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(3);
        blockingQueue2.put("a");
        blockingQueue2.put("a");
        blockingQueue2.put("a");
        //blockingQueue2.put("a");
        System.out.println("put success");

        blockingQueue2.take();
        blockingQueue2.take();
        blockingQueue2.take();
        //blockingQueue2.take();
        System.out.println("take success");

        // 队列未满时不阻塞；如果队列已满 则阻塞2s后返回false（等待两秒后未插入成功则不插入）
        System.out.println(blockingQueue2.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue2.offer("b",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue2.offer("c",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue2.offer("d",2L, TimeUnit.SECONDS));
        System.out.println("offer success");

    }
}
