package ua.in.dris4ecoder.view.customControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers.SimpleDialogueWindowController;

import java.io.IOException;

/**
 * Created by Alex Korneyko on 10.09.2016 10:18.
 */
public class WarningsDialogWindow {

    private static Stage stage;
    private static SimpleDialogueWindowController simpleDialogueWindowController;

    public static boolean showWindow(WindowType windowType, String message, Stage owner) {

        if (stage == null )
            init(owner);

        switch (windowType) {
            case INFO:
                simpleDialogueWindowController.setParameters("@../../images/dialog-information.png", message);
                break;
            case CONFIRM:
                simpleDialogueWindowController.setParameters("@../../images/dialog-confirm.png", message);
                break;
            case WARNING:
                simpleDialogueWindowController.setParameters("@../../images/dialog-warning.png", message);
                break;
            case ERROR:
                simpleDialogueWindowController.setParameters("@../../images/dialog-error.png", message);
        }

        stage.showAndWait();
        simpleDialogueWindowController.disableOk(false);
        return simpleDialogueWindowController.getResult();
    }

    public static boolean showWindow(WindowType windowType, String message, Stage owner, boolean disableOkButton) {

        if (stage == null )
            init(owner);

        simpleDialogueWindowController.disableOk(true);
        return showWindow(windowType, message, owner);
    }

    public static boolean getResult() {
        return simpleDialogueWindowController.getResult();
    }

    private static void init(Stage owner) {

        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        FXMLLoader fxmlLoader = new FXMLLoader(Object.class.getResource("/fxml/dialogueWindows/simpleDialogueWindow.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException ignored) {
        }

        simpleDialogueWindowController = fxmlLoader.getController();
        simpleDialogueWindowController.init(stage);
    }

    public enum WindowType {
        INFO, CONFIRM, WARNING, ERROR
    }
}
