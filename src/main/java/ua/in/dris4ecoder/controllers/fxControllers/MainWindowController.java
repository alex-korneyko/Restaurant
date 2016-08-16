package ua.in.dris4ecoder.controllers.fxControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import ua.in.dris4ecoder.gui.customControls.IngredientControllerTab;
import ua.in.dris4ecoder.gui.customControls.StaffControllerTab;
import ua.in.dris4ecoder.model.businessObjects.Employee;
import ua.in.dris4ecoder.model.businessObjects.EmployeePost;

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

        StaffControllerTab<EmployeePost> tabPosts = new StaffControllerTab<>("Должности", "posts", mainStage);

        tabPosts.setDisableSearchByDataRange(true);
        tabPosts.setColumnsByClass(EmployeePost.class, "id", "Название");

        tabPaneDbManagement.getTabs().add(tabPosts);
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabEmployees(ActionEvent actionEvent) {

        if(findTab("employees")) return;

        StaffControllerTab<Employee> tabEmployees = new StaffControllerTab<>("Сотрудники", "employees", mainStage);

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

    public void createTabIngredients(ActionEvent actionEvent) {

        if(findTab("ingredients")) return;

        IngredientControllerTab ingredientControllerTab = new IngredientControllerTab("Ингредиенты", "ingredients", mainStage);




    }
}
