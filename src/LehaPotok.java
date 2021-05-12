@Reflectable(name = "123",value = "123")
public class LehaPotok extends Thread {

    public  void run(){
        while (true){

        System.out.println(currentThread().getName()+" kaka");



        }
    }

}
