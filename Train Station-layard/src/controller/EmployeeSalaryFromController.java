package controller;

import bo.BOFactory;
import bo.custom.EmployeeBO;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.EmployeeDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeSalaryFromController {
    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBOType(BOFactory.BoType.EMPLOYEE);
    public Button btnBack;
    public TableColumn tblEmpId;
    public TableColumn tblEmpName;
    public TableColumn tblEmpAddress;
    public TableColumn tblEmpAge;
    public TableColumn tblEmpTel;
    public TableColumn tblEmpSalary;
    public Button btnUpdate;
    public TableView tblEmployee;
    public AnchorPane salaryAnchorPane;

    public void initialize() {
        tblEmpId.setCellValueFactory(new PropertyValueFactory("id"));
        tblEmpName.setCellValueFactory(new PropertyValueFactory("name"));
        tblEmpAddress.setCellValueFactory(new PropertyValueFactory("address"));
        tblEmpAge.setCellValueFactory(new PropertyValueFactory("age"));
        tblEmpTel.setCellValueFactory(new PropertyValueFactory("contact"));
        tblEmpSalary.setCellValueFactory(new PropertyValueFactory("salary"));

        loadAllEmployee();
    }

    private void loadAllEmployee() {
        try {
            ArrayList<EmployeeDTO> allEmployee = employeeBO.getAllEmployee();
            for (EmployeeDTO employee : allEmployee) {
                tblEmployee.getItems().add(
                        new EmployeeDTO(
                                employee.getId(),
                                employee.getName(),
                                employee.getAddress(),
                                employee.getAge(),
                                employee.getContact(),
                                employee.getSalary()
                        ));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/ModifyEmployeeFrom.fxml"));
        salaryAnchorPane.getChildren().setAll(pane);
    }


    public void btnBackOnAction(ActionEvent actionEvent) {
        salaryAnchorPane.getChildren().clear();
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/EmployeeSalaryReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(load);

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
