package controller;

import bo.BOFactory;
import bo.custom.BookingCustomerBO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.BookingCustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class IncomeReportFromController {
    public Button btnBack;
    public TableColumn tblIncomeDate;
    public TableColumn tblIncome;
    public TableColumn tblOption;
    public TextField txtMonthlyIncome;
    public AnchorPane incomeAnchorPane;
    public TableView<BookingCustomerDTO> tblReport;
    private final BookingCustomerBO bookingCustomerBO = (BookingCustomerBO) BOFactory.getBoFactory().getBOType(BOFactory.BoType.BOOKING_CUSTOMER);

    public void initialize(){
        tblIncomeDate.setCellValueFactory(new PropertyValueFactory("date"));
        tblIncome.setCellValueFactory(new PropertyValueFactory("price"));
        tblOption.setCellValueFactory((param) -> {
            ImageView edit = new ImageView("/assets/draw.png");
            ImageView delete = new ImageView("/assets/trash.png");
            return new ReadOnlyObjectWrapper<>(new HBox(100, edit, delete));
        });
        loadAllIncome();
    }

    private void loadAllIncome(){

        try {
            ArrayList<BookingCustomerDTO> allCustomerBooking = bookingCustomerBO.getAllCustomerBooking();
            for (BookingCustomerDTO customer :allCustomerBooking) {
                tblReport.getItems().add(
                  new BookingCustomerDTO(
                          customer.getId(),
                          customer.getName(),
                          customer.getAddress(),
                          customer.getContact(),
                          customer.getTrainFrom(),
                          customer.getTrainTo(),
                          customer.getTime(),
                          customer.getTrain(),
                          customer.getSeatNo(),
                          customer.getTrainClass(),
                          customer.getPrice(),
                          customer.getDate()
                  ));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        incomeAnchorPane.getChildren().clear();
    }
}
