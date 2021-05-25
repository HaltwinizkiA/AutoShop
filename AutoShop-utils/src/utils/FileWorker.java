package utils;

import model.entity.master.Master;
import model.entity.work.Work;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileWorker {


    public Properties getProperties(String path) {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            logger("properties err " + e);
            return null;
        }
    }

    public void writer(Object object, String path) {
        try (FileOutputStream outputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(object);
            objectOutputStream.close();


        } catch (Exception e) {
            logger(e.toString());


        }

    }

    public void csvWorkWriter(String path, List<Work> workList) {

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            String collum = " ID ;NAME ;PRICE ";
            bw.write(collum);
            bw.newLine();
            workList.forEach(work -> {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(work.getId());
                oneLine.append(";" + work.getName());
                oneLine.append(";" + work.getPrice());

                try {
                    bw.write(oneLine.toString());
                    bw.newLine();

                } catch (Exception e) {
                    System.out.println("_" + e);
                }
            });
        } catch (Exception e) {
            logger("ERROR csvWorkWriter "+e);

        }

    }
    public void csvMasterWriter(String path, List<Master> masterList){
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            String collum = " ID ;NAME ;PRICE ";

            bw.write(collum);
            bw.newLine();
            masterList.forEach(master -> {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(master.getId());
                oneLine.append(";" + master.getName());
                oneLine.append(";" + master.ge);


                try {
                    bw.write(oneLine.toString());
                    bw.newLine();

                } catch (Exception e) {
                    System.out.println("_" + e);
                }
            });
        } catch (Exception e) {
            logger("ERROR csvWorkWriter "+e);

        }
    }

    public List<Work> csvWorkReader(String path) {
        List<Work> workList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\37533\\Projects\\AutoShop\\dataBase\\csv\\work.csv"))) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] redline = line.split(";");
                workList.add(new Work(redline[1], Double.parseDouble(redline[2]), Integer.parseInt(redline[0])));
                line = br.readLine();
            }
            return workList;
        } catch (Exception e) {
            logger("Error csvWorkReader :"+e);
            return null;
        }
    }


    public <T> T lehaReader(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Object object = objectInputStream.readObject();

            return (T) object;
        } catch (Exception e) {

            logger(e.toString());
            return null;
        }


    }


    public Object reader(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Object object = objectInputStream.readObject();
            return object;
        } catch (Exception e) {

            logger(e.toString());
            return null;
        }
    }


    public boolean logger(String string) {
        File file = new File("C:\\Users\\37533\\Projects\\AutoShop\\logs.txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(string + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }


}
