package ua.in.dris4ecoder.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import ua.in.dris4ecoder.gui.customControls.CustomTab;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;

import java.io.IOException;

/**
 * Created by Alex Korneyko on 04.08.2016 14:49.
 */
public class MainWindowController {

    @FXML public MenuItem menuItemCreateTabPosts;
    public TabPane tabPaneDbManagement;
    private Stage mainStage;
    private SingleSelectionModel<Tab> selectionModel;

    @FXML private void initialize() {
        selectionModel = tabPaneDbManagement.getSelectionModel();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void createTabPosts(ActionEvent actionEvent) {

        if(findTab("posts")) return;

        CustomTab<EmployeePost> tabPosts = new CustomTab<>("Должности", "posts");

        tabPosts.setDisableSearchByDataRange(true);
//        tabPosts.setColumns("Id", "Название");
        tabPosts.setColumnsByClass(EmployeePost.class, "id", "Название");

        tabPaneDbManagement.getTabs().add(tabPosts);
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabEmployees(ActionEvent actionEvent) {

        if(findTab("employees")) return;

        CustomTab<Employee> tabEmployees = new CustomTab<>("Сотрудники", "employees");

        tabEmployees.setColumnsByClass(Employee.class, "Id", "Фамилия", "Имя", "Телефон", "Дата рождения", "Должность", "Оклад");

        tabPaneDbManagement.getTabs().add(tabEmployees);
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    private boolean findTab(String id) {

        for(int i=0; i < tabPaneDbManagement.getTabs().size(); i++) {
            if(tabPaneDbManagement.getTabs().get(i).getId().equals(id)) {
                selectionModel.select(i);
                return true;
            }
        }
        return false;
    }
}
