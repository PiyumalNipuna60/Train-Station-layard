package bo.custom.impl;

import bo.custom.StationBO;
import dao.custom.StationDAO;
import dao.custom.impl.StationDAOImpl;
import model.StationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class StationBOImpl implements StationBO {

    StationDAO stationDAO = new StationDAOImpl();

    @Override
    public ArrayList<StationDTO> getAllStation() throws SQLException, ClassNotFoundException {
        return stationDAO.getAll();
    }

    @Override
    public boolean SaveStation(StationDTO dto) throws SQLException, ClassNotFoundException {
        return stationDAO.Save(dto);
    }

    @Override
    public boolean updateStation(StationDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteStation(String s) throws SQLException, ClassNotFoundException {
        return stationDAO.delete(s);
    }

    @Override
    public StationDTO searchStation(String s) throws SQLException, ClassNotFoundException {
        return stationDAO.search(s);
    }

    @Override
    public boolean existStation(String s) throws SQLException, ClassNotFoundException {
        return stationDAO.exist(s);
    }

    @Override
    public String generateNewStationId() throws SQLException, ClassNotFoundException {
        return stationDAO.generateNewId();
    }
}
