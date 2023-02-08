package bo.custom;

import bo.SuperBO;
import dao.SqlUtil;
import model.StationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StationBO extends SuperBO {

    public ArrayList<StationDTO> getAllStation() throws SQLException, ClassNotFoundException;

    public boolean SaveStation(StationDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateStation(StationDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteStation(String s) throws SQLException, ClassNotFoundException;

    public StationDTO searchStation(String s) throws SQLException, ClassNotFoundException;

    public boolean existStation(String s) throws SQLException, ClassNotFoundException;

    public String generateNewStationId() throws SQLException, ClassNotFoundException;

}
