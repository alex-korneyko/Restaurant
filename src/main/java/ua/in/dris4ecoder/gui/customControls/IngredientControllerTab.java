package ua.in.dris4ecoder.gui.customControls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.io.IOException;

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
        setId(id);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/customTab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
