package ua.in.dris4ecoder.view.customControls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ua.in.dris4ecoder.controllers.fxControllers.ServiceClass;
import ua.in.dris4ecoder.controllers.fxControllers.tabControllers.DishTabController;
import ua.in.dris4ecoder.model.businessObjects.Dish;

/**
 * Created by Alex Korneyko on 17.08.2016 19:48.
 */
public class DishTab extends Tab {

    public DishTab(String text, String id) {
        super(text);
        setId(id);
    }

    public void init(Stage mainStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dishTab.fxml"));
        ObservableList<Dish> observableList = FXCollections.observableArrayList();
        DishTabController dishTabController = new DishTabController(observableList, mainStage);
        fxmlLoader.setController(dishTabController);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        TableView<Dish> tableView = ServiceClass.getTableView(this);
        ServiceClass.setColumns(tableView, "id", "idProp");
        ServiceClass.setColumns(tableView, "Название", "dishNameProp", 200);
        ServiceClass.setColumns(tableView, "Категория", "dishCategoryProp");
        ServiceClass.setColumns(tableView, "Цена", "priceProp");
        ServiceClass.setColumns(tableView, "Вес", "weightProp");
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.setItems(observableList);

        dishTabController.init(tableView);
    }
}
