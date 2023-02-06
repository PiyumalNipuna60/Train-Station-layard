package dao;

import bo.SuperBO;
import bo.custom.BOFactory;
import bo.custom.impl.BookingCustomerBOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        BOOKING_CUSTOMER,EMPLOYEE,STATION,TRAIN,TRAIN_SCHEDULE
    }

    public String getBOType(BOFactory.BoType type){
        switch (type){
            case BOOKING_CUSTOMER:
                return null;
            case EMPLOYEE:
                return null;
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
}
