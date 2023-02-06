package dao;

import dao.custom.impl.BookingCustomerDAOImpl;
import dao.custom.impl.EmployeeDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getBOType(DAOTypes type) {
        switch (type) {
            case BOOKING_CUSTOMER:
                return new BookingCustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case STATION:
                return null;
            case TRAIN:
                return null;
            case TRAIN_SCHEDULE:
                return null;
            default:
                return null;
        }
    }

    public enum DAOTypes {
        BOOKING_CUSTOMER, EMPLOYEE, STATION, TRAIN, TRAIN_SCHEDULE
    }
}
