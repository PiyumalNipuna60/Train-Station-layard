package dao.custom;

import dao.CrudDAO;
import model.TrainSchedulCheckDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TrainSchedulDAO extends CrudDAO<TrainSchedulCheckDTO,String> {

    public ResultSet TrainSchedulCheck(String from, String to) throws SQLException, ClassNotFoundException;

}
