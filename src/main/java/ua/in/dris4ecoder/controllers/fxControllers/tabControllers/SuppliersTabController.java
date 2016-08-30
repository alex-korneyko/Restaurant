package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.model.businessObjects.Supplier;

import java.io.IOException;

/**
 * Created by Alex Korneyko on 30.08.2016 16:57.
 */
public class SuppliersTabController implements TabController<Supplier> {

    private TableView<Supplier> tableView;
    private ObservableList<Supplier> observableList;

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

    }

    @Override
    public void init(Stage mainStage, TableView<Supplier> tableView) throws Exception {

        this.tableView = tableView;
        this.observableList = tableView.getItems();

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editAction(new ActionEvent());
            }
        });


    }
}
