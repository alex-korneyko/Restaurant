package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ua.in.dris4ecoder.model.businessObjects.Order;

/**
 * Created by Alex Korneyko on 28.08.2016 16:55.
 */
public class OrderAddEditDialogueWindowController implements AddEditController<Order> {

    public TableView tableViewIngredients;
    public Button buttonAddDish;
    public Button buttonEditDishCount;
    public Button buttonRemoveDish;
    public Button buttonClearDishList;
    public Button buttonOk;
    public Button buttonCancel;

    @Override
    public void saveAction(ActionEvent actionEvent) {

    }

    @Override
    public void closeAction(ActionEvent actionEvent) {

    }

    @Override
    public void setMainStage(Stage mainStage) {

    }

    @Override
    public void init(ObservableList<Order> observableList, Stage thisStage) throws Exception {

    }

    @Override
    public void setValueForEditing(Order valueForEditing) {

    }

    @Override
    public Order getNewValue() {
        return null;
    }

    public void addDishToOrderAction(ActionEvent actionEvent) {

    }

    public void editDishCountInOrderAction(ActionEvent actionEvent) {

    }

    public void removeDishFromOrderAction(ActionEvent actionEvent) {

    }

    public void clearDishListInOrderAction(ActionEvent actionEvent) {

    }
}
