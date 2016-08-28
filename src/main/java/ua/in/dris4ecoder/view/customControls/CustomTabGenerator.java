package ua.in.dris4ecoder.view.customControls;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.controllers.fxControllers.tabControllers.TabController;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

import java.util.List;

/**
 * Created by Alex Korneyko on 25.08.2016 13:40.
 */
public class CustomTabGenerator {

    public static Tab generate(Stage mainStage, String resourcePath, List<CustomColumn> customColumns) throws Exception {

        Tab tab = new Tab();

        FXMLLoader fxmlLoader = new FXMLLoader(Object.class.getResource(resourcePath));
        fxmlLoader.setRoot(tab);
        fxmlLoader.load();

        TableView<Ingredient> tableView = ServiceClass.getTableView(tab);

        customColumns.forEach(customColumn -> ServiceClass.setColumns(tableView, customColumn));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.setItems(FXCollections.observableArrayList());

        ((TabController) fxmlLoader.getController()).init(mainStage, tableView);

        return tab;
    }
}
