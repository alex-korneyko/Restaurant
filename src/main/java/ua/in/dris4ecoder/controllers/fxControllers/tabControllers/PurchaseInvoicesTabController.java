package ua.in.dris4ecoder.controllers.fxControllers.tabControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.PurchaseInvoice;
import ua.in.dris4ecoder.view.windowsSet.DialogueWindows;

import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Korneyko on 30.08.2016 13:30.
 */
public class PurchaseInvoicesTabController implements TabController<PurchaseInvoice>{

    private TableView<PurchaseInvoice> tableView;
    private ObservableList<PurchaseInvoice> observableList;

    @Override
    public void addAction(ActionEvent actionEvent) throws IOException {

        DialogueWindows.getStage("purchaseInvoiceAddEditStage").setTitle("Новая приходная накладная");
        DialogueWindows.getController("purchaseInvoiceAddEditStage").setValueForEditing(null);
        DialogueWindows.getStage("purchaseInvoiceAddEditStage").showAndWait();
    }

    @Override
    public void editAction(ActionEvent actionEvent) {

        DialogueWindows.getStage("purchaseInvoiceAddEditStage").setTitle("Изменить");
        DialogueWindows.getController("purchaseInvoiceAddEditStage").setValueForEditing(tableView.getSelectionModel().getSelectedItem());
        DialogueWindows.getStage("purchaseInvoiceAddEditStage").showAndWait();
    }

    @Override
    public void deleteAction(ActionEvent actionEvent) {

        final PurchaseInvoice selectedItem = tableView.getSelectionModel().getSelectedItem();
        Main.getManagementController().removePurchaseInvoice(selectedItem);
        getAllAction(actionEvent);
    }

    @Override
    public void getAllAction(ActionEvent actionEvent) {

        observableList.clear();
        List<PurchaseInvoice> purchaseInvoices = Main.getManagementController().findAllPurchaseInvoices();
        observableList.addAll(purchaseInvoices);
    }

    @Override
    public void init(Stage mainStage, TableView<PurchaseInvoice> tableView) throws Exception {

        this.tableView = tableView;
        this.observableList = tableView.getItems();

        if (DialogueWindows.getStage("purchaseInvoiceAddEditStage") == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/dialogueWindows/purchaseInvoiceAddEditDialogueWindow.fxml"));
            DialogueWindows.createStage("purchaseInvoiceAddEditStage", mainStage, fxmlLoader, observableList);
        }

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                editAction(new ActionEvent());
            }
        });
    }
}
