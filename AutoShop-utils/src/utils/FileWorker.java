package utils;


import configuration.AutoShopConfiguration;
import model.entity.car.Car;
import model.entity.master.Master;
import model.entity.order.Order;
import model.entity.work.Work;
import model.enums.Specialty;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public void csvWorkWriter(String path, List<Work> ImportWorkList) {
        List<Work> newWorks = List.copyOf(ImportWorkList);
        List<Work> workList = csvWorkReader(path);

        for (int i = 0; i < workList.size() & i < workList.size(); i++) {
            for (int j = 0; j < workList.size(); j++) {
                if (workList.get(i).getId() == newWorks.get(j).getId()) {
                    workList.set(i, newWorks.get(j));
                    newWorks.remove(j);
                }
            }
        }
        workList.addAll(newWorks);


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
            logger("ERROR csvWorkWriter " + e);

        }

    }

    public void csvMasterWriter(String path, List<Master> ImportMasterList) {
        List<Master> newWorks = List.copyOf(ImportMasterList);
        List<Master> masterList = csvMasterReader(path);
        for (int i = 0; i < masterList.size() & i < masterList.size(); i++) {
            for (int j = 0; j < masterList.size(); j++) {
                if (masterList.get(i).getId() == newWorks.get(j).getId()) {
                    masterList.set(i, newWorks.get(j));
                    newWorks.remove(j);
                }

            }
        }
        masterList.addAll(newWorks);

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            String collum = " ID ;NAME ;STATUS ;SPECIALTY ;PHONE NUMBER ;DATE OF BIRTH ";

            bw.write(collum);
            bw.newLine();
            masterList.forEach(master -> {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(master.getId());
                oneLine.append(";" + master.getName());
                oneLine.append(";" + master.getStatus());
                oneLine.append(";" + master.getSpecialty());
                oneLine.append(";" + master.getPhoneNumber());
                oneLine.append(";" + master.getDateOfBirth());

                try {
                    bw.write(oneLine.toString());
                    bw.newLine();

                } catch (Exception e) {
                    System.out.println("_" + e);
                }
            });
        } catch (Exception e) {
            logger("ERROR csvWorkWriter " + e);

        }
    }

    public void csvOrderWriter(String path, List<Order> ImportOrderList) {
        AutoShopConfiguration autoShopConfiguration = new AutoShopConfiguration();
        List<Order> newWorks = List.copyOf(ImportOrderList);
        List<Order> orderList = csvOrderReader(path, autoShopConfiguration.getWorkCSVPath(), autoShopConfiguration.getMasterCSVPath());
        for (int i = 0; i < orderList.size() & i < orderList.size(); i++) {
            for (int j = 0; j < orderList.size(); j++) {
                if (orderList.get(i).getId() == newWorks.get(j).getId()) {
                    orderList.set(i, newWorks.get(j));
                    newWorks.remove(j);
                }
            }
        }
        orderList.addAll(newWorks);

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            String collum = " ID ;OWNERS NAME ;STATUS ;CREATE DATE ;PLANNED START DATE ;COMPLETION DATE ;PRICE ;CAR ;WORK ;MASTER ID ";
            bw.write(collum);
            bw.newLine();
            orderList.forEach(order -> {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(order.getId());
                oneLine.append(";" + order.getOwnersName());
                oneLine.append(";" + order.getStatus());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
                oneLine.append(";" + simpleDateFormat.format(order.getCreateOrderDate()));
                oneLine.append(";" + simpleDateFormat.format(order.getPlannedStartDate()));
                if (order.getDateOfCompletion() != null) {
                    oneLine.append(";" + simpleDateFormat.format(order.getDateOfCompletion()));
                } else {
                    oneLine.append(";" + null);
                }
                oneLine.append(";" + order.getPrice());
                oneLine.append(";" + order.getCar().getMark() + "/" + order.getCar().getModel() + "/" + order.getCar().getColor() + "/" + order.getCar().getNumber() + "/" + order.getCar().getId());
                oneLine.append(";");
                order.getWork().forEach(work -> {
                    oneLine.append(work.getId() + "/");
                });
                if (order.getMaster() != null) {
                    oneLine.append(";" + order.getMaster().getId());
                } else {
                    oneLine.append(";" + null);
                }

                try {
                    bw.write(oneLine.toString());
                    bw.newLine();

                } catch (Exception e) {
                    logger("ERROR csv Order writer" + e);
                }
            });
        } catch (Exception e) {
            logger("ERROR csv  Order Writer " + e);

        }
    }

    public List<Work> csvWorkReader(String path) {
        List<Work> workList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\37533\\Projects\\AutoShop\\dataBase\\csv\\work.csv"))) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] readline = line.split(";");
                workList.add(new Work(readline[1], Double.parseDouble(readline[2]), Integer.parseInt(readline[0])));
                line = br.readLine();
            }
            return workList;
        } catch (Exception e) {
            logger("Error csvWorkReader :" + e);
            return null;
        }
    }

    public List<Master> csvMasterReader(String path) {
        List<Master> masterList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\37533\\Projects\\AutoShop\\dataBase\\csv\\work.csv"))) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] readline = line.split(";");
                masterList.add(new Master(readline[1], readline[6], readline[5], Specialty.valueOf(readline[3]), Integer.parseInt(readline[0])));
                line = br.readLine();
            }
            return masterList;
        } catch (Exception e) {
            logger("Error csvWorkReader :" + e);
            return null;
        }

    }

    public List<Order> csvOrderReader(String orderPath, String workPath, String masterPath) {
        List<Order> orderList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(orderPath))) {
            String line = br.readLine();
            line = br.readLine();

            while (line != null) {
                String[] readline = line.split(";");
                List<Work> workList = new ArrayList<>();
                String[] workId = readline[8].split("/");
                for (String id : workId) {
                    csvWorkReader(workPath).forEach(work -> {
                        if (work.getId() == Integer.parseInt(id)) {
                            workList.add(work);
                        }
                    });

                }
                String[] car = readline[7].split("/");
                DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                dateFormat.setLenient(false);
                Date plannedStartDate = null;
                Date createOrderDate = null;
                Date completedDate = null;
                try {
                    plannedStartDate = dateFormat.parse(readline[3]);
                    createOrderDate = dateFormat.parse(readline[4]);
                    if (!readline[5].equals("null")) {
                        completedDate = dateFormat.parse(readline[5]);
                    }

                } catch (ParseException e) {
                    logger(e.toString());
                }

                Order order = new Order(createOrderDate, plannedStartDate, new Car(car[0], car[1], car[2], car[3], Integer.parseInt(car[4])), readline[1], workList, Double.parseDouble(readline[6]), Integer.parseInt(readline[0]));
                if (!readline[9].equals("null")) {
                    List<Master> masterList = csvMasterReader(masterPath);
                    masterList.forEach(master -> {
                        if (master.getId() == Integer.parseInt(readline[9])) ;
                        order.setMaster(master);
                    });

                }
                orderList.add(order);

//Order(Date createOrderDate, Date plannedStartDate, Car car, String ownersName, List<Work> work, double price, Integer id)
                line = br.readLine();
            }
            return orderList;

        } catch (Exception e) {
            logger("Error csv Order Reader :" + e);
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