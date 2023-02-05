package controller;

import dao.custom.StationDAO;
import dao.custom.TrainDAO;
import dao.custom.impl.StationDAOImpl;
import dao.custom.impl.TrainDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

public class AddTrainFromController {
    private final TrainDAO trainDAO = new TrainDAOImpl();
    private final StationDAO stationDAO = new StationDAOImpl();
    public Button btnBack;
    public ComboBox cmbTrainTo;
    public ComboBox cmbTrainFrom;
    public TextField txtTrainId;
    public TextField txtTrainName;
    public Button btnAddTrain;
    public TextField txtStartTime;
    public TextField txtEndTime;
    public TableView tblAllTrain;
    public TableColumn colTrainTo;
    public TableColumn colTrainFrom;
    public TableColumn colTrainId;
    public TableColumn colTrainName;
    public TableColumn colTrainStartTime;
    public TableColumn colTrainEndTime;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {

        trainFrom();
        trainTo();

        btnAddTrain.setDisable(true);

        Pattern pattenId = Pattern.compile("^(T00-)[0-9]{3,5}$");
        Pattern pattenName = Pattern.compile("^[A-z ]{3,}$");
        Pattern patternStartTime = Pattern.compile("^([01]?[0-9]|2[0-3]).[0-5][0-9]$");
        Pattern pattenEndTime = Pattern.compile("^([01]?[0-9]|2[0-3]).[0-5][0-9]$");

        map.put(txtTrainId, pattenId);
        map.put(txtTrainName, pattenName);
        map.put(txtStartTime, patternStartTime);
        map.put(txtEndTime, pattenEndTime);

        colTrainId.setCellValueFactory(new PropertyValueFactory("trainId"));
        colTrainName.setCellValueFactory(new PropertyValueFactory("trainName"));
        colTrainStartTime.setCellValueFactory(new PropertyValueFactory("startTime"));
        colTrainEndTime.setCellValueFactory(new PropertyValueFactory("endTime"));
        colTrainFrom.setCellValueFactory(new PropertyValueFactory("trainFrom"));
        colTrainTo.setCellValueFactory(new PropertyValueFactory("trainTo"));


        loadAllTrain();
    }


    //============================
    private void loadAllTrain() {
        try {
            //============================
            tblAllTrain.getItems().clear();
            ArrayList<TrainDTO> all = trainDAO.getAll();

            for (TrainDTO train : all) {
                tblAllTrain.getItems().add(
                        new TrainDTO(
                                train.getTrainId(),
                                train.getTrainName(),
                                train.getStartTime(),
                                train.getEndTime(),
                                train.getTrainFrom(),
                                train.getTrainTo()
                        ));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    //============================
    public void textFields_Key_Released(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        Validation();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object responds = Validation();

            if (responds instanceof TextField) {
                TextField textField = (TextField) responds;
                textField.requestFocus();
            } else {
                boolean exist = trainDAO.exist(txtTrainId.getText());
                if (exist) {

                } else {
                    btnAddTrainOnAction();
                }
            }
        }
    }


    private Object Validation() {
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

    private void removeError(TextField text) {
        text.getParent().setStyle("-fx-border-color: green");
        btnAddTrain.setDisable(false);
    }

    private void addError(TextField text) {
        if (text.getText().length() > 0) {
            text.getParent().setStyle("-fx-border-color: red");
        }
        btnAddTrain.setDisable(true);
    }

    public void btnAddTrainOnAction() {

        try {
            boolean exist = trainDAO.exist(txtTrainId.getText());
            if (exist) {
                new Alert(Alert.AlertType.CONFIRMATION, "Duplicate Train ID..!").show();
            } else {
                boolean save = trainDAO.Save(new TrainDTO(
                        txtTrainId.getText(),
                        txtTrainName.getText(),
                        txtStartTime.getText(),
                        txtEndTime.getText(),
                        cmbTrainFrom.getValue(),
                        cmbTrainTo.getValue()
                ));

                if (save) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Add New Train..!").show();
                    loadAllTrain();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        btnAddTrain.setDisable(true);
    }

    public void trainFrom() {
        try {
            ArrayList<StationDTO> all = stationDAO.getAll();
            ObservableList obList = FXCollections.observableArrayList();
            for (StationDTO station : all) {
                obList.add(new String(station.getName()));
            }
            cmbTrainFrom.setItems(obList);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void trainTo() {
//        try {
//            ResultSet result = CrudUtil.executeQuery("SELECT * FROM station ORDER BY name ASC");
//            ObservableList obList = FXCollections.observableArrayList();
//            while (result.next()) {
//                obList.add(result.getString(2));
//            }
//            cmbTrainTo.setItems(obList);
//
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public void clear() {
        txtTrainId.clear();
        txtTrainName.clear();
        txtEndTime.clear();
        txtStartTime.clear();
        cmbTrainFrom.getSelectionModel().clearSelection();
        cmbTrainTo.getSelectionModel().clearSelection();
    }


    //============================
    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            TrainDTO search = trainDAO.search(txtTrainId.getText());
            if (search != null) {
                txtTrainName.setText(search.getTrainName());
                txtStartTime.setText(search.getStartTime());
                txtEndTime.setText(search.getEndTime());
                cmbTrainFrom.setValue(search.getTrainFrom());
                cmbTrainTo.setValue(search.getTrainTo());
            } else {
                new Alert(Alert.AlertType.ERROR, "Empty Result..!").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnclearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void btnEmployeeReportOnAction(ActionEvent actionEvent) {
        String trainID = txtTrainId.getText();
        String trainName = txtTrainName.getText();
        String startTime = txtStartTime.getText();
        String endTime = txtEndTime.getText();
        String from = (String) cmbTrainFrom.getValue();
        String to = (String) cmbTrainTo.getValue();

        HashMap map = new HashMap();

        map.put("trainId", trainID);
        map.put("trainName", trainName);
        map.put("startTime", startTime);
        map.put("EndTime", endTime);
        map.put("trainFrom", from);
        map.put("trainTo", to);

        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/TrainReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            // JasperReport compileReport= (JasperReport) JRLoader.loadObject(this.getClass().getResource("/views/reports/TrainReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JREmptyDataSource(1));
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
