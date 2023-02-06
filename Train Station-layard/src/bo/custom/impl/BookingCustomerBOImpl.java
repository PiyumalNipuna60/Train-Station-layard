package bo.custom.impl;

import bo.custom.BookingCustomerBO;
import dao.custom.impl.BookingCustomerDAOImpl;
import model.BookingCustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookingCustomerBOImpl implements BookingCustomerBO {
    BookingCustomerDAOImpl bookingCustomerDAO = new BookingCustomerDAOImpl();

    @Override
    public ArrayList<BookingCustomerDTO> getAllCustomerBooking() throws SQLException, ClassNotFoundException {

        return null;
    }

    @Override
    public boolean saveCustomerBooking(BookingCustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateCustomerBooking(BookingCustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existCustomerBooking(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCustomerBooking(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateCustomerBookingNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
