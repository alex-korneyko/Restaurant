package ua.in.dris4ecoder.view.windowsSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.in.dris4ecoder.controllers.fxControllers.MainWindowController;

/**
 * Created by Alex Korneyko on 04.08.2016 13:22.
 */
public class SimpleMainWindow extends Application implements MainWindow {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainWindow.fxml"));
        Parent fxmlParent = fxmlLoader.load();
        MainWindowController mainWindowController = fxmlLoader.getController();
        mainWindowController.setMainStage(primaryStage);

        primaryStage.setTitle("Ресторан");
        primaryStage.setScene(new Scene(fxmlParent));
        primaryStage.setMinHeight(665);
        primaryStage.setMinWidth(1000);
        primaryStage.show();

    }

    @Override
    public void runMainWindow(String[] args) {
        launch(args);
    }
}
