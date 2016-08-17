package ua.in.dris4ecoder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.dris4ecoder.controllers.businessControllers.ManagementController;
import ua.in.dris4ecoder.controllers.businessControllers.ServiceController;
import ua.in.dris4ecoder.controllers.businessControllers.StaffController;
import ua.in.dris4ecoder.model.businessObjects.Dish;
import ua.in.dris4ecoder.model.businessObjects.DishCategory;
import ua.in.dris4ecoder.model.businessObjects.Ingredient;
import ua.in.dris4ecoder.view.windowsSet.MainWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Korneyko on 28.07.2016 13:28.
 */
public class Main {

    private static StaffController staffController;
    private static ManagementController managementController;
    private static ServiceController serviceController;

    private MainWindow mainWindow;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
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
