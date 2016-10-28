package ua.in.dris4ecoder.springConfigClasses;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ua.in.dris4ecoder.Main;
import ua.in.dris4ecoder.controllers.businessControllers.InstrumentsController;
import ua.in.dris4ecoder.controllers.businessControllers.ManagementController;
import ua.in.dris4ecoder.controllers.businessControllers.ServiceController;
import ua.in.dris4ecoder.controllers.businessControllers.StaffController;
import ua.in.dris4ecoder.view.windowsSet.MainWindow;
import ua.in.dris4ecoder.model.businessObjects.*;
import ua.in.dris4ecoder.model.dao.RestaurantDao;
import ua.in.dris4ecoder.model.dao.jdbc.JdbcEmployeePostsDao;

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
    Main main(StaffController staffController,
              InstrumentsController instrumentsController,
              ServiceController serviceController,
              ManagementController managementController,
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
    RestaurantDao jdbcRestaurantDao(ComboPooledDataSource dataSource) {

        JdbcEmployeePostsDao jdbcEmployeePostsDao = new JdbcEmployeePostsDao();
        jdbcEmployeePostsDao.setDataSource(dataSource);

        return jdbcEmployeePostsDao;
    }

    @Bean
    StaffController staffController(RestaurantDao<EmployeePost> jdbcEmployeePostsDao,
                                    RestaurantDao<Employee> jdbcEmployeeDao) {

        StaffController controller = new StaffController();
        controller.setEmployeePostsDao(jdbcEmployeePostsDao);
        controller.setEmployeeDao(jdbcEmployeeDao);

        return controller;
    }

    @Bean
    InstrumentsController instrumentsController(@Qualifier("hibernateIngredientDao") RestaurantDao<Ingredient> ingredientRestaurantDao,
                                                @Qualifier("hibernateDishDao") RestaurantDao<Dish> dishRestaurantDao,
                                                @Qualifier("hibernateMenuDao") RestaurantDao<Menu> menuRestaurantDao,
                                                @Qualifier("hibernateUnitDao") RestaurantDao<Unit> unitRestaurantDao) {

        InstrumentsController instrumentsController = new InstrumentsController();
        instrumentsController.setIngredientRestaurantDao(ingredientRestaurantDao);
        instrumentsController.setDishRestaurantDao(dishRestaurantDao);
        instrumentsController.setMenuRestaurantDao(menuRestaurantDao);
        instrumentsController.setUnitRestaurantDao(unitRestaurantDao);

        return instrumentsController;
    }

    @Bean
    ManagementController managementController(@Qualifier("hibernateSupplierDao") RestaurantDao<Contractor> supplierRestaurantDao,
                                              @Qualifier("hibernatePurchaseInvoiceDao") RestaurantDao<PurchaseInvoice> purchaseInvoiceRestaurantDao,
                                              @Qualifier("hibernateSalesInvoiceDao") RestaurantDao<SalesInvoice> salesInvoiceRestaurantDao,
                                              @Qualifier("hibernateWarehouseDao") RestaurantDao<WarehousePosition> warehousePositionRestaurantDao) {

        ManagementController managementController = new ManagementController();
        managementController.setContractorRestaurantDao(supplierRestaurantDao);
        managementController.setPurchaseInvoiceRestaurantDao(purchaseInvoiceRestaurantDao);
        managementController.setSalesInvoiceRestaurantDao(salesInvoiceRestaurantDao);
        managementController.setWarehousePositionRestaurantDao(warehousePositionRestaurantDao);
        return managementController;
    }

    @Bean
    ServiceController serviceController(RestaurantDao<Order> hibernateOrderDao,
                                        RestaurantDao<KitchenProcess> hibernateKitchenDao) {

        final ServiceController serviceController = new ServiceController();
        serviceController.setOrdersDao(hibernateOrderDao);
        serviceController.setKitchenProcessDao(hibernateKitchenDao);

        return serviceController;
    }


}
