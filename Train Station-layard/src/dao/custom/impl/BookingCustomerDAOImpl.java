package dao.custom.impl;

import dao.SqlUtil;
import dao.custom.BookingCustomerDAO;
import model.BookingCustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingCustomerDAOImpl implements BookingCustomerDAO {

    public ArrayList<BookingCustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<BookingCustomerDTO> allBokking=new ArrayList<>();
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM booking");
        while (resultSet.next()){
            allBokking.add(new BookingCustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11),
                    resultSet.getString(12)
            ));
        }
        return allBokking;
    }

    @Override
    public boolean Save(BookingCustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("insert into booking values(?,?,?,?,?,?,?,?,?,?,?,?)", dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContact(),
                dto.getTrainFrom(),
                dto.getTrainTo(),
                dto.getTime(),
                dto.getTrain(),
                dto.getSeatNo(),
                dto.getTrainClass(),
                dto.getPrice(),
                dto.getDate());
    }

    @Override
    public boolean update(BookingCustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE booking SET name=?,address=?,contact=?,trainFrom=?, trainTo=?, time=?, train=?, seatNo=?, class=?, price=?,date=?  WHERE id=?",
                dto.getName(),
                dto.getAddress(),
                dto.getContact(),
                dto.getTrainFrom(),
                dto.getTrainTo(),
                dto.getTime(),
                dto.getTrain(),
                dto.getSeatNo(),
                dto.getClass(),
                dto.getPrice(),
                dto.getDate(),
                dto.getId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
       return SqlUtil.executeUpdate("Delete from booking where id=?",s);
    }

    @Override
    public BookingCustomerDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM booking WHERE id=?", s);
        if (resultSet.next()){
            return new BookingCustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11),
                    resultSet.getString(12)
            );
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
       // return SqlUtil.executeUpdate("Select * from booking WHERE id=?", s);

        ResultSet rst = SqlUtil.executeQuery("SELECT id FROM booking WHERE id=?", s);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }


}
