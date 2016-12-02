package ua.in.dris4ecoder.springConfigClasses;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.model.businessServices.*;
import ua.in.dris4ecoder.model.dao.CurrencyDao;
import ua.in.dris4ecoder.view.windowsSet.MainWindow;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.dao.RestaurantDao;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Alex Korneyko on 28.07.2016 15:00.
 */
@Configuration
public class AppConfig {

    @Bean
    Main main(StaffService staffController,
              InstrumentsService instrumentsController,
              ServiceService serviceController,
              ManagementService managementController,
              MainWindow mainWindow) {

        Main mainObject = new Main();

        mainObject.setStaffController(staffController);
        mainObject.setInstrumentsController(instrumentsController);
        mainObject.setServiceController(serviceController);
        mainObject.setManagementController(managementController);
        mainObject.setMainWindow(mainWindow);

        return mainObject;
    }

    @Bean
    ComboPooledDataSource comboPooledDataSourceForRestaurant() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(new ClassPathResource("jdbcRestaurant.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataSource.setDriverClass(properties.getProperty("jdbc.driver.class"));
        dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
        dataSource.setUser(properties.getProperty("jdbc.user"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("jdbc.min.connection")));
        dataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("jdbc.max.connection")));
        dataSource.setAcquireIncrement(Integer.parseInt(properties.getProperty("jdbc.acquire.increment")));

        return dataSource;
    }

    @Bean
    StaffService staffController(RestaurantDao<EmployeePost> hibernateEmployeePostDao,
                                 RestaurantDao<Employee> hibernateEmployeeDao,
                                 UserRegistrationService userRegistrationService) {

        StaffService controller = new StaffService();
        controller.setEmployeePostsDao(hibernateEmployeePostDao);
        controller.setEmployeeDao(hibernateEmployeeDao);
        controller.setUserRegistrationService(userRegistrationService);

        return controller;
    }

    @Bean
    InstrumentsService instrumentsController(@Qualifier("hibernateIngredientDao") RestaurantDao<Ingredient> ingredientRestaurantDao,
                                             @Qualifier("hibernateDishDao") RestaurantDao<Dish> dishRestaurantDao,
                                             @Qualifier("hibernateMenuDao") RestaurantDao<Menu> menuRestaurantDao,
                                             @Qualifier("hibernateUnitDao") RestaurantDao<Unit> unitRestaurantDao,
                                             CurrencyDao currencyDao) {

        InstrumentsService instrumentsController = new InstrumentsService();
        instrumentsController.setIngredientRestaurantDao(ingredientRestaurantDao);
        instrumentsController.setDishRestaurantDao(dishRestaurantDao);
        instrumentsController.setMenuRestaurantDao(menuRestaurantDao);
        instrumentsController.setUnitRestaurantDao(unitRestaurantDao);
        instrumentsController.setCurrencyDao(currencyDao);

        return instrumentsController;
    }

    @Bean
    ManagementService managementController(@Qualifier("hibernateSupplierDao") RestaurantDao<Contractor> supplierRestaurantDao,
                                           @Qualifier("hibernatePurchaseInvoiceDao") RestaurantDao<PurchaseInvoice> purchaseInvoiceRestaurantDao,
                                           @Qualifier("hibernateSalesInvoiceDao") RestaurantDao<SalesInvoice> salesInvoiceRestaurantDao,
                                           @Qualifier("hibernateWarehouseDao") RestaurantDao<WarehousePosition> warehousePositionRestaurantDao) {

        ManagementService managementController = new ManagementService();
        managementController.setContractorRestaurantDao(supplierRestaurantDao);
        managementController.setPurchaseInvoiceRestaurantDao(purchaseInvoiceRestaurantDao);
        managementController.setSalesInvoiceRestaurantDao(salesInvoiceRestaurantDao);
        managementController.setWarehousePositionRestaurantDao(warehousePositionRestaurantDao);
        return managementController;
    }

    @Bean
    ServiceService serviceController(RestaurantDao<Order> hibernateOrderDao,
                                     RestaurantDao<KitchenProcess> hibernateKitchenDao) {

        final ServiceService serviceController = new ServiceService();
        serviceController.setOrdersDao(hibernateOrderDao);
        serviceController.setKitchenProcessDao(hibernateKitchenDao);

        return serviceController;
    }


}
