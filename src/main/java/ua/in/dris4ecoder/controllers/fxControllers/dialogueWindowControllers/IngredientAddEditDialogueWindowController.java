package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.springframework.transaction.annotation.Transactional;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

/**
 * Created by Alex Korneyko on 16.08.2016 19:47.
 */
public class IngredientAddEditDialogueWindowController implements AddEditController {

    private ObservableList<Ingredient> observableList;

    @FXML
    public TextField textFieldIngredientName;

    @Override
    public void saveAction(ActionEvent actionEvent) {

        if(!textFieldIngredientName.getText().isEmpty()) {
            Main.getManagementController().addIngredient(textFieldIngredientName.getText());
            observableList.add(Main.getManagementController().findIngredient(textFieldIngredientName.getText()).get(0));
        }

        textFieldIngredientName.setText("");
        closeAction(actionEvent);
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void setObservableList(ObservableList<Ingredient> observableList) {
        this.observableList = observableList;
    }
}
