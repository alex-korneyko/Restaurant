package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

/**
 * Created by Alex Korneyko on 16.08.2016 19:47.
 */
public class IngredientAddEditDialogueWindowController implements AddEditController {

    private ObservableList<Ingredient> observableList;
    private Ingredient selectedIngredient;

    @FXML
    public TextField textFieldIngredientName;

    @Override
    public void saveAction(ActionEvent actionEvent) {

        if(selectedIngredient == null) {
            if (!textFieldIngredientName.getText().isEmpty()) {
                Main.getManagementController().addIngredient(textFieldIngredientName.getText());
                observableList.add(Main.getManagementController().findIngredient(textFieldIngredientName.getText()).get(0));
            }
        } else {
            Main.getManagementController().editIngredient(selectedIngredient.getId(), new Ingredient(textFieldIngredientName.getText()));
            observableList.clear();
            observableList.addAll(Main.getManagementController().findAllIngredients());
        }

        textFieldIngredientName.setText("");
        closeAction(actionEvent);
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {

        textFieldIngredientName.setText("");
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void setObservableList(ObservableList<Ingredient> observableList) {
        this.observableList = observableList;
    }

    public void setIngredient(Ingredient selectedItem) {
        selectedIngredient = selectedItem;
        textFieldIngredientName.setText(selectedItem.getIngredientName());
    }
}
