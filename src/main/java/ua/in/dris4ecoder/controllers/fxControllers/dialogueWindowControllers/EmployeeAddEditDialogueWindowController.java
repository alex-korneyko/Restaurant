package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 09.08.2016 12:36.
 */
public class EmployeeAddEditDialogueWindowController implements AddEditController<Employee> {


    public TextField textFieldLastName;
    public TextField textFieldFirstName;
    public ComboBox<String> comboBoxEmployeePost;
    public TextField textFieldPhone;
    public TextField textFieldSalary;
    public DatePicker datePickerDayOfBirth;

    private ObservableList<String> employeePosts = FXCollections.observableArrayList();

    private Employee employee;
    private ObservableList<Employee> observableList;
    private Stage controlledStage;
    private Stage mainStage;

    @Override
    @Transactional
    public void saveAction(ActionEvent actionEvent) {

        if (employee == null) {
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

    @Override
    public void closeAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void addNewEmPost() {

        Stage postAddEditStage = DialogueWindows.getStage("postAddEditStage");
        postAddEditStage.setTitle("Создать");
        DialogueWindows.getController("postAddEditStage").setValueForEditing(null);
        postAddEditStage.showAndWait();

        final EmployeePost employeePost = (EmployeePost) DialogueWindows.getController("postAddEditStage").getNewValue();
        if (employeePost != null) {
            refreshEmployeePosts();
            comboBoxEmployeePost.setValue(employeePost.getPostName());
        }
    }

    @Transactional
    private void fillEmployee() {
        employee.setFirstName(textFieldFirstName.getText());
        employee.setLastName(textFieldLastName.getText());
        employee.setEmployeePost(Main.getStaffController().findEmployeePost(comboBoxEmployeePost.getValue()));
        employee.setPhoneNumber(textFieldPhone.getText());
        employee.setSalary(Double.parseDouble(textFieldSalary.getText()));
        employee.setDateOfBirth(datePickerDayOfBirth.getValue());
    }

    public void setMainStage(Stage mainStage) {

        this.mainStage = mainStage;
    }

    @Override
    @Transactional
    public void setValueForEditing(Employee selectedItem) {
        refreshEmployeePosts();

        textFieldLastName.setText(selectedItem != null ? selectedItem.getLastName() : "");
        textFieldFirstName.setText(selectedItem != null ? selectedItem.getFirstName() : "");
        comboBoxEmployeePost.setValue(selectedItem != null ? selectedItem.getEmployeePost().getPostName() : "");
        textFieldPhone.setText(selectedItem != null ? selectedItem.getPhoneNumber() : "");
        textFieldSalary.setText(selectedItem != null ? String.valueOf(selectedItem.getSalary()) : "");
        datePickerDayOfBirth.setValue(selectedItem != null ? selectedItem.getDateOfBirth() : null);

        employee = selectedItem;
    }

    @Override
    public Employee getNewValue() {

        return this.employee;
    }

    private void refreshEmployeePosts() {
        employeePosts.clear();
        employeePosts.addAll(Main.getStaffController().getAllEmployeePosts().stream().map(EmployeePost::getPostName).collect(Collectors.toList()));
        comboBoxEmployeePost.setItems(employeePosts);
    }

    public void init(ObservableList<Employee> observableList, Stage thisStage) throws Exception {
        this.observableList = observableList;
        this.controlledStage = thisStage;

        if(DialogueWindows.getStage("postAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/emPostAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("postAddEditStage", controlledStage, fxmlLoader, this.observableList);
        }
    }
}
