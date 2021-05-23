package utils;


import java.util.Scanner;

public class TextWorker {
    Scanner scanner;
    FileWorker worker;

    public TextWorker() {
        scanner=new Scanner(System.in);
        worker=new FileWorker();
    }
    public String getStringInput(){
        try {
            return scanner.next();

        }catch (Exception e){
            worker.logger("wrong string input"+e);
            return null;
        }
    }
    public Integer getIntInput(){
        try {
            return scanner.nextInt();
        }catch (Exception e){
            worker.logger("wrong int input"+e);
            return null;
        }
    }
    public void println(String str){
        System.out.println(str);
    }
}
