package utility;



import java.util.Scanner;

public class TextWorker {
    Scanner scanner;


    public TextWorker() {
        scanner=new Scanner(System.in);

    }
    public String getStringInput(){
        try {
            return scanner.next();

        }catch (Exception e){
            println("wrong string input"+e);
            return null;
        }
    }
    public Integer getIntInput(){
        try {
            return scanner.nextInt();
        }catch (Exception e){
            println("wrong int input"+e);
            return null;
        }
    }
    public Double getDoubleInput(){
        try {
            return scanner.nextDouble();
        }catch (Exception e){
            println("wrong int input"+e);
            return null;
        }
    }
    public void println(String str){
        System.out.println(str);
    }
    public String getStringLine(){
        try {
            return scanner.nextLine();

        }catch (Exception e){
            println("wrong string input"+e);
            return null;
        }
    }

}