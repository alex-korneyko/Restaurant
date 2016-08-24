package ua.in.dris4ecoder;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.dris4ecoder.controllers.businessControllers.ManagementController;
import ua.in.dris4ecoder.controllers.businessControllers.ServiceController;
import ua.in.dris4ecoder.controllers.businessControllers.StaffController;
import ua.in.dris4ecoder.controllers.fxControllers.dialogueWindowControllers.AddEditController;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.view.windowsSet.MainWindow;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex Korneyko on 28.07.2016 13:28.
 */
public class Main {

    private static StaffController staffController;
    private static ManagementController managementController;
    private static ServiceController serviceController;


    private MainWindow mainWindow;

    public static void main(String[] args) {

        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("application-context.xml");
        Main main = applicationContext.getBean(Main.class);
        main.start(args);
    }

    private void start(String[] args) {

        mainWindow.runMainWindow(args);

    }

    public static StaffController getStaffController() {
        return staffController;
    }

    public static ManagementController getManagementController() {
        return managementController;
    }

    public void setStaffController(StaffController staffController) {
        Main.staffController = staffController;
    }

    public void setManagementController(ManagementController managementController) {
        Main.managementController = managementController;
    }

    public void setServiceController(ServiceController serviceController) {
        Main.serviceController = serviceController;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
