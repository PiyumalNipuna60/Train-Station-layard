package bo.custom;

import model.BookingCustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingCustomerBO{

    public ArrayList<BookingCustomerDTO> getAllCustomerBooking() throws SQLException, ClassNotFoundException;

    public boolean saveCustomerBooking(BookingCustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateCustomerBooking(BookingCustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean existCustomerBooking(String id) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomerBooking(String id) throws SQLException, ClassNotFoundException ;

    public String generateCustomerBookingNewId() throws SQLException, ClassNotFoundException;
}
