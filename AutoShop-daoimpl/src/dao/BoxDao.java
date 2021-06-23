package dao;

import connect.DbConnect;
import model.entity.car.Car;
import model.entity.garage.Garage;
import model.entity.work.Work;
import utils.FileWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoxDao extends AbstractDao {
    FileWorker fileWorker = new FileWorker();
    String GET_ALL = "SELECT * FROM autoshop.box";
    String GET_CAR_IN_BOX = "select car_id from autoshop.box_cars where box_id=";
    String GET_BOX_BY_ID = "select * FROM autoshop.box where id=";
    String GET_COUNT_BUSY_PLACE = "select count(car_id) from box_cars where box_id=";
    String MODIFY_CAPACITY="UPDATE `autoshop`.`box` SET `capacity` = ? WHERE id =?";
    String FREE_UP_PLACE="DELETE FROM `autoshop`.`box_cars` WHERE car_id=";
    @Override
    public List getall() {
        List<Garage> boxList = new ArrayList<>();
        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int capacity = resultSet.getInt("capacity");
                Garage box = new Garage(id, capacity);
                boxList.add(box);
                return boxList;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    public Garage getBoxById(int boxId) {
        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_BOX_BY_ID + boxId);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int capacity = resultSet.getInt("capacity");

                return new Garage(id, capacity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    public Garage modifyBoxCapacity(int boxId,int newCapacity){
        try {
            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(MODIFY_CAPACITY);

            preparedStatement.setInt(1, newCapacity);
            preparedStatement.setInt(2, boxId);
            preparedStatement.executeUpdate();
            return getBoxById(boxId);
        } catch (SQLException throwables) {
            fileWorker.logger("work dao err " + throwables);
        }

        return null;

    }

    public List<Car> viewCarinBox(int boxId) {
        List<Car> carList = new ArrayList<>();
        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_CAR_IN_BOX + boxId);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String color = resultSet.getString("color");
                String mark = resultSet.getString("mark");
                String model = resultSet.getString("model");
                String number = resultSet.getString("number");
                Car car = new Car(mark, model, color, number, id);
                carList.add(car);
            }
        } catch (SQLException throwables) {
            fileWorker.logger("order dao error " + throwables);
        }
        return carList;


    }

    public int viewFreeBox(int boxId) {
        Garage garage = getBoxById(boxId);
        int capacity = garage.getCapacity();
        int countCars=0;
        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_COUNT_BUSY_PLACE + boxId);

            while (resultSet.next()) {
                 countCars = resultSet.getInt("id");


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return capacity-countCars;
    }

    public String freeUpSpaceBox(int carId){
        try {
            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(FREE_UP_PLACE+carId);

            preparedStatement.executeUpdate();
            return "successful";
        } catch (SQLException throwables) {
            fileWorker.logger("work dao err " + throwables);
        }
        return "unsuccessful";
    }
}
