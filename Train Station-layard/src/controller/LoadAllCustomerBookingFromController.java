package controller;

import bo.BOFactory;
import bo.custom.BookingCustomerBO;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.BookingCustomerDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadAllCustomerBookingFromController {

    private final BookingCustomerBO bookingCustomerBO = (BookingCustomerBO) BOFactory.getBoFactory().getBOType(BOFactory.BoType.BOOKING_CUSTOMER);
    public Button btnBack;
    public TableView tblCustomerBooking;
    public TableColumn colCusAddress;
    public TableColumn colCusId;
    public TableColumn colCusName;
    public TableColumn colCusTel;
    public TableColumn colCusFrom;
    public TableColumn colCusTo;
    public TableColumn colCusTime;
    public TableColumn colCusTrain;
    public TableColumn colCusSeatNo;
    public TableColumn colCusClass;
    public TableColumn colCusPrice;
    public AnchorPane customerAnchorPane;
    public TableColumn colDate;

    public void initialize() {
        colCusId.setCellValueFactory(new PropertyValueFactory("id"));
        colCusName.setCellValueFactory(new PropertyValueFactory("name"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colCusTel.setCellValueFactory(new PropertyValueFactory("contact"));
        colCusFrom.setCellValueFactory(new PropertyValueFactory("trainFrom"));
        colCusTo.setCellValueFactory(new PropertyValueFactory("trainTo"));
        colCusTime.setCellValueFactory(new PropertyValueFactory("time"));
        colCusTrain.setCellValueFactory(new PropertyValueFactory("train"));
        colCusSeatNo.setCellValueFactory(new PropertyValueFactory("seatNo"));
        colCusClass.setCellValueFactory(new PropertyValueFactory("class"));
        colCusPrice.setCellValueFactory(new PropertyValueFactory("price"));
        colDate.setCellValueFactory(new PropertyValueFactory("Date"));

        loadAllBooking();
    }

    private void loadAllBooking() {

        try {
            ArrayList<BookingCustomerDTO> allCustomerBooking = bookingCustomerBO.getAllCustomerBooking();
            for (BookingCustomerDTO customer : allCustomerBooking) {
                tblCustomerBooking.getItems().add(
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
        customerAnchorPane.getChildren().clear();
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        try {

            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/AllCustomerReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(load);

            //JasperReport compileReport=(JasperReport) JRLoader.loadObject(this.getClass().getResource("/views/reports/TrainScheduleReport.jasper"));
            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, connection);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
