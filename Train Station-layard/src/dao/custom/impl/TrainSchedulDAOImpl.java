package dao.custom.impl;

import dao.SqlUtil;
import dao.custom.TrainSchedulDAO;
import model.TrainSchedulCheckDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainSchedulDAOImpl implements TrainSchedulDAO {
    @Override
    public ArrayList<TrainSchedulCheckDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Save(TrainSchedulCheckDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(TrainSchedulCheckDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public TrainSchedulCheckDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet TrainSchedulCheck(String from,String to) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM  stationSchedule where cusFrom='" + from + "' && cusTo='" + to + "'");
        return resultSet;
    }
}
