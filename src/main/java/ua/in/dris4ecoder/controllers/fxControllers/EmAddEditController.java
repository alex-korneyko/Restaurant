package ua.in.dris4ecoder.controllers.fxControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.gui.customControls.StaffControllerTab;
import ua.in.dris4ecoder.model.businessObjects.BusinessObject;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 09.08.2016 12:36.
 */
public class EmAddEditController implements EditController {


    public TextField textFieldLastName;
    public TextField textFieldFirstName;
    public ComboBox comboBoxEmployeePost;
    public TextField textFieldPhone;
    public TextField textFieldSalary;
    public DatePicker datePickerDayOfBirth;

    private ObservableList<String> employeePosts = FXCollections.observableArrayList();
    private StaffControllerTab owner;

    Employee employee;
    private ObservableList<BusinessObject> observableList;

    @Override
    public void saveAction(ActionEvent actionEvent) {

        if(employee == null) {
            employee = new Employee();
            fillEmployee();
            Main.getStaffController().addEmployee(employee);
            observableList.add(employee);
        } else {
            fillEmployee();
            Main.getStaffController().editEmployee(employee.getId(), employee);
        }

        closeAction(actionEvent);
    }

    private void fillEmployee() {
        employee.setFirstName(textFieldFirstName.getText());
        employee.setLastName(textFieldLastName.getText());
        employee.setEmployeePost(Main.getStaffController().findEmployeePost(comboBoxEmployeePost.getValue().toString()).get(0));
        employee.setTelephone(textFieldPhone.getText());
        employee.setSalary(Double.parseDouble(textFieldSalary.getText()));
        employee.setDateOfBirth(datePickerDayOfBirth.getValue());
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void setEmployee(Employee selectedItem) {

        employeePosts.addAll(Main.getStaffController().getAllEmployeePosts().stream().map(EmployeePost::getPostName).collect(Collectors.toList()));

        comboBoxEmployeePost.getItems().clear();
        comboBoxEmployeePost.setItems(employeePosts);

        if (selectedItem != null) {
            textFieldLastName.setText(selectedItem.getLastName());
            textFieldFirstName.setText(selectedItem.getFirstName());
            comboBoxEmployeePost.setValue(selectedItem.getEmployeePost().getPostName());
            textFieldPhone.setText(selectedItem.getTelephone());
            textFieldSalary.setText(String.valueOf(selectedItem.getSalary()));
            datePickerDayOfBirth.setValue(selectedItem.getDateOfBirth());
        }

        employee = selectedItem;
    }

    public void setOwner(StaffControllerTab owner) {
        this.owner = owner;
    }

    public void addNewEmPost(ActionEvent actionEvent) throws IOException {
        System.out.println(owner.getId());
        owner.setId("posts");
        owner.addAction(actionEvent);
        owner.setId("employees");
        employeePosts.clear();
        employeePosts.addAll(Main.getStaffController().getAllEmployeePosts().stream().map(EmployeePost::getPostName).collect(Collectors.toList()));
        comboBoxEmployeePost.setValue(employeePosts.get(employeePosts.size() - 1));
    }

    public void setObservableList(ObservableList observableList) {
        this.observableList = observableList;
    }

}
