package dao.custom.impl;

import dao.SqlUtil;
import dao.custom.StationDAO;
import model.StationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StationDAOImpl implements StationDAO {
    @Override
    public ArrayList<StationDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.executeQuery("Select*from station");
        ArrayList<StationDTO> AllStation = new ArrayList<>();
        while (rst.next()) {
            AllStation.add(
                    new StationDTO(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3)
                    ));
        }
        return AllStation;
    }

    @Override
    public boolean Save(StationDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("Insert into station values(?,?,?)",dto.getId(),dto.getName(),dto.getContact());
    }

    @Override
    public boolean update(StationDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("Update station  set name=?,contact=? where id=?",dto.getName(),dto.getContact(),dto.getId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
       return SqlUtil.executeUpdate("Delete from station where id=?",s);
    }

    @Override
    public StationDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("Select*from station where id=?", s);
        if (resultSet.next()){
            return new StationDTO(
              resultSet.getString(1),
              resultSet.getString(2),
              resultSet.getString(3)
            );
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("select id from station where id=?", s);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
