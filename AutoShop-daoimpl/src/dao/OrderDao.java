package dao;

import connect.DbConnect;
import model.entity.car.Car;
import model.entity.master.Master;
import model.entity.order.Order;
import model.entity.work.Work;
import model.enums.OrderStatus;
import utils.FileWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


import java.util.List;

public class OrderDao extends AbstractDao {
    private static OrderDao orderDao;
    private final FileWorker fileWorker = new FileWorker();
    private final String GET_ALL = "SELECT * FROM autoshop.orders";
    private final String INSERT = "insert into `autoshop`.`orders`(owners_name,create_date,planned_start_date,car_id) values(?,?,?,?)";
    private final String CANCELED = "UPDATE `autoshop`.`orders` SET `status` = 'CANCELED' WHERE id=?";
    private final String CLOSED = "UPDATE `autoshop`.`orders` SET `status` = 'CLOSED' WHERE id=?";
    private final String DELL = "DELETE FROM `autoshop`.`orders` WHERE id=";
    private final String MODIFY_PLANNED_DATE = "UPDATE `autoshop`.`orders` SET `planned_start_date` = ? WHERE id=?";
    private final String PROGRESS = "UPDATE `autoshop`.`orders` SET `status` = 'IN_PROGRESS' WHERE id=?";
    private final String ORDER_BY_DATE_COMPLETE = "SELECT * FROM autoshop.orders order by completion_date";
    private final String ORDER_BY_DATE_CREATE = "SELECT * FROM autoshop.orders order by create_date";
    private final String ORDER_BY_DATE_PLANNED = "SELECT * FROM autoshop.orders order by planned_start_date";
    private final String ORDER_BY_PRICE = "SELECT * FROM autoshop.orders order by completion_date";
    private final String ORDERS_IN_PROGRESS = "SELECT * FROM autoshop.orders where status='IN_PROGRESS'";
    private final String ORDERS_IN_TIME = "SELECT * FROM autoshop.orders where create_date>=? and create_date<=?";
    private final String ORDER_TO_MASTER="UPDATE `autoshop`.`orders` SET `master_id` = ? WHERE (`id` = '?');";

