package controller;

import bo.BOFactory;
import bo.SuperBO;
import bo.custom.StationBO;
import bo.custom.TrainSchedulBO;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.StationDTO;
import model.TrainSchedulCheckDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;
import java.util.ArrayList;

public class CheckTrainScheduleFromController {
    public Button btnSearch;
    public TableColumn tblTrainTo;
    public TableColumn tblTrainFrom;
    public TableColumn tblTrainName;
    public Button btnBack;
    public TableColumn tblTrainId;
    public AnchorPane ScheduleAnchorPane;
    public ComboBox cmbTrainTo;
    public ComboBox cmbTrainFrom;
    public TableView tblTrainLoad;
    public TableColumn tblTrainStartTime;
    public TableColumn tblTrainStopTime;
    public TableColumn tblTrainStartStation;
    public TableColumn tblTrainEndStation;
    public TableColumn tblEndTime;
    private final TrainSchedulBO trainSchedulBO= (TrainSchedulBO) BOFactory.getBoFactory().getBOType(BOFactory.BoType.TRAIN_SCHEDULE);
    private final StationBO stationBO = (StationBO) BOFactory.getBoFactory().getBOType(BOFactory.BoType.STATION);


    public void initialize() {
        uploadComboBox();

        tblTrainFrom.setCellValueFactory(new PropertyValueFactory("From"));
        tblTrainTo.setCellValueFactory(new PropertyValueFactory("To"));
        tblTrainId.setCellValueFactory(new PropertyValueFactory("TrainId"));
        tblTrainName.setCellValueFactory(new PropertyValueFactory("TrainName"));
        tblTrainStartTime.setCellValueFactory(new PropertyValueFactory("StartTrainTime"));
        tblEndTime.setCellValueFactory(new PropertyValueFactory("EndStationTime"));
        tblTrainStopTime.setCellValueFactory(new PropertyValueFactory("TrainStopTime"));
        tblTrainStartStation.setCellValueFactory(new PropertyValueFactory("TrainStartStation"));
        tblTrainEndStation.setCellValueFactory(new PropertyValueFactory("TrainEndStation"));

          //  loadAllTrain();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        tblTrainLoad.getItems().clear();
        String from = (String) cmbTrainFrom.getValue();
        String to = (String) cmbTrainTo.getValue();

        try {
            ArrayList<TrainSchedulCheckDTO> trainSchedul = trainSchedulBO.TrainSchedulCheck(from, to);
            for (TrainSchedulCheckDTO train :trainSchedul) {
                tblTrainLoad.getItems().add(
                  new TrainSchedulCheckDTO(
                          train.getFrom(),
                          train.getTo(),
                          train.getTrainId(),
                          train.getTrainName(),
                          train.getStartTrainTime(),
                          train.getEndStationTime(),
                          train.getTrainStopTime(),
                          train.getTrainStartStation(),
                          train.getTrainEndStation()
                  ));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        ScheduleAnchorPane.getChildren().clear();
    }

    private void loadAllTrain() throws SQLException, ClassNotFoundException {
//        ResultSet result = CrudUtil.executeQuery("SELECT * FROM stationSchedule");
//        ObservableList<TrainSchedulCheck> obList = FXCollections.observableArrayList();
//        while (result.next()) {
//            obList.add(
//                    new TrainSchedulCheck(
//                            result.getString("cusFrom"),
//                            result.getString("cusTo"),
//                            result.getString("TrainId"),
//                            result.getString("TrainName"),
//                            result.getString("StartStationTime"),
//                            result.getString("EndStationTime"),
//                            result.getString("TrainStopTime"),
//                            result.getString("TrainStartStation"),
//                            result.getString("TrainStopStation")
//                    ));
//        }
//        tblTrainLoad.setItems(obList);
    }

    public void uploadComboBox() {
        comboFrom();
        comboTo();
    }

    public void comboFrom() {

        try {
            ArrayList<StationDTO> allStation = stationBO.getAllStation();
            ObservableList obList = FXCollections.observableArrayList();
            for (StationDTO station :allStation) {
                obList.add(new String(station.getName()));
            }
            cmbTrainFrom.setItems(obList);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void comboTo() {
        try {
            ArrayList<StationDTO> allStation = stationBO.getAllStation();
            ObservableList obList = FXCollections.observableArrayList();
            for (StationDTO station :allStation) {
                obList.add(new String(station.getName()));
            }
            cmbTrainTo.setItems(obList);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnPrintOnAction(ActionEvent actionEvent)throws JRException {
        try {

            JasperDesign load= JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/TrainScheduleReport.jrxml")) ;
            JasperReport compileReport = JasperCompileManager.compileReport(load);

            //JasperReport compileReport=(JasperReport) JRLoader.loadObject(this.getClass().getResource("/views/reports/TrainScheduleReport.jasper"));
            Connection connection= DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint= JasperFillManager.fillReport(compileReport,null,connection);
            JasperViewer.viewReport(jasperPrint,false);
        }catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
