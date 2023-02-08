package bo.custom.impl;

import bo.custom.TrainSchedulBO;
import dao.custom.TrainSchedulDAO;
import model.TrainSchedulCheckDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TrainSchedulBOImpl implements TrainSchedulBO {

    TrainSchedulBO trainSchedulBO = new TrainSchedulBOImpl();


    @Override
    public ArrayList<TrainSchedulCheckDTO> getAll() throws SQLException, ClassNotFoundException {
        return trainSchedulBO.getAll();
    }

    @Override
    public boolean Save(TrainSchedulCheckDTO dto) throws SQLException, ClassNotFoundException {
        return trainSchedulBO.Save(dto);
    }

    @Override
    public boolean update(TrainSchedulCheckDTO dto) throws SQLException, ClassNotFoundException {
        return trainSchedulBO.update(dto);
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return trainSchedulBO.delete(s);
    }

    @Override
    public TrainSchedulCheckDTO search(String s) throws SQLException, ClassNotFoundException {
        return trainSchedulBO.search(s);
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return trainSchedulBO.exist(s);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return trainSchedulBO.generateNewId();
    }
}
