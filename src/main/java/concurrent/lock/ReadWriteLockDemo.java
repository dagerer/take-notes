package concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源时，就不应该在有其他线程可以对该资源进行读或写
 *
 *  写锁：原子+独占
 * @author dager
 * @date 2019/5/14
 */
public class ReadWriteLockDemo {

    /**
     * 多个线程操作时 需要使用volatile通知其他线程更新值
     */
    public volatile Map<Integer, String> map = new HashMap<>();

    /**
     * 创建读写锁
     */
    public ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(Integer key, String value) {
        System.out.println(Thread.currentThread().getName() + "\t 正在写入 key=" + key + " value=" + value);
        // 写锁
        readWriteLock.writeLock().lock();
        try{
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(Integer key) {
        System.out.println(Thread.currentThread().getName() + "\t 正在读取 key=" + key);
        // 读锁
        readWriteLock.readLock().lock();
        try{
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成 value=" + result);
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }


    public static void main(String[] args) {
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        for (int i = 1; i < 7; i++) {
            Integer key = i;
            new Thread(() -> {
                readWriteLockDemo.put(key,key+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i < 7; i++) {
            Integer key = i;
            new Thread(() -> {
                readWriteLockDemo.get(key);
            },String.valueOf(i)).start();
        }

    }

}
