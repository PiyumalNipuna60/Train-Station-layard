package dao;

import dao.custom.impl.*;

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
                return new StationDAOImpl();
            case TRAIN:
                return new TrainDAOImpl();
            case TRAIN_SCHEDULE:
                return new TrainSchedulDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        BOOKING_CUSTOMER, EMPLOYEE, STATION, TRAIN, TRAIN_SCHEDULE
    }
}
