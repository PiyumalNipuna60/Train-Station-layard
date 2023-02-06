package bo.custom.impl;

import bo.custom.TrainBO;
import dao.DAOFactory;
import dao.custom.TrainDAO;
import dao.custom.impl.TrainDAOImpl;
import model.TrainDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TrainBOImpl implements TrainBO {

    private final TrainDAO trainDAO = (TrainDAO) DAOFactory.getDaoFactory().getBOType(DAOFactory.DAOTypes.TRAIN);

    @Override
    public ArrayList<TrainDTO> getAllTrain() throws SQLException, ClassNotFoundException {
        return trainDAO.getAll();
    }

    @Override
    public boolean SaveTrain(TrainDTO dto) throws SQLException, ClassNotFoundException {
        return trainDAO.Save(dto);
    }

    @Override
    public boolean updateTrain(TrainDTO dto) throws SQLException, ClassNotFoundException {
        return trainDAO.update(dto);
    }

    @Override
    public boolean deleteTrain(String id) throws SQLException, ClassNotFoundException {
        return trainDAO.delete(id);
    }

    @Override
    public TrainDTO searchTrain(String id) throws SQLException, ClassNotFoundException {
        return trainDAO.search(id);
    }

    @Override
    public boolean existTrain(String id) throws SQLException, ClassNotFoundException {
        return trainDAO.exist(id);
    }

    @Override
    public String generateNewTrainId() throws SQLException, ClassNotFoundException {
        return trainDAO.generateNewId();
    }
}
