package dao.custom.impl;

import dao.SqlUtil;
import dao.custom.TrainDAO;
import model.TrainDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainDAOImpl implements TrainDAO {
    @Override
    public ArrayList<TrainDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<TrainDTO> AllTrain = new ArrayList<>();
        ResultSet rst = SqlUtil.executeQuery("Select * from train");

        while (rst.next()){
            AllTrain.add(
                    new TrainDTO(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5),
                            rst.getString(6)
                            ));
        }
        return AllTrain;
    }

    @Override
    public boolean Save(TrainDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("insert into train Values(?,?,?,?,?,?)",
                dto.getTrainId(),
                dto.getTrainName(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getTrainFrom(),
                dto.getTrainTo()
        );
    }

    @Override
    public boolean update(TrainDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("update train set trainName=?,startTime=?,EndTime=?,trainFrom=?,trainTo=? where trainId=?",
                dto.getTrainId(),
                dto.getTrainName(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getTrainFrom(),
                dto.getTrainTo()
                );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("delete from train where trainId=?",s);
    }

    @Override
    public TrainDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.executeQuery("Select * from train where trainId=?", s);
        if (rst.next()){
            return new TrainDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("Select trainId from train where trainId=?", s);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
