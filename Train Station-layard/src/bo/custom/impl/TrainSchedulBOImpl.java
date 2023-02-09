package bo.custom.impl;

import bo.custom.TrainSchedulBO;
import dao.DAOFactory;
import dao.SqlUtil;
import dao.custom.TrainSchedulDAO;
import model.TrainSchedulCheckDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainSchedulBOImpl implements TrainSchedulBO {

    TrainSchedulDAO trainSchedulDAO = (TrainSchedulDAO) DAOFactory.getDaoFactory().getBOType(DAOFactory.DAOTypes.TRAIN_SCHEDULE);


    @Override
    public ArrayList<TrainSchedulCheckDTO> getAll() throws SQLException, ClassNotFoundException {
        return trainSchedulDAO.getAll();
    }

    @Override
    public boolean Save(TrainSchedulCheckDTO dto) throws SQLException, ClassNotFoundException {
        return trainSchedulDAO.Save(dto);
    }

    @Override
    public boolean update(TrainSchedulCheckDTO dto) throws SQLException, ClassNotFoundException {
        return trainSchedulDAO.update(dto);
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return trainSchedulDAO.delete(s);
    }

    @Override
    public TrainSchedulCheckDTO search(String s) throws SQLException, ClassNotFoundException {
        return trainSchedulDAO.search(s);
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return trainSchedulDAO.exist(s);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return trainSchedulDAO.generateNewId();
    }

    @Override
    public ArrayList<TrainSchedulCheckDTO> TrainSchedulCheck(String from, String to) throws SQLException, ClassNotFoundException {
        return trainSchedulDAO.TrainSchedulCheck(from,to);
    }
}
