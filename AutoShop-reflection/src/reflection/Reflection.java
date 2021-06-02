package reflection;

import annotations.CsvEntity;
import annotations.CsvProperty;
import annotations.Property;
import model.entity.Entity;
import model.entity.work.Work;
import utils.FileWorker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
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

    private List<Work> importWork() {
        List<Work> worklist = new ArrayList<>();


        return worklist;
    }

    public void export(List list) {
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

    public void writeCsv(String path, String data, String simpleName, String tableOfContents) throws IOException {

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

