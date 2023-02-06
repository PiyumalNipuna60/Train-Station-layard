package bo.custom;

import bo.SuperBO;
import bo.custom.impl.BookingCustomerBOImpl;
import bo.custom.impl.EmployeeBOImpl;
import bo.custom.impl.TrainBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBOType(BoType type) {
        switch (type) {
            case BOOKING_CUSTOMER:
                return new BookingCustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case STATION:
                return null;
            case TRAIN:
                return new TrainBOImpl();
            case TRAIN_SCHEDULE:
                return null;
            default:
                return null;
        }
    }

    public enum BoType {
        BOOKING_CUSTOMER, EMPLOYEE, STATION, TRAIN, TRAIN_SCHEDULE
    }

}
