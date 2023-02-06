package bo.custom.impl;

import bo.custom.BookingCustomerBO;
import dao.DAOFactory;
import dao.custom.impl.BookingCustomerDAOImpl;
import model.BookingCustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookingCustomerBOImpl implements BookingCustomerBO {
    BookingCustomerDAOImpl bookingCustomerDAO = (BookingCustomerDAOImpl) DAOFactory.getDaoFactory().getBOType(DAOFactory.DAOTypes.BOOKING_CUSTOMER);

    @Override
    public ArrayList<BookingCustomerDTO> getAllCustomerBooking() throws SQLException, ClassNotFoundException {
        return bookingCustomerDAO.getAll();
    }

    @Override
    public boolean saveCustomerBooking(BookingCustomerDTO dto) throws SQLException, ClassNotFoundException {
        return bookingCustomerDAO.Save(dto);
    }

    @Override
    public boolean updateCustomerBooking(BookingCustomerDTO dto) throws SQLException, ClassNotFoundException {
        return bookingCustomerDAO.update(dto);
    }

    @Override
    public boolean existCustomerBooking(String id) throws SQLException, ClassNotFoundException {
        return bookingCustomerDAO.exist(id);
    }

    @Override
    public boolean deleteCustomerBooking(String id) throws SQLException, ClassNotFoundException {
        return bookingCustomerDAO.delete(id);
    }

    @Override
    public String generateCustomerBookingNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public BookingCustomerDTO searchCustomerBook(String s) throws SQLException, ClassNotFoundException {
        return bookingCustomerDAO.search(s);
    }
}
