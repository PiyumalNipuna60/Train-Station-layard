package bo.custom;

import bo.SuperBO;
import bo.custom.impl.BookingCustomerBOImpl;
import bo.custom.impl.EmployeeBOImpl;
import dao.custom.impl.BookingCustomerDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        if (boFactory==null){
             boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BoType{
        BOOKING_CUSTOMER,EMPLOYEE,STATION,TRAIN,TRAIN_SCHEDULE
    }

    public SuperBO getBOType(BoType type){
        switch (type){
            case BOOKING_CUSTOMER:
                return new BookingCustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
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
