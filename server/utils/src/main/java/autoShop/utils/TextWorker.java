package autoShop.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TextWorker {
    private final Scanner scanner;
    private final FileWorker worker;

    public TextWorker() {
        scanner = new Scanner(System.in);
        worker = new FileWorker();
    }

    public String getStringInput() {
        try {
            return scanner.next();

        } catch (Exception e) {
            worker.logger("wrong string input" + e);
            return null;
        }
    }

    public Integer getIntInput() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            worker.logger("wrong int input" + e);
            return null;
        }
    }

    public void println(String str) {
        System.out.println(str);
    }

    public Date dateParser(String line) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date date = null;
        try {
            date = dateFormat.parse(line);

        } catch (ParseException e) {
            worker.logger("date parser text worker error" + e);
        }

        return date;

    }

    public String dateParser(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String line = simpleDateFormat.format(date);

        return line;
    }
}
