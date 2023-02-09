package dao.custom;

import dao.CrudDAO;
import model.TrainSchedulCheckDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TrainSchedulDAO extends CrudDAO<TrainSchedulCheckDTO,String> {

    public ArrayList<TrainSchedulCheckDTO> TrainSchedulCheck(String from, String to) throws SQLException, ClassNotFoundException;

}
