package controller;

import dao.custom.BookingCustomerDAO;
import dao.custom.StationDAO;
import dao.custom.TrainDAO;
import dao.custom.impl.BookingCustomerDAOImpl;
import dao.custom.impl.StationDAOImpl;
import dao.custom.impl.TrainDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import model.BookingCustomerDTO;
import model.StationDTO;
import model.TrainDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;


public class AddBookingCustomerFromController {
    private final BookingCustomerDAO bookingCustomerDAO = new BookingCustomerDAOImpl();
    private final TrainDAO trainDAO = new TrainDAOImpl();
    private final StationDAO stationDAO = new StationDAOImpl();

    public Button btnBooking;
    public TextField txtCusName;
    public TextField txtCusId;
    public TextField txtCusAddress;
    public TextField txtCusContact;
    public ComboBox cmbCusFrom;
    public ComboBox cmbCusTo;
    public ComboBox cmbCusTrain;
    public TextField txtCusPrice;
    public ComboBox cmbCusSeatNo;
    public TextField txtTrainTime;
    public ComboBox cmbCusClass;
    public TableView tblCustomerBooking;
    public TableColumn colCusId;
    public TableColumn colCusName;
    public TableColumn colCusAddress;
    public TableColumn colCusTel;
    public TableColumn colCusFrom;
    public TableColumn colCusTo;
    public TableColumn colCusTime;
    public TableColumn colCusTrain;
    public TableColumn colCusSeatNo;
    public TableColumn colCusClass;
    public TableColumn colCusPrice;
    public TextField txtCusBookDate;
    public TableColumn colDate;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        uploadComboBox();
        btnBooking.setDisable(true);

        Pattern patternId = Pattern.compile("^(C00-)[0-9]{3,5}$");
        Pattern patternName = Pattern.compile("^[A-z ]{3,}$");
        Pattern patternAddress = Pattern.compile("^[A-z0-9 ,/]{5,}$");
        Pattern patternContact = Pattern.compile("^(071|072|077|076|078|075)[0-9]{7}$");
        Pattern patternTime = Pattern.compile("^([01]?[0-9]|2[0-3]).[0-5][0-9]$");
        Pattern patternPrice = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");

        map.put(txtCusId, patternId);
        map.put(txtCusName, patternName);
        map.put(txtCusAddress, patternAddress);
        map.put(txtCusContact, patternContact);
        map.put(txtTrainTime, patternTime);
        map.put(txtCusPrice, patternPrice);

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
        colDate.setCellValueFactory(new PropertyValueFactory("date"));


