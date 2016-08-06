package ua.in.dris4ecoder.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import ua.in.dris4ecoder.gui.customControls.CustomTab;

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

        CustomTab tabPosts = new CustomTab("Должности");
        tabPosts.setId("posts");

        tabPosts.setDisableSearchByDataRange(true);
        tabPosts.setColumns("Id", "Название");

        tabPaneDbManagement.getTabs().add(tabPosts);
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabEmployees(ActionEvent actionEvent) {

        if(findTab("Employees")) return;

        CustomTab tabEmployees = new CustomTab("Сотрудники");
        tabEmployees.setId("Employees");

        tabEmployees.setColumns("Id", "Фамилия", "Имя", "Должность", "Дата рождения", "Телефон", "Оклад");

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
