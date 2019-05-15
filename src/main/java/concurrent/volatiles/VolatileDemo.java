package concurrent.volatiles;

/**
 * 可见性验证
 *
 * @author dager
 * @date 2019/5/9
 */
public class VolatileDemo {

    //public volatile int visiable = 0;

    public int visiable = 0;

    public void add(){
        this.visiable = 1;
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();


        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            volatileDemo.add();

        }).start();

        while(volatileDemo.visiable == 0){

        }

        System.out.println("volatile 可见性验证");
    }


}
