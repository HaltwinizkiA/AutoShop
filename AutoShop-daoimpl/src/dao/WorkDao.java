package dao;


import connect.DbConnect;
import model.entity.work.Work;
import utils.FileWorker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkDao extends AbstractDao {
    private static WorkDao workDao;
    private final FileWorker fileWorker = new FileWorker();
    private final String GET_ALL = "SELECT * FROM autoshop.works";
    private final String INSERT = "insert into `autoshop`.`works`(name,price)values(?,?)";
    private final String DELETE = "DELETE FROM `autoshop`.`works` WHERE id =";
    private final String ORDERS_WORK = "SELECT * FROM autoshop.orders_works where order_id=";
    private final String WORK_TO_ORDER = "insert `autoshop`.`orders_works`(order_id,work_id)values(?,?)";
    private final String WORK_BY_ID = "SELECT * FROM autoshop.works where id=";

    public static WorkDao getInstance() {
        if (workDao == null) {
            workDao = new WorkDao();
        }
        return workDao;
    }


    public Work addWork(String name, String price) {
        try {
            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(INSERT);

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, Double.parseDouble(price));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery("SELECT id FROM autoshop.works where name='" + name + "' and price=" + price);

            resultSet.next();
            int id = resultSet.getInt("id");
            return new Work(name, Double.parseDouble(price), id);
        } catch (SQLException throwables) {
            fileWorker.logger("work dao err " + throwables);
        }

        return null;
    }

    public void deleteWork(String id) {
        try {

            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(DELETE + id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            fileWorker.logger("work dao err " + throwables);
        }

    }

    public String viewAllWork() {
        List<Work> workList = getall();
        String line = "";
        for (Work work : workList) {
            line = line + "\n" + work;
        }
        return line;
    }

    public Work getWorkById(int id) {

        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(WORK_BY_ID + id);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                return new Work(name, price, id);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Work> getWorkByIdOrder(int orderId) {
        List<Work> workList = new ArrayList<>();
        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(ORDERS_WORK + orderId);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Work work = new Work(name, price, id);
                workList.add(work);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return workList;
    }

    public List<Work> setWorkToOrder(int orderId, String... workId) {
        List<Work> workList = new ArrayList<>();
        for (String i : workId) {
            try {


                PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(WORK_TO_ORDER);
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, Integer.parseInt(i));
                preparedStatement.executeUpdate();
                workList.add(getWorkById(Integer.parseInt(i)));
            } catch (SQLException throwables) {
                fileWorker.logger("work dao err " + throwables);
            }


        }
        return workList;

    }

    @Override
    public List getall() {
        List<Work> workList = new ArrayList<>();
        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Work work = new Work(name, price, id);
                workList.add(work);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return workList;
    }
}
