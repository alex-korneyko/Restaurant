package ua.in.dris4ecoder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.in.dris4ecoder.model.businessServices.InstrumentsService;
import ua.in.dris4ecoder.model.businessServices.ManagementService;
import ua.in.dris4ecoder.model.businessServices.ServiceService;
import ua.in.dris4ecoder.model.businessServices.StaffService;
import ua.in.dris4ecoder.springConfigClasses.*;
import ua.in.dris4ecoder.view.windowsSet.MainWindow;

/**
 * Created by Alex Korneyko on 28.07.2016 13:28.
 */
public class Main {

    private static StaffService staffController;
    private static InstrumentsService instrumentsController;
    private static ServiceService serviceController;
    private static ManagementService managementController;


    private MainWindow mainWindow;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                AppConfig.class, FXSpringConfig.class, HibernateConfig.class, SecurityConfig.class);
        Main main = applicationContext.getBean(Main.class);
        main.start(args);
    }

    private void start(String[] args) {

        mainWindow.runMainWindow(args);
    }

    public static StaffService getStaffController() {
        return staffController;
    }

    public static InstrumentsService getInstrumentsController() {
        return instrumentsController;
    }

    public static ServiceService getServiceController() {
        return serviceController;
    }

    public static ManagementService getManagementController() {
        return managementController;
    }

    public void setStaffController(StaffService staffController) {
        Main.staffController = staffController;
    }

    public void setInstrumentsController(InstrumentsService instrumentsController) {
        Main.instrumentsController = instrumentsController;
    }

    public void setServiceController(ServiceService serviceController) {
        Main.serviceController = serviceController;
    }

    public void setManagementController(ManagementService managementController) {
        Main.managementController = managementController;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
