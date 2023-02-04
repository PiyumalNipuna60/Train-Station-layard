package dao.custom.impl;

import dao.SqlUtil;
import dao.custom.EmployeeDAO;
import model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("Select * from employee");
        ArrayList<EmployeeDTO> AllEmployee = new ArrayList<>();
        while (resultSet.next()){
            AllEmployee.add(
                    new EmployeeDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    ));
        }
        return AllEmployee;
    }

    @Override
    public boolean Save(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public EmployeeDTO search(String s) throws SQLException, ClassNotFoundException {
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
