package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddEmployeeFromController {
    public Button btnAddEmployee;
    public TextField txtEmpName;
    public TextField txtEmpId;
    public TextField txtEmpAddress;
    public TextField txtEmpTel;
    public TextField txtEmpAge;
    public TextField txtEmpSalary;
    public Button btnBack;
    public AnchorPane employeeAnchorPane;
    public TableView tblEmployee;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colEmpAddress;
    public TableColumn colEmpAge;
    public TableColumn colEmpTel;
    public TableColumn colEmpSalary;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        btnAddEmployee.setDisable(true);

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

        try {
            loadAllEmployee();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllEmployee() throws ClassNotFoundException, SQLException {

    }


    public void btnAddEmployeeOnAction() {

    }

    public void textFields_Key_Releaseed(KeyEvent keyEvent) {
        Validation();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object responds = Validation();

            if (responds instanceof TextField) {
                TextField textField = (TextField) responds;
                textField.requestFocus();
            } else {
                btnAddEmployeeOnAction();
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

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        employeeAnchorPane.getChildren().clear();
    }

    public void addError(TextField txtCus) {
        if (txtCus.getText().length() > 0) {
            txtCus.getParent().setStyle("-fx-border-color: red");
        }
        btnAddEmployee.setDisable(true);
    }

    public void removeError(TextField txtCus) {
        txtCus.getParent().setStyle("-fx-border-color: green");
        btnAddEmployee.setDisable(false);
    }

    public void SearchOnAction(ActionEvent actionEvent) {

    }

    public void btnEmployeeReportOnAction(ActionEvent actionEvent) {

        String EmployeeID = txtEmpId.getText();
        String EmployeeName = txtEmpName.getText();
        String EmployeeAddress = txtEmpAddress.getText();
        String EmployeeAge=txtEmpAge.getText();
        String EmployeeContact=txtEmpTel.getText();
        String EmployeeSalary=txtEmpSalary.getText();

        HashMap map=new HashMap();

        map.put("id",EmployeeID);
        map.put("name",EmployeeName);
        map.put("address",EmployeeAddress);
        map.put("age",EmployeeAge);
        map.put("contact",EmployeeContact);
        map.put("salary",EmployeeSalary);

        try {
            JasperDesign load= JRXmlLoader.load(this.getClass().getResourceAsStream("/views/reports/EmployeeReport.jrxml")) ;
            JasperReport compileReport = JasperCompileManager.compileReport(load);
           // JasperReport compileReport= (JasperReport) JRLoader.loadObject(this.getClass().getResource("/views/reports/EmployeeReport.jasper"));
            JasperPrint jasperPrint= JasperFillManager.fillReport(compileReport,map, new JREmptyDataSource(1));
            JasperViewer.viewReport(jasperPrint,false);

        }catch (JRException e){
            e.printStackTrace();
        }
    }

    public void btnclearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void clear() {
        txtEmpId.clear();
        txtEmpName.clear();
        txtEmpAddress.clear();
        txtEmpAge.clear();
        txtEmpTel.clear();
        txtEmpSalary.clear();
    }

}
