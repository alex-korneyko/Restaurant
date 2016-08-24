package ua.in.dris4ecoder.view.windowsSet;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers.AddEditController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex Korneyko on 23.08.2016 13:11.
 */
public class DialogueWindows {

    private static Map<String, Stage> dialogueWindows = new HashMap<>();
    private static Map<String, AddEditController> dialogueWindowsControllers = new HashMap<>();

    public static Stage getStage(String stageKey) {
        return dialogueWindows.get(stageKey);
    }

    public static AddEditController getController(String stageKey) {
        return dialogueWindowsControllers.get(stageKey);
    }

    public static <E> void createStage(String stageKey, Stage mainStage, FXMLLoader fxmlLoader, ObservableList<E> observableList) throws Exception {

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainStage);
        stage.setScene(new Scene(fxmlLoader.load()));
        AddEditController<E> addEditController = fxmlLoader.getController();
        addEditController.init(observableList);

        dialogueWindowsControllers.put(stageKey, addEditController);
        dialogueWindows.put(stageKey, stage);
    }
}
