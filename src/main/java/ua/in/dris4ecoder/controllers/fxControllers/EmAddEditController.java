package ua.in.dris4ecoder.controllers.fxControllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Employee;

/**
 * Created by Alex Korneyko on 09.08.2016 12:36.
 */
public class EmAddEditController implements EditController {


    public TextField textFieldLastName;
    public TextField textFieldFirstName;
    public ComboBox ComboBoxEmployeePost;
    public TextField textFieldPhone;
    public TextField textFieldSalary;
    public DatePicker datePickerDayOfBirth;

    Employee employee;

    @Override
    public void saveAction(ActionEvent actionEvent) {

        employee.setFirstName(textFieldFirstName.getText());
        employee.setLastName(textFieldLastName.getText());

        employee.setTelephone(textFieldPhone.getText());
        employee.setSalary(Double.parseDouble(textFieldSalary.getText()));
        employee.setDateOfBirth(datePickerDayOfBirth.getValue());

        Main.getStaffController().editEmployee(employee.getId(), employee);

        closeAction(actionEvent);
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void setEmployee(Employee selectedItem) {

        textFieldLastName.setText(selectedItem.getLastName());
        textFieldFirstName.setText(selectedItem.getFirstName());
        ComboBoxEmployeePost.setValue("aaa");
        textFieldPhone.setText(selectedItem.getTelephone());
        textFieldSalary.setText(String.valueOf(selectedItem.getSalary()));
        datePickerDayOfBirth.setValue(selectedItem.getDateOfBirth());

        employee = selectedItem;
    }
}
