package concurrent;

/**
 * DCL单例模式
 *
 * @author dager
 * @date 2019/5/10
 */
public class SingletonDCLDemo {

    private static volatile SingletonDCLDemo singletonDCLDemo = null;
    private SingletonDCLDemo(){
        System.out.println("SingletonDCLDemo");
    }

    public static SingletonDCLDemo getInstance() {
        if(singletonDCLDemo == null){
            synchronized (SingletonDCLDemo.class){
                if(singletonDCLDemo == null){
                    singletonDCLDemo = new SingletonDCLDemo();
                }
            }
        }
        return singletonDCLDemo;
    }

    public static void main(String[] args) {
        System.out.println(SingletonDCLDemo.getInstance() == SingletonDCLDemo.getInstance());
        System.out.println(SingletonDCLDemo.getInstance() == SingletonDCLDemo.getInstance());
        System.out.println(SingletonDCLDemo.getInstance() == SingletonDCLDemo.getInstance());
    }
}
