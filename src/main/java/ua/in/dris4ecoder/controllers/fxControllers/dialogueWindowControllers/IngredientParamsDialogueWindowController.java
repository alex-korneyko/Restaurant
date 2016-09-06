package ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;

/**
 * Created by Alex Korneyko on 31.08.2016 12:39.
 */
public class IngredientParamsDialogueWindowController implements AddEditController<Ingredient> {

    public Label labelIngredientName;
    public Label labelUnit;
    public Label labelLastPrice;
    public Label labelAveragePrice;
    public TextField textFieldPrice;
    public TextField textFieldAmount;
    private Ingredient ingredient;
    private ObservableList<Ingredient> observableList;

    @Override
    public void saveAction(ActionEvent actionEvent) {
        ingredient.setIngredientPrice(Double.parseDouble(textFieldPrice.getText().replace(',', '.')));
        ingredient.setIngredientWeight(Double.parseDouble(textFieldAmount.getText().replace(',', '.')));

        if(observableList.contains(ingredient)) {
            observableList.set(observableList.indexOf(ingredient), ingredient);
        } else {
            observableList.add(ingredient);
        }

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void closeAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void setMainStage(Stage mainStage) {

    }

    @Override
    public void init(ObservableList<Ingredient> observableList, Stage thisStage) throws Exception {
        this.observableList = observableList;
    }

    @Override
    public void setValueForEditing(Ingredient valueForEditing) {
        this.ingredient = valueForEditing;
        labelIngredientName.setText(valueForEditing.getIngredientName());
        labelUnit.setText(valueForEditing.getUnit().getUnitName());
        textFieldPrice.setText(ingredient.getIngredientPrice() == null ? "0.0" : ingredient.getIngredientPrice().toString());
        textFieldAmount.setText(ingredient.getIngredientWeight() == null ? "0.0" : ingredient.getIngredientWeight().toString());
    }

    @Override
    public Ingredient getNewValue() {
        return null;
    }
}
