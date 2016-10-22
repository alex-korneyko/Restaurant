package ua.in.dris4ecoder.view.customControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import ua.in.dris4ecoder.controllers.fxControllers.tabControllers.MainTabController;

import java.io.IOException;

/**
 * Created by Alex Korneyko on 24.09.2016 19:42.
 */
public class MainTab extends Tab {

    public MainTab(String text, String id) {
        super(text);
        setId(id);
    }

    public void init(Stage mainStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/tabs/mainTab.fxml"));
        MainTabController mainTabController = new MainTabController();
        fxmlLoader.setController(mainTabController);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        mainTabController.init();
    }
}
