package bo.custom.impl;

import bo.custom.EmployeeBO;
import dao.DAOFactory;
import dao.custom.impl.EmployeeDAOImpl;
import model.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAOImpl employeeDAO = (EmployeeDAOImpl) DAOFactory.getDaoFactory().getBOType(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }

    @Override
    public boolean SaveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.Save(dto);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(dto);
    }

    @Override
    public boolean deleteEmployee(String s) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(s);
    }

    @Override
    public EmployeeDTO searchEmployee(String s) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(s);
    }

    @Override
    public boolean existEmployee(String s) throws SQLException, ClassNotFoundException {
        return employeeDAO.exist(s);
    }

    @Override
    public String generateEmployeeNewId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewId();
    }
}
