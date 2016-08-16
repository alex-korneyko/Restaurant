package ua.in.dris4ecoder.gui.customControls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 16.08.2016 11:07.
 */
public class IngredientControllerTab extends Tab {

    private ObservableList<Ingredient> observableList = FXCollections.observableArrayList();
    private Stage mainStage;
    private TableView<Ingredient> tableView;

    @FXML
    private void initialize(){

        tableView = ServiceClass.getTableView(this);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public IngredientControllerTab(String text, String id, Stage mainStage) {
        super(text);
        this.mainStage = mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ingredientTab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setId(id);

        setColumns(tableView, "id", "idProp");
        setColumns(tableView, "Название", "ingredientNameProp");
        tableView.setItems(observableList);
    }

    @FXML
    public void addAction(ActionEvent actionEvent) {

    }

    @FXML
    public void editAction(ActionEvent actionEvent) {

    }

    @FXML
    public void deleteAction(ActionEvent actionEvent) {

    }

    @FXML
    public void getAllAction(ActionEvent actionEvent) {

        System.out.println("Get all ingredients!!!!!!!!!!!!!!!!!!!!!!");
        observableList.clear();
        final List<Ingredient> allIngredients = Main.getManagementController().getAllIngredients();
        observableList.addAll(allIngredients);
    }

    private void setColumns(TableView<Ingredient> tableView, String columnName, String propertyName) {
        TableColumn<Ingredient, String> tableColumn = new TableColumn<>(columnName);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        tableView.getColumns().add(tableColumn);
    }

}
