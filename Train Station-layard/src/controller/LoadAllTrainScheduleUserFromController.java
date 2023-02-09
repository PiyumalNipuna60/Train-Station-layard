package controller;

import bo.BOFactory;
import bo.SuperBO;
import bo.custom.TrainBO;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.TrainDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadAllTrainScheduleUserFromController {

    public TableColumn colTrainTo;
    public TableColumn colTrainFrom;
    public TableColumn colTrainId;
    public TableColumn colTrainName;
    public TableColumn colTrainStartTime;
    public TableColumn colTrainEndTime;
    public TableView tblAllTrain;
    public AnchorPane AllEmployeeAnchorPane;

    public void initialize() {

        colTrainId.setCellValueFactory(new PropertyValueFactory("trainId"));
        colTrainName.setCellValueFactory(new PropertyValueFactory("trainName"));
        colTrainStartTime.setCellValueFactory(new PropertyValueFactory("startTime"));
        colTrainEndTime.setCellValueFactory(new PropertyValueFactory("endTime"));
        colTrainFrom.setCellValueFactory(new PropertyValueFactory("trainFrom"));
        colTrainTo.setCellValueFactory(new PropertyValueFactory("trainTo"));

        loadAllTrain();
    }

    private void loadAllTrain() {

        TrainBO trainBO = (TrainBO) BOFactory.getBoFactory().getBOType(BOFactory.BoType.TRAIN);
        try {
            ArrayList<TrainDTO> allTrain = trainBO.getAllTrain();
            for (TrainDTO train :allTrain) {
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
//        ResultSet result = CrudUtil.executeQuery("SELECT * FROM train");
//        ObservableList<Train> obList = FXCollections.observableArrayList();
//        while (result.next()) {
//            obList.add(
//                    new Train(
//                            result.getString("trainId"),
//                            result.getString("trainName"),
//                            result.getString("startTime"),
//                            result.getString("endTime"),
//                            result.getString("trainFrom"),
//                            result.getString("trainTo")
//                    )
//            );
//        }
//        tblAllTrain.setItems(obList);
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        AllEmployeeAnchorPane.getChildren().clear();
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/AllTrainScheduleReport.jrxml"));
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
