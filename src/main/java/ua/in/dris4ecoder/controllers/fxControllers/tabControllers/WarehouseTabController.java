package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.model.businessObjects.Invoice;
import ua.in.dris4ecoder.model.businessObjects.WarehousePosition;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 30.08.2016 11:56.
 */
public class WarehouseTabController implements TabController<WarehousePosition> {

    @FXML
    private TableView<WarehousePosition> tableView;
    private ObservableList<WarehousePosition> observableList;

    @Override
    public void addAction(ActionEvent actionEvent) throws IOException {

    }

    @Override
    public void editAction(ActionEvent actionEvent) {

    }

    @Override
    public void deleteAction(ActionEvent actionEvent) {

    }

    @Override
    public void getAllAction(ActionEvent actionEvent) {

        observableList.clear();
        List<WarehousePosition> allPositions = Main.getManagementController().findAllPositions();
        observableList.addAll(allPositions);
    }

    @Override
    public void init(Stage mainStage, TableView<WarehousePosition> tableView) throws Exception {

        this.tableView = tableView;
        this.observableList = tableView.getItems();
    }
}
