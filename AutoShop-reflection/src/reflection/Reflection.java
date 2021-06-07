package reflection;

import annotations.CsvEntity;
import annotations.CsvProperty;
import annotations.Property;
import model.entity.Entity;
import model.entity.car.Car;
import model.entity.master.Master;
import model.entity.order.Order;
import model.entity.work.Work;
import model.enums.OrderStatus;
import model.enums.Specialty;
import utils.FileWorker;

import java.io.*;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reflection {
    private final Map<Integer, Object> entityList;
    private final Map<Integer, String> entityName;
    private final FileWorker fileWorker;


    public Reflection() {
        this.entityList = new HashMap<>();
        this.entityName = new HashMap<>();
        this.fileWorker = new FileWorker();
    }

    public List<Work> importWorks() {
        List<Work> workList = new ArrayList<>();
        Class workClass = Work.class;
        CsvEntity entityAnnotation = (CsvEntity) workClass.getAnnotation(CsvEntity.class);
        String path = entityAnnotation.fileName();
        String separator = entityAnnotation.separator();

        List<String> csvData = importData(path);
        for (int i = 1; i < csvData.size(); i++) {
            String[] readline = csvData.get(i).split(separator);
            workList.add(new Work(readline[1], Double.parseDouble(readline[2]), Integer.parseInt(readline[0])));
        }

        return workList;
    }

    public List<Master> importMasters() {
        List<Master> masterList = new ArrayList<>();
        Class workClass = Master.class;
        CsvEntity entityAnnotation = (CsvEntity) workClass.getAnnotation(CsvEntity.class);
        String path = entityAnnotation.fileName();
        String separator = entityAnnotation.separator();
        List<String> csvData = importData(path);
        for (int i = 1; i < csvData.size(); i++) {
            String[] readline = csvData.get(i).split(separator);
            masterList.add(new Master(readline[1], readline[5], readline[4], Specialty.valueOf(readline[3]), Integer.parseInt(readline[0]), readline[2]));
        }
        return masterList;
    }

    public List<Order> importOrder() {
        List<Order> orderList = new ArrayList<>();
        List<Work> workList = new ArrayList<>();
        Class orderClass = Order.class;
        CsvEntity entityAnnotation = (CsvEntity) orderClass.getAnnotation(CsvEntity.class);
        String path = entityAnnotation.fileName();
        String separator = entityAnnotation.separator();
        List<String> csvData = importData(path);
        for (int i = 1; i < csvData.size(); i++) {
            String[] readline = csvData.get(i).split(separator);

            String[] workId = readline[7].split("/");
            for (String id : workId) {
                importWorks().forEach(work -> {
                    if (work.getId() == Integer.parseInt(id)) {
                        workList.add(work);
                    }
                });

            }
            String[] car = readline[1].split("/");
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            dateFormat.setLenient(false);
            Date plannedStartDate = null;
            Date createOrderDate = null;
            Date completedDate = null;
            try {
                plannedStartDate = dateFormat.parse(readline[6]);
                createOrderDate = dateFormat.parse(readline[4]);
                if (!readline[5].equals("null")) {
                    completedDate = dateFormat.parse(readline[9]);
                }

            } catch (ParseException e) {
                fileWorker.logger(e.toString());
            }

            Order order = new Order(createOrderDate, plannedStartDate, new Car(car[0], car[1], car[2], car[3], Integer.parseInt(car[4])), readline[2], workList, Double.parseDouble(readline[5]), Integer.parseInt(readline[0]));
            order.setStatus(OrderStatus.valueOf(readline[3]));
            if (!readline[9].equals("null")) {
                List<Master> masterList = importMasters();
                masterList.forEach(master -> {
                    if (master.getId() == Integer.parseInt(readline[8])) ;
                    order.setMaster(master);
                });

            }
            orderList.add(order);
        }
        return orderList;
    }

    public void export(List list) {
        entityName.clear();
        entityList.clear();
        String csvData = "";
        String name = "";
        String tableOfContents = "";
        String path = "";
        for (Object object : list) {
            Class entityClass = object.getClass();
            name = entityClass.getSimpleName();
            CsvEntity entityAnnotation = (CsvEntity) entityClass.getAnnotation(CsvEntity.class);
            String separator = entityAnnotation.separator();
            path = entityAnnotation.fileName();
            Field[] entityFields = entityClass.getDeclaredFields();
            for (Field field : entityFields) {
                CsvProperty fieldProperty = field.getAnnotation(CsvProperty.class);
                field.setAccessible(true);
                try {
                    if (fieldProperty != null && fieldProperty.property() == Property.SimpleProperty) {
                        entityName.put(fieldProperty.colomnNuber(), fieldProperty.keyField());
                        entityList.put(fieldProperty.colomnNuber(), field.get(object).toString());

                    } else if (fieldProperty != null && fieldProperty.property() == Property.GetIdProperty) {

                        entityName.put(fieldProperty.colomnNuber(), fieldProperty.keyField());
                        Entity entity = (Entity) field.get(object);
                        if (entity != null) {
                            entityList.put(fieldProperty.colomnNuber(), entity.getId());
                        } else {
                            entityList.put(fieldProperty.colomnNuber(), null);
                        }
                    } else if (fieldProperty != null && fieldProperty.property() == Property.DateProperty) {
                        entityName.put(fieldProperty.colomnNuber(), fieldProperty.keyField());
                        Date date = (Date) field.get(object);
                        if (date != null) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
                            entityList.put(fieldProperty.colomnNuber(), simpleDateFormat.format(date));
                        } else {
                            entityList.put(fieldProperty.colomnNuber(), null);
                        }
                    } else if (fieldProperty != null && fieldProperty.property() == Property.ListProperty) {
                        entityName.put(fieldProperty.colomnNuber(), fieldProperty.keyField());
                        List<Entity> entity = (List<Entity>) field.get(object);
                        if (entity != null) {
                            StringBuffer buffer = new StringBuffer();
                            buffer.append(entity.get(0).getId());
                            for (int i = 1; i < entity.size(); i++) {
                                buffer.append("/" + entity.get(i).getId());
                            }

                            entityList.put(fieldProperty.colomnNuber(), buffer.toString());
                        } else {
                            entityList.put(fieldProperty.colomnNuber(), null);
                        }
                    } else if (fieldProperty != null && fieldProperty.property() == Property.CarProperty) {
                        entityName.put(fieldProperty.colomnNuber(), fieldProperty.keyField());
                        Car car = (Car) field.get(object);
                        entityList.put(fieldProperty.colomnNuber(), car.getMark() + "/" + car.getNumber() + "/" + car.getColor() + "/" + car.getNumber() + "/" + car.getId());

                    }
                } catch (Exception e) {
                    fileWorker.logger("reflection export error " + e);
                }

            }
            csvData = getCsvDataToWrite(entityList, csvData, separator);
            tableOfContents = tableOfContents(entityName, separator);
        }
        try {
            writeCsv(path, csvData, name, tableOfContents);
        } catch (Exception e) {
            System.out.println("123" + e);
        }

    }

    private List<String> importData(String path) {
        String line;
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(
                path))) {
            while ((line = reader.readLine()) != null) {
                if (!"".equals(line)) {
                    Scanner scanner = new Scanner(line);
                    scanner.useDelimiter(";");
                    list.add(line);
                }
            }
        } catch (Exception e) {
            fileWorker.logger("import Data Error" + e);
            return null;
        }
        return list;
    }

    private void writeCsv(String path, String data, String simpleName, String tableOfContents) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            if (null != simpleName) {
                bw.write(tableOfContents + "\n" + data);
            }
        } catch (Exception e) {
            fileWorker.logger("CSV REFLECT EXPORT ERROR" + e);
        }


    }

    private String tableOfContents(Map nameList, String separator) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < nameList.size(); j++) {
            builder.append(nameList.get(j));
            builder.append(separator);
        }
        return builder.toString();

    }

    private String getCsvDataToWrite(Map entityMap, String csvData, String separator) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < entityMap.size(); j++) {
            builder.append(entityMap.get(j));
            builder.append(separator);
        }
        builder.append("\n");
        if (csvData != null) {
            builder.append(csvData);
        }
        return builder.toString();
    }


}

