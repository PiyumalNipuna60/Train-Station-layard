package controller;

import bo.BOFactory;
import bo.custom.EmployeeBO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.EmployeeDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ModifyEmployeeFromController {
    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBOType(BOFactory.BoType.EMPLOYEE);
    public Button btnDeleteEmployee1;
    public Button btnBack;
    public TextField txtEmpSalary;
    public TextField txtEmpAge;
    public TextField txtEmpTel;
    public TextField txtEmpAddress;
    public TextField txtEmpId;
    public TextField txtEmpName;
    public Button btnUpdateEmployee;
    public TableView tblEmployee;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colEmpAddress;
    public TableColumn colEmpAge;
    public TableColumn colEmpTel;
    public TableColumn colEmpSalary;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        btnUpdateEmployee.setDisable(true);
        btnDeleteEmployee1.setDisable(true);

        Pattern pattenId = Pattern.compile("^(E00-)[0-9]{3,5}$");
        Pattern pattenName = Pattern.compile("^[A-z ]{3,}$");
        Pattern pattenAddress = Pattern.compile("^[A-z0-9 ,/]{5,}$");
        Pattern pattenAge = Pattern.compile("^[0-9]{2}$");
        Pattern pattenTel = Pattern.compile("^(071|072|077|076|078|075)[0-9]{7}$");
        Pattern pattenSalary = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");

        map.put(txtEmpId, pattenId);
        map.put(txtEmpName, pattenName);
        map.put(txtEmpAddress, pattenAddress);
        map.put(txtEmpAge, pattenAge);
        map.put(txtEmpTel, pattenTel);
        map.put(txtEmpSalary, pattenSalary);


        colEmpId.setCellValueFactory(new PropertyValueFactory("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory("name"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colEmpAge.setCellValueFactory(new PropertyValueFactory("age"));
        colEmpTel.setCellValueFactory(new PropertyValueFactory("contact"));
        colEmpSalary.setCellValueFactory(new PropertyValueFactory("salary"));

        loadAllEmployee();
    }

    private void loadAllEmployee() {
        tblEmployee.getItems().clear();
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


    public void txtSearchOnAction(ActionEvent actionEvent) {
        tblEmployee.getItems().clear();
        try {
            EmployeeDTO search = employeeBO.searchEmployee(txtEmpId.getText());
            if (search != null) {
                txtEmpName.setText(search.getName());
                txtEmpAddress.setText(search.getAddress());
                txtEmpAge.setText(search.getAge());
                txtEmpTel.setText(search.getContact());
                txtEmpSalary.setText(search.getSalary());

                tblEmployee.getItems().add(
                        new EmployeeDTO(
                                search.getId(),
                                search.getName(),
                                search.getAddress(),
                                search.getAge(),
                                search.getContact(),
                                search.getSalary()
                        ));
            } else {
                new Alert(Alert.AlertType.ERROR, "Empty Result..!").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        boolean b = employeeBO.updateEmployee(new EmployeeDTO(
                txtEmpId.getText(),
                txtEmpName.getText(),
                txtEmpAddress.getText(),
                txtEmpAge.getText(),
                txtEmpTel.getText(),
                txtEmpSalary.getText()
        ));
        if (b) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            clear();
            loadAllEmployee();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            clear();
            loadAllEmployee();
        }
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        boolean b = employeeBO.deleteEmployee(txtEmpId.getText());
        if (b) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            loadAllEmployee();
            clear();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            loadAllEmployee();
            clear();
        }
    }

    public void textFields_Key_Releaseed(KeyEvent keyEvent) {
        Validation();

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

    public void addError(TextField txtCus) {
        if (txtCus.getText().length() > 0) {
            txtCus.getParent().setStyle("-fx-border-color: red");
        }
        btnUpdateEmployee.setDisable(true);
    }

    public void removeError(TextField txtCus) {
        txtCus.getParent().setStyle("-fx-border-color: green");
        btnUpdateEmployee.setDisable(false);
        btnDeleteEmployee1.setDisable(false);
    }

    public void clear() {
        txtEmpId.clear();
        txtEmpName.clear();
        txtEmpAddress.clear();
        txtEmpAge.clear();
        txtEmpTel.clear();
        txtEmpSalary.clear();
    }

    public void btnclearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void btnEmployeeReportOnAction(ActionEvent actionEvent) {
        String EmployeeID = txtEmpId.getText();
        String EmployeeName = txtEmpName.getText();
        String EmployeeAddress = txtEmpAddress.getText();
        String EmployeeAge = txtEmpAge.getText();
        String EmployeeContact = txtEmpTel.getText();
        String EmployeeSalary = txtEmpSalary.getText();

        HashMap map = new HashMap();

        map.put("id", EmployeeID);
        map.put("name", EmployeeName);
        map.put("address", EmployeeAddress);
        map.put("age", EmployeeAge);
        map.put("contact", EmployeeContact);
        map.put("salary", EmployeeSalary);

        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/EmployeeReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            // JasperReport compileReport= (JasperReport) JRLoader.loadObject(this.getClass().getResource("/views/reports/EmployeeReport.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JREmptyDataSource(1));
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
