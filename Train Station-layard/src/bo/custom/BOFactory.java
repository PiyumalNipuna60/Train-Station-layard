package bo.custom;

import bo.SuperBO;
import dao.custom.impl.BookingCustomerDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    private BOFactory getBoFactory(){
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