        loadAllBooking();
    }

    public void textFields_Key_Releaseed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        validate();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object responds = validate();

            if (responds instanceof TextField) {
                TextField textField = (TextField) responds;
                textField.requestFocus();
            } else {
               //========================
                boolean exist = bookingCustomerDAO.exist(txtCusId.getText());
                if (exist) {

                } else {
                    btnBookingOnAction();
                }
            }
        }
    }

    private Object validate() {
        for (TextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if (!pattern.matcher(key.getText()).matches()) {
                addError(key);
                return key;
            } else
                removeError(key);
        }
        return true;
    }

    public void removeError(TextField text) {
        text.getParent().setStyle("-fx-border-color: green");
        btnBooking.setDisable(false);
    }

    public void addError(TextField text) {
        if (text.getText().length() > 0) {
            text.getParent().setStyle("-fx-border-color: red");
        }
        btnBooking.setDisable(true);
    }


    public void btnBookingOnAction() {
        try {
            //========================
            String customerID = txtCusId.getText();
            String customerName = txtCusName.getText();
            String customerAddress = txtCusAddress.getText();
            String customerContact = txtCusContact.getText();
            Object trainFrom = cmbCusFrom.getValue();
            Object trainTo = cmbCusTo.getValue();
            String time = txtTrainTime.getText();
            Object train = cmbCusTrain.getValue();
            Object seatNo = cmbCusSeatNo.getValue();
            Object trainClass = cmbCusClass.getValue();
            String price = txtCusPrice.getText();
            String date = txtCusBookDate.getText();

            boolean exist = bookingCustomerDAO.exist(customerID);
            if (exist) {
                new Alert(Alert.AlertType.ERROR, "All Ready Add ID!").show();
            } else {
                btnBooking.setVisible(true);
                boolean save = bookingCustomerDAO.Save(new BookingCustomerDTO(customerID, customerName, customerAddress,
                        customerContact, trainFrom, trainTo, time, train, seatNo, trainClass, price, date));
                if (save) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Save Booking !").show();
                    loadAllBooking();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Wrong !").show();
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        String customerID = txtCusId.getText();
        String customerName = txtCusName.getText();
        String customerAddress = txtCusAddress.getText();
        String customerContact = txtCusContact.getText();
        String trainFrom = (String) cmbCusFrom.getValue();
        String trainTo = (String) cmbCusTo.getValue();
        String time = txtTrainTime.getText();
        String train = (String) cmbCusTrain.getValue();
        String seatNo = (String) cmbCusSeatNo.getValue();
        String trainClass = (String) cmbCusClass.getValue();
        String price = txtCusPrice.getText();

        HashMap map = new HashMap();

        map.put("id", customerID);
        map.put("name", customerName);
        map.put("address", customerAddress);
        map.put("contact", customerContact);
        map.put("trainFrom", trainFrom);
        map.put("trainTo", trainTo);
        map.put("time", time);
        map.put("train", train);
        map.put("seatNo", seatNo);
        map.put("trainClass", trainClass);
        map.put("price", price);

        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/BookingReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            //  JasperReport compileReport= (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/reports/BookingReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JREmptyDataSource(1));
            JasperViewer.viewReport(jasperPrint, false);


        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void uploadComboBox() {
        try {
            comboFrom();
            comboTo();
            comboTrain();
            comboSeatNo();
            comboClass();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void comboFrom() throws SQLException, ClassNotFoundException {
        ArrayList<StationDTO> all = stationDAO.getAll();
        ObservableList obList = FXCollections.observableArrayList();
        for (StationDTO station :all) {
            obList.add(new String(station.getName()));
        }
        cmbCusFrom.setItems(obList);
    }

    public void comboTo() throws SQLException, ClassNotFoundException {
        ArrayList<StationDTO> all = stationDAO.getAll();
        ObservableList<Object> obList = FXCollections.observableArrayList();
        for (StationDTO station :all) {
            obList.add(new String(station.getName()));
        }
        cmbCusTo.setItems(obList);
    }

    //from to train search karanna one
    public void comboTrain() {
        Object value1 = cmbCusTo.getValue();
        Object value2 = cmbCusFrom.getValue();

            try {
                if (value1!=null && value2!=null){

//                    cmbCusTrain.getItems().clear();
//                    cmbCusTo.getValue();
//                    System.out.println("from : "+cmbCusFrom.getValue());
//                    System.out.println("To : "+cmbCusTo.getValue());
                    // method ekak liyala sql liyanna one

                }else {
                    cmbCusTrain.getItems().clear();
                    ArrayList<TrainDTO> all = trainDAO.getAll();
                    ObservableList obList = FXCollections.observableArrayList();
                    for (TrainDTO dto :all) {
                        obList.add(new String(dto.getTrainName()));
                    }
                    cmbCusTrain.setItems(obList);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    //available check karanna one
    public void comboSeatNo() {

    }

    public void comboClass() {
        ObservableList obList = FXCollections.observableArrayList();
        obList.add("1");
        obList.add("2");
        obList.add("3");
        cmbCusClass.setItems(obList);
    }

    private void loadAllBooking() {
        tblCustomerBooking.getItems().clear();
        try {
            //========================
            ArrayList<BookingCustomerDTO> all = bookingCustomerDAO.getAll();
            for (BookingCustomerDTO dto : all) {
                tblCustomerBooking.getItems().add(
                        new BookingCustomerDTO(
                                dto.getId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getContact(),
                                dto.getTrainFrom(),
                                dto.getTrainTo(),
                                dto.getTime(),
                                dto.getTrain(),
                                dto.getSeatNo(),
                                dto.getTrainClass(),
                                dto.getPrice(),
                                dto.getDate()));

            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void clear() {
        txtCusId.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusContact.clear();
        txtTrainTime.clear();
        txtCusPrice.clear();
        cmbCusFrom.getSelectionModel().clearSelection();
        cmbCusTo.getSelectionModel().clearSelection();
        cmbCusTrain.getSelectionModel().clearSelection();
        cmbCusSeatNo.getSelectionModel().clearSelection();
        cmbCusClass.getSelectionModel().clearSelection();
        txtCusBookDate.clear();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {

        try {
            //========================
            BookingCustomerDTO search = bookingCustomerDAO.search(txtCusId.getText());
            if (search != null) {
                txtCusId.setText(search.getId());
                txtCusName.setText(search.getName());
                txtCusAddress.setText(search.getAddress());
                txtCusContact.setText(search.getContact());
                txtTrainTime.setText(search.getTime());
                txtCusPrice.setText(search.getPrice());
                cmbCusFrom.setValue(search.getTrainFrom());
                cmbCusTo.setValue(search.getTrainTo());
                cmbCusTrain.setValue(search.getTrain());
                cmbCusSeatNo.setValue(search.getSeatNo());
                cmbCusClass.setValue(search.getTrainClass());
                txtCusBookDate.setText(search.getDate());
            } else {
                new Alert(Alert.AlertType.ERROR, "Empty Result..!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cmbCusFromOnAction(ActionEvent actionEvent) {
        comboTrain();
    }

    public void cmbCusToOnAction(ActionEvent actionEvent) {
        comboTrain();
    }
}
