package ua.in.dris4ecoder.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
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

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void createTabPosts(ActionEvent actionEvent) throws IOException {

        CustomTab tabPosts = new CustomTab("Должности");
        tabPosts.setId("posts");
        tabPosts.setDisableSearchByDataRange(true);
        tabPosts.setColumns("Id", "Название");

        tabPaneDbManagement.getTabs().add(tabPosts);
    }

    public void createTabEmployees(ActionEvent actionEvent) {

        CustomTab tabEmployees = new CustomTab("Сотрудники");
        tabEmployees.setId("Employees");

        tabEmployees.setColumns("Id", "Фамилия", "Имя", "Должность", "Дата рождения", "Телефон", "Оклад");

        tabPaneDbManagement.getTabs().add(tabEmployees);
    }
}
