package dao;

import connect.DbConnect;
import model.entity.car.Car;
import model.entity.order.Order;
import utils.FileWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarDao extends AbstractDao {
    private static CarDao carDao;
    private final FileWorker fileWorker = new FileWorker();
    private final String GET_ALL = "SELECT * FROM autoshop.cars";
    private final String BOX_CARS = "insert into `autoshop`.`box_cars`(box_id,car_id) values(?,?)";
    private final String INSERT = "insert into `autoshop`.`cars`(mark,model,color,number) values(?,?,?,?)";

    public static CarDao getInstance() {
        if (carDao == null) {
            carDao = new CarDao();
        }
        return carDao;
    }


    public String setCarInBox(int carId, int boxId) {
        try {

            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(BOX_CARS);
            preparedStatement.setInt(1, boxId);
            preparedStatement.setInt(2, carId);

            preparedStatement.executeUpdate();

            return "successful";
        } catch (SQLException throwables) {
            fileWorker.logger("order dao err " + throwables);
        }
        return "unsuccessfully";

    }

    public Car addCar(String mark, String model, String color, String number) {

        try {

            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(INSERT);
            preparedStatement.setString(1, mark);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, color);
            preparedStatement.setString(4, number);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.executeQuery("SELECT id FROM autoshop.cars where number=" + number);
            resultSet.next();
            int id = resultSet.getInt("id");

            return new Car(mark, model, color, number, id);

        } catch (SQLException throwables) {
            fileWorker.logger("order dao err " + throwables);
        }
        return null;


    }


    public Car getCarById(int id) {
        List<Car> carList = getall();
        for (Car car : carList) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    @Override
    public List getall() {
        List<Car> carList = new ArrayList<>();

        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

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
}
