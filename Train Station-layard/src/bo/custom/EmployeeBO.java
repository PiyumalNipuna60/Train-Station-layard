package bo.custom;

import bo.SuperBO;
import dao.SqlUtil;
import model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    public boolean SaveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteEmployee(String s) throws SQLException, ClassNotFoundException;

    public EmployeeDTO searchEmployee(String s) throws SQLException, ClassNotFoundException;

    public boolean existEmployee(String s) throws SQLException, ClassNotFoundException;

    public String generateEmployeeNewId() throws SQLException, ClassNotFoundException;
}
