package bo.custom;

import bo.SuperBO;
import model.TrainDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TrainBO extends SuperBO {

    public ArrayList<TrainDTO> getAllTrain() throws SQLException, ClassNotFoundException;

    public boolean SaveTrain(TrainDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateTrain(TrainDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteTrain(String id) throws SQLException, ClassNotFoundException;

    public TrainDTO searchTrain(String id) throws SQLException, ClassNotFoundException;

    public boolean existTrain(String id) throws SQLException, ClassNotFoundException;

    public String generateNewTrainId() throws SQLException, ClassNotFoundException;
}
