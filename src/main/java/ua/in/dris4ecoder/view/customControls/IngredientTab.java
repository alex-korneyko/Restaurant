package ua.in.dris4ecoder.view.customControls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ua.in.dris4ecoder.controllers.fxControllers.tabControllers.IngredientTabController;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.io.IOException;

/**
 * Created by Alex Korneyko on 16.08.2016 11:07.
 */
public class IngredientTab extends Tab {

    private Stage mainStage;
    private TableView<Ingredient> tableView;
    ObservableList<Ingredient> observableList = FXCollections.observableArrayList();
    private final IngredientTabController ingredientTabController;

    public IngredientTab(String text, String id, Stage mainStage) throws IOException {
        super(text);
        this.mainStage = mainStage;
        setId(id);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ingredientTab.fxml"));
        ingredientTabController = new IngredientTabController(observableList, mainStage);
        fxmlLoader.setController(ingredientTabController);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tableView = ServiceClass.getTableView(this);
        setColumns(tableView, "id", "idProp");
        setColumns(tableView, "Название", "ingredientNameProp");
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.setItems(observableList);
        ingredientTabController.init(tableView);
    }

    private void setColumns(TableView<Ingredient> tableView, String columnName, String propertyName) {
        TableColumn<Ingredient, String> tableColumn = new TableColumn<>(columnName);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        tableView.getColumns().add(tableColumn);
    }

}
