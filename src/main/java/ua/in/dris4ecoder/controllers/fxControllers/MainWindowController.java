package ua.in.dris4ecoder.controllers.fxControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import ua.in.dris4ecoder.view.customControls.CustomColumn;
import ua.in.dris4ecoder.view.customControls.CustomTabGenerator;
import ua.in.dris4ecoder.view.customControls.SettingsTab;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex Korneyko on 04.08.2016 14:49.
 */
public class MainWindowController {

    @FXML public MenuItem menuItemCreateTabPosts;
    @FXML public TabPane tabPaneDbManagement;
    private Stage mainStage;
    private SingleSelectionModel<Tab> selectionModel;

    public void createTabPosts() throws Exception {

        if(findTab("posts")) return;

        List<CustomColumn> customColumns = Arrays.asList(
                new CustomColumn("id", "id"),
                new CustomColumn("Название", "postName", 200)
        );

        tabPaneDbManagement.getTabs().add(CustomTabGenerator.generate(mainStage, "/fxml/tabs/postTab.fxml", customColumns));

        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabEmployees() throws Exception {

        if(findTab("employees")) return;

        List<CustomColumn> customColumns = Arrays.asList(
                new CustomColumn("id", "idProp", 30),
                new CustomColumn("Фамилия", "lastNameProp", 120),
                new CustomColumn("Имя", "firstNameProp", 120),
                new CustomColumn("Телефон", "phoneNumberProp", 120),
                new CustomColumn("Дата рождения", "dateOfBirthProp"),
                new CustomColumn("Должность", "employeePostProp", 120),
                new CustomColumn("Оклад", "salaryProp", 70)
        );

        tabPaneDbManagement.getTabs().add(CustomTabGenerator.generate(mainStage, "/fxml/tabs/employeeTab.fxml", customColumns));

        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabOrders(ActionEvent actionEvent) throws Exception {

        if(findTab("orders")) return;

        List<CustomColumn> customColumns = Arrays.asList(
                new CustomColumn("id", "idProp", 50),
                new CustomColumn("Оффициант", "employeeNameProp", 200),
                new CustomColumn("Столик", "deskProp", 50),
                new CustomColumn("Статус заказа", "statusProp", 100),
                new CustomColumn("Дата", "dateProp", 100)
        );

        tabPaneDbManagement.getTabs().add(CustomTabGenerator.generate(mainStage, "/fxml/tabs/orderTab.fxml", customColumns));
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabKitchen(ActionEvent actionEvent) throws Exception {

        if(findTab("kitchen")) return;

        List<CustomColumn> customColumns = Arrays.asList(

        );

        tabPaneDbManagement.getTabs().add(CustomTabGenerator.generate(mainStage, "/fxml/tabs/kitchenProcessTab.fxml", customColumns));
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabIngredients() throws Exception {

        if(findTab("ingredients")) return;

        List<CustomColumn> customColumns = Arrays.asList(
                new CustomColumn("id", "idProp"),
                new CustomColumn("Название", "ingredientNameProp", 200),
                new CustomColumn("Еденица", "unitNameProperty", 60)
        );
        tabPaneDbManagement.getTabs().add(CustomTabGenerator.generate(mainStage, "/fxml/tabs/ingredientTab.fxml", customColumns));
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabDishes() throws Exception {

        if(findTab("dishes")) return;

        List<CustomColumn> customColumns = Arrays.asList(
                new CustomColumn("id", "idProp"),
                new CustomColumn("Название", "dishNameProp", 200),
                new CustomColumn("Категория", "dishCategoryProp"),
                new CustomColumn("Цена", "priceProp"),
                new CustomColumn("Вес", "weightProp")
        );
        tabPaneDbManagement.getTabs().add(CustomTabGenerator.generate(mainStage, "/fxml/tabs/dishTab.fxml", customColumns));
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabPurchaseInvoices(ActionEvent actionEvent) throws Exception {

        if(findTab("purchaseInvoices")) return;

        List<CustomColumn> customColumns = Arrays.asList(
                new CustomColumn("id", "idProp"),
                new CustomColumn("Дата", "invoiceDateProp"),
                new CustomColumn("Контрагент", "contractorProp", 300),
                new CustomColumn("Накладная пост.", "invoiceIdFromContractorProp"),
                new CustomColumn("Сумма", "amountOfInvoiceProp")
        );

        tabPaneDbManagement.getTabs().add(CustomTabGenerator.generate(mainStage, "/fxml/tabs/purchaseInvoicesTab.fxml", customColumns));
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabSalesInvoices(ActionEvent actionEvent) throws Exception {

        if(findTab("salesInvoices")) return;

        List<CustomColumn> customColumns = Arrays.asList(
                new CustomColumn("id", "idProp"),
                new CustomColumn("Дата", "invoiceDateProp"),
                new CustomColumn("Контрагент", "contractorProp", 300),
                new CustomColumn("Ордер", "orderIdProp"),
                new CustomColumn("Сумма", "amountOfInvoiceProp")
        );

        tabPaneDbManagement.getTabs().add(CustomTabGenerator.generate(mainStage, "/fxml/tabs/salesInvoicesTab.fxml", customColumns));
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabContractor(ActionEvent actionEvent) throws Exception {

        if(findTab("contractors")) return;

        List<CustomColumn> customColumns = Arrays.asList(
                new CustomColumn("id", "idProp"),
                new CustomColumn("Название", "contractorNameProp", 300)
        );

        tabPaneDbManagement.getTabs().add(CustomTabGenerator.generate(mainStage, "/fxml/tabs/contractorsTab.fxml", customColumns));
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    public void createTabSettings() throws IOException {

        if(findTab("settings")) return;

        SettingsTab settingsTab = new SettingsTab("Настройки", "settings");
        settingsTab.init(mainStage);
        tabPaneDbManagement.getTabs().add(settingsTab);
        selectionModel.select(tabPaneDbManagement.getTabs().size() - 1);
    }

    private boolean findTab(String id) {

        for(int i=0; i < tabPaneDbManagement.getTabs().size(); i++) {
            if(tabPaneDbManagement.getTabs().get(i).getId().equals(id)) {
                selectionModel.select(i);
                return true;
            }
        }
        return false;
    }

    @FXML private void initialize() {
        selectionModel = tabPaneDbManagement.getSelectionModel();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
