package ua.in.dris4ecoder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.dris4ecoder.controllers.businessControllers.InstrumentsController;
import ua.in.dris4ecoder.controllers.businessControllers.ManagementController;
import ua.in.dris4ecoder.controllers.businessControllers.ServiceController;
import ua.in.dris4ecoder.controllers.businessControllers.StaffController;
import ua.in.dris4ecoder.springConfigClasses.*;
import ua.in.dris4ecoder.view.windowsSet.MainWindow;

/**
 * Created by Alex Korneyko on 28.07.2016 13:28.
 */
public class Main {

    private static StaffController staffController;
    private static InstrumentsController instrumentsController;
    private static ServiceController serviceController;
    private static ManagementController managementController;


    private MainWindow mainWindow;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                AppConfig.class, FXSpringConfig.class, HibernateConfig.class, JdbcConfig.class, SecurityConfig.class);
        Main main = applicationContext.getBean(Main.class);
        main.start(args);
    }

    private void start(String[] args) {

        mainWindow.runMainWindow(args);
    }

    public static StaffController getStaffController() {
        return staffController;
    }

    public static InstrumentsController getInstrumentsController() {
        return instrumentsController;
    }

    public static ServiceController getServiceController() {
        return serviceController;
    }

    public static ManagementController getManagementController() {
        return managementController;
    }

    public void setStaffController(StaffController staffController) {
        Main.staffController = staffController;
    }

    public void setInstrumentsController(InstrumentsController instrumentsController) {
        Main.instrumentsController = instrumentsController;
    }

    public void setServiceController(ServiceController serviceController) {
        Main.serviceController = serviceController;
    }

    public void setManagementController(ManagementController managementController) {
        Main.managementController = managementController;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
