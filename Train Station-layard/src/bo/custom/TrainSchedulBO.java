package bo.custom;

import bo.SuperBO;
import model.TrainSchedulCheckDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TrainSchedulBO extends SuperBO {

    public ArrayList<TrainSchedulCheckDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean Save(TrainSchedulCheckDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(TrainSchedulCheckDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String s) throws SQLException, ClassNotFoundException;

    public TrainSchedulCheckDTO search(String s) throws SQLException, ClassNotFoundException ;

    public boolean exist(String s) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException ;

    public ArrayList<TrainSchedulCheckDTO> TrainSchedulCheck(String from, String to) throws SQLException, ClassNotFoundException;

}
