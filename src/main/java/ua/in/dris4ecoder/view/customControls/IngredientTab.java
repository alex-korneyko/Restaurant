package ua.in.dris4ecoder.view.customControls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ua.in.dris4ecoder.controllers.fxControllers.tabControllers.IngredientTabController;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.io.IOException;

/**
 * Created by Alex Korneyko on 16.08.2016 11:07.
 */
public class IngredientTab extends Tab {

    public IngredientTab(String text, String id){
        super(text);
        setId(id);
    }

    public void init(Stage mainStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ingredientTab.fxml"));
        ObservableList<Ingredient> observableList = FXCollections.observableArrayList();
        IngredientTabController ingredientTabController = new IngredientTabController(observableList, mainStage);
        fxmlLoader.setController(ingredientTabController);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        TableView<Ingredient> tableView = ServiceClass.getTableView(this);
        ServiceClass.setColumns(tableView, "id", "idProp");
        ServiceClass.setColumns(tableView, "Название", "ingredientNameProp");
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.setItems(observableList);

        ingredientTabController.init(tableView);
    }
}
