package dao.custom.impl;

import dao.SqlUtil;
import dao.custom.TrainSchedulDAO;
import model.TrainDTO;
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
    public  ArrayList<TrainSchedulCheckDTO> TrainSchedulCheck(String from,String to) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM  stationSchedule where cusFrom='" + from + "' && cusTo='" + to + "'");
        ArrayList<TrainSchedulCheckDTO> AllTrain = new ArrayList<>();
        while (resultSet.next()){
            AllTrain.add(
                    new TrainSchedulCheckDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9)
                    )
            );
        }
        return AllTrain;
    }
}
