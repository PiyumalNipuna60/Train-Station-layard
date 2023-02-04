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

    }

    @Override
    public boolean Save(TrainDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(TrainDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public TrainDTO search(String s) throws SQLException, ClassNotFoundException {
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
}