    public static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDao();
        }
        return orderDao;
    }

    public List<Order> getOrderList() {
        List<Order> orderList = new ArrayList<>();

        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int carId = resultSet.getInt("car_id");
                Date completionDate = resultSet.getDate("completion_date");
                int masterId = resultSet.getInt("master_id");
                Date create_date = resultSet.getDate("create_date");
                Date plannedStartDate = resultSet.getDate("planned_start_date");
                String ownersName = resultSet.getString("owners_name");
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
                List<Work> workList = WorkDao.getInstance().getWorkByIdOrder(id);
                Master master = MasterDao.getInstance().getMasterById(masterId);
                Car car = CarDao.getInstance().getCarById(carId);
                Order order = new Order(create_date, plannedStartDate, car, ownersName, workList, getPrice(workList), id);
                orderList.add(order);
            }
        } catch (SQLException throwables) {
            fileWorker.logger("order dao error " + throwables);
        }
        return orderList;
    }

    public Order getOrderById(int orderId) {
        for (Order order : getOrderList()) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public Order addOrder(Date plannedStartDate, Car car, String ownersName) {


        try {

            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(INSERT);
            preparedStatement.setString(1, ownersName);
            Date create = new Date();
            preparedStatement.setDate(2, (java.sql.Date) create);
            preparedStatement.setDate(3, (java.sql.Date) plannedStartDate);
            preparedStatement.setInt(4, car.getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT id FROM autoshop.orders where owners_name='" + ownersName + "' and car_id=" + car.getId());
            resultSet.next();
            int id = resultSet.getInt("id");

            return new Order(create, plannedStartDate, car, ownersName, id);

        } catch (SQLException throwables) {
            fileWorker.logger("order dao err " + throwables);
        }
        return null;
    }

    public Order canceledOrder(int id) {
        try {
            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(CANCELED);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            fileWorker.logger("order dao err " + throwables);
        }
        return getOrderById(id);
    }

    public Order closeOrder(int id) {
        try {
            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(CLOSED);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            fileWorker.logger("order dao err " + throwables);
        }
        return getOrderById(id);

    }

    public Order dellOrder(int id) {
        try {
            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(DELL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            fileWorker.logger("order dao err " + throwables);
        }
        return getOrderById(id);
    }

    public Order modifyPlannedStartDate(int id, Date date) {
        try {
            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(MODIFY_PLANNED_DATE);
            preparedStatement.setDate(1, (java.sql.Date) date);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            fileWorker.logger("order dao err " + throwables);
        }
        return getOrderById(id);
    }

    public Order progressOrder(int id) {
        try {
            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(PROGRESS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            fileWorker.logger("order dao err " + throwables);
        }
        return getOrderById(id);
    }

    public String viewAllOrder(List<Order> orderList) {
        String line = "";
        for (Order order : orderList) {
            line = line + order.toString();
        }
        return line;
    }

    public List<Order> sortByComplete() {
        return sort(ORDER_BY_DATE_COMPLETE);
    }

    public List<Order> sortByCreate() {
        return sort(ORDER_BY_DATE_CREATE);
    }

    public List<Order> sortByPlanned() {
        return sort(ORDER_BY_DATE_PLANNED);
    }

    public List<Order> sortByPRICE() {
        return sort(ORDER_BY_PRICE);
    }

    public List<Order> getOrdersInProgress() {
        List<Order> orderList = new ArrayList<>();

        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(ORDERS_IN_PROGRESS);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int carId = resultSet.getInt("car_id");
                Date completionDate = resultSet.getDate("completion_date");
                int masterId = resultSet.getInt("master_id");
                Date create_date = resultSet.getDate("create_date");
                Date plannedStartDate = resultSet.getDate("planned_start_date");
                String ownersName = resultSet.getString("owners_name");
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
                List<Work> workList = WorkDao.getInstance().getWorkByIdOrder(id);
                Master master = MasterDao.getInstance().getMasterById(masterId);
                Car car = CarDao.getInstance().getCarById(carId);
                Order order = new Order(create_date, plannedStartDate, car, ownersName, workList, getPrice(workList), id);
                orderList.add(order);
            }
        } catch (SQLException throwables) {
            fileWorker.logger("order dao error " + throwables);
        }
        return orderList;
    }

    public List<Order> getOrderInTime(Date first, Date second) {
        List<Order> orderList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(ORDERS_IN_TIME);
            preparedStatement.setDate(1, (java.sql.Date) first);
            preparedStatement.setDate(2, (java.sql.Date) second);
            ResultSet resultSet = preparedStatement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int carId = resultSet.getInt("car_id");
                Date completionDate = resultSet.getDate("completion_date");
                int masterId = resultSet.getInt("master_id");
                Date create_date = resultSet.getDate("create_date");
                Date plannedStartDate = resultSet.getDate("planned_start_date");
                String ownersName = resultSet.getString("owners_name");
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
                List<Work> workList = WorkDao.getInstance().getWorkByIdOrder(id);
                Master master = MasterDao.getInstance().getMasterById(masterId);
                Car car = CarDao.getInstance().getCarById(carId);
                Order order = new Order(create_date, plannedStartDate, car, ownersName, workList, getPrice(workList), id);
                orderList.add(order);
            }
        } catch (SQLException throwables) {
            fileWorker.logger("order dao error " + throwables);
        }
        return orderList;
    }

    public Order copyOrderAndModify(Order order, Date plannedStartDate, Car car, String ownersName) {
        if (plannedStartDate != null) {
            order.setPlannedStartDate(plannedStartDate);
        }
        if (car != null) {
            order.setCar(car);
        }
        if (ownersName != null) {
            order.setOwnersName(ownersName);
        }
        addOrder(order.getPlannedStartDate(), order.getCar(), order.getOwnersName());
        return order;

    }

    private List<Order> sort(String sort) {
        List<Order> orderList = new ArrayList<>();

        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sort);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int carId = resultSet.getInt("car_id");
                Date completionDate = resultSet.getDate("completion_date");
                int masterId = resultSet.getInt("master_id");
                Date create_date = resultSet.getDate("create_date");
                Date plannedStartDate = resultSet.getDate("planned_start_date");
                String ownersName = resultSet.getString("owners_name");
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
                List<Work> workList = WorkDao.getInstance().getWorkByIdOrder(id);
                Master master = MasterDao.getInstance().getMasterById(masterId);
                Car car = CarDao.getInstance().getCarById(carId);
                Order order = new Order(create_date, plannedStartDate, car, ownersName, workList, getPrice(workList), id);
                orderList.add(order);
            }
        } catch (SQLException throwables) {
            fileWorker.logger("order dao error " + throwables);
        }
        return orderList;
    }

    private double getPrice(List<Work> workList) {
        double price = 0;
        for (Work work : workList) {
            price += work.getPrice();
        }
        return price;
    }

    public Order transferToMaster(int orderId,int masterId){
        try {

            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(ORDER_TO_MASTER);
            preparedStatement.setInt(1, masterId);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();

            return getOrderById(orderId);

        } catch (SQLException throwables) {
            fileWorker.logger("order dao err " + throwables);
        }
        return null;

    }

    @Override
    public List getall() {
        List<Order> orderList = new ArrayList<>();

        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int carId = resultSet.getInt("car_id");
                Date completionDate = resultSet.getDate("completion_date");
                int masterId = resultSet.getInt("master_id");
                Date create_date = resultSet.getDate("create_date");
                Date plannedStartDate = resultSet.getDate("planned_start_date");
                String ownersName = resultSet.getString("owners_name");
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
                List<Work> workList = WorkDao.getInstance().getWorkByIdOrder(id);
                Master master = MasterDao.getInstance().getMasterById(masterId);
                Car car = CarDao.getInstance().getCarById(carId);
                Order order = new Order(create_date, plannedStartDate, car, ownersName, workList, getPrice(workList), id);
                orderList.add(order);
                return orderList;
            }
        } catch (SQLException throwables) {
            fileWorker.logger("order dao error " + throwables);
        }


        return null;
    }
}
