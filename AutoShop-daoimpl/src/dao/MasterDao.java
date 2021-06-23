package dao;


import connect.DbConnect;
import model.entity.master.Master;


import model.enums.Specialty;
import utils.FileWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MasterDao extends AbstractDao  {
    private static MasterDao masterDao;
    private final FileWorker fileWorker = new FileWorker();
    private final String GET_ALL = "SELECT * FROM autoshop.masters";
    private final String INSERT = "insert into `autoshop`.`masters`(name,date_birth,specialty,phone_number,status )values(?,?,?,?,?);";
    private final String DELETE = "DELETE FROM `autoshop`.`masters` WHERE id =";
    private final String SORT_BY_NAME = "SELECT * FROM autoshop.masters order by name";
    private final String SORT_BY_BUSY = "SELECT * FROM autoshop.masters order by status";
    private final String MASTER_BY_ID = "SELECT * FROM autoshop.masters WHERE ID =";
    private final String MASTER_BY_ORDER_ID="select master_id from autoshop.orders where id=";

    public static MasterDao getInstance() {

        if (masterDao == null) {
            masterDao = new MasterDao();
        }
        return masterDao;
    }



    public Master addMaster(String name, String dateOfbirth, String specialty, String phoneNumber) {
        PreparedStatement preparedStatement = null;
        int id = 0;
        try {

            preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(INSERT);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, dateOfbirth);
            preparedStatement.setString(3, specialty);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery("SELECT id FROM autoshop.masters where name='" + name + "' and phone_number=" + phoneNumber);

            resultSet.next();
            id = resultSet.getInt("id");

        } catch (SQLException throwables) {
            fileWorker.logger("work dao err " + throwables);
        }

        return new Master(name, dateOfbirth, phoneNumber, Specialty.valueOf(specialty), id);
    }

    public Master dellMaster(int id) {
        try {
            PreparedStatement preparedStatement = DbConnect.getInstance().getConnection().prepareStatement(DELETE + id);
            preparedStatement.executeUpdate();
            return getMasterById(id);
        } catch (SQLException throwables) {
            fileWorker.logger("work dao err " + throwables);
        }
        return null;

    }

    public String viewAllMaster() {

        List<Master> masterList = getall();
        String line = "";
        for (Master master : masterList) {
            line = line + "\n" + master;
        }
        return line;
    }

    public List<Master> sortBusyMaster() {
        List<Master> masterList = new ArrayList<>();

        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SORT_BY_BUSY);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dateOfbirth = resultSet.getString("date_birth");
                String phoneNumber = resultSet.getString("phone_number");
                String specialty = resultSet.getString("specialty");
                Master master = new Master(name, dateOfbirth, phoneNumber, Specialty.valueOf(specialty), id);
                masterList.add(master);
            }
        } catch (SQLException throwables) {
            fileWorker.logger("master dao error " + throwables);
        }
        return masterList;

    }

    public List<Master> sortNameMaster() {
        List<Master> masterList = new ArrayList<>();

        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SORT_BY_NAME);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dateOfbirth = resultSet.getString("date_birth");
                String phoneNumber = resultSet.getString("phone_number");
                String specialty = resultSet.getString("specialty");
                Master master = new Master(name, dateOfbirth, phoneNumber, Specialty.valueOf(specialty), id);
                masterList.add(master);
            }
        } catch (SQLException throwables) {
            fileWorker.logger("master dao error " + throwables);
        }
        return masterList;
    }

    public Master getMasterById(int id) {
        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(MASTER_BY_ID + id);
            while (resultSet.next()) {
                int masterId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dateOfbirth = resultSet.getString("date_birth");
                String phoneNumber = resultSet.getString("phone_number");
                String specialty = resultSet.getString("specialty");
                return new Master(name, dateOfbirth, phoneNumber, Specialty.valueOf(specialty), masterId);
            }
        } catch (SQLException throwables) {
            fileWorker.logger("master dao err " + throwables);
        }
        return null;
    }

    public Master getMasterInOrder(int orderId){
        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(MASTER_BY_ORDER_ID + orderId);
            while (resultSet.next()) {
                int masterId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dateOfbirth = resultSet.getString("date_birth");
                String phoneNumber = resultSet.getString("phone_number");
                String specialty = resultSet.getString("specialty");
                return new Master(name, dateOfbirth, phoneNumber, Specialty.valueOf(specialty), masterId);
            }
        } catch (SQLException throwables) {
            fileWorker.logger("master dao err " + throwables);
        }
        return null;


    }


    @Override
    public List getall() {
        List<Master> masterList = new ArrayList<>();

        try {
            Statement statement = DbConnect.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dateOfbirth = resultSet.getString("date_birth");
                String phoneNumber = resultSet.getString("phone_number");
                String specialty = resultSet.getString("specialty");
                Master master = new Master(name, dateOfbirth, phoneNumber, Specialty.valueOf(specialty), id);
                masterList.add(master);
            }
        } catch (SQLException throwables) {
            fileWorker.logger("master dao err " + throwables);
        }
        return masterList;
    }
}
