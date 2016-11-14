package ua.in.dris4ecoder.model.businessObjects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Alex Korneyko on 30.08.2016 11:58.
 */
@Entity
@Table(name = "service.invoices")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Invoice implements BusinessObject {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "date_of_waybill")
    private LocalDate invoiceDate;

    @Column(name = "amount_of_invoice")
    private double amountOfInvoice;

    @ElementCollection
    @CollectionTable(name = "service.invoice_weights")
    @Column(name = "ingredient_weight")
    @MapKeyJoinColumn(name = "ingredient_id", referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private Map<Ingredient, Double> ingredientWeightPerInvoice = new HashMap<>();


    @ElementCollection
    @CollectionTable(name = "service.invoice_prices")
    @Column(name = "ingredient_price")
    @MapKeyJoinColumn(name = "ingredient_id")
    @Fetch(FetchMode.JOIN)
    private Map<Ingredient, Double> ingredientCostPerInvoice = new HashMap<>();

    @Column(name = "is_auto_price")
    private boolean autoPrice;

    @Transient
    private SimpleIntegerProperty idProp = new SimpleIntegerProperty();
    @Transient
    private SimpleStringProperty invoiceDateProp = new SimpleStringProperty();
    @Transient
    private SimpleDoubleProperty amountOfInvoiceProp = new SimpleDoubleProperty();

    public Invoice() {
        this.invoiceDate = LocalDate.now();
    }

    public Invoice(boolean autoPrice) {
        this();
        this.autoPrice = autoPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        idProp.set(id);
        this.id = id;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        invoiceDateProp.set(invoiceDate.toString());
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getAmountOfInvoice() {

        if (autoPrice) {
            amountOfInvoice = ingredientCostPerInvoice.keySet().stream()
                    .mapToDouble(key -> ingredientCostPerInvoice.get(key) * ingredientWeightPerInvoice.get(key)).sum();
        }

        return (new BigDecimal(amountOfInvoice)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setAmountOfInvoice(double amountOfInvoice) {
        amountOfInvoiceProp.set(amountOfInvoice);
        this.amountOfInvoice = amountOfInvoice;
    }

    public void addIngredient(Ingredient ingredient) {

        if (ingredientCostPerInvoice.containsKey(ingredient)) {

            double oldSum = ingredientCostPerInvoice.get(ingredient) * ingredientWeightPerInvoice.get(ingredient);
            double newSum = ingredient.getIngredientPrice() * ingredient.getIngredientWeight();

            ingredientCostPerInvoice.put(ingredient, (oldSum + newSum) / (ingredientWeightPerInvoice.get(ingredient) + ingredient.getIngredientWeight()));

            ingredientWeightPerInvoice.put(ingredient, ingredientWeightPerInvoice.get(ingredient) + ingredient.getIngredientWeight());
        } else {
            ingredientCostPerInvoice.put(ingredient, ingredient.getIngredientPrice());
            ingredientWeightPerInvoice.put(ingredient, ingredient.getIngredientWeight());
        }

        this.amountOfInvoice = getAmountOfInvoice().doubleValue();
    }

    public void removeIngredient(Ingredient ingredient) {

        ingredientCostPerInvoice.remove(ingredient);
        ingredientWeightPerInvoice.remove(ingredient);
    }

    public List<Ingredient> getIngredients() {

        List<Ingredient> ingredients = new ArrayList<>();

        for (Ingredient ingredient: ingredientWeightPerInvoice.keySet()) {
            ingredient.setIngredientWeight(ingredientWeightPerInvoice.get(ingredient));
            ingredient.setIngredientPrice(ingredientCostPerInvoice.get(ingredient));
            ingredients.add(ingredient);
        }

        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        ingredientWeightPerInvoice.clear();
        ingredientCostPerInvoice.clear();
        ingredients.forEach(ingredient -> {
            ingredientWeightPerInvoice.put(ingredient, ingredient.getIngredientWeight());
            ingredientCostPerInvoice.put(ingredient, ingredient.getIngredientPrice());
        });
    }

    public Ingredient getIngredientWithParamsInInvoice(Ingredient ingredient) {

        ingredient.setIngredientPrice(ingredientCostPerInvoice.get(ingredient));
        ingredient.setIngredientWeight(ingredientWeightPerInvoice.get(ingredient));

        return ingredient;
    }

    public boolean isAutoPrice() {
        return autoPrice;
    }

    public void setAutoPrice(boolean autoPrice) {
        this.autoPrice = autoPrice;
    }

    public SimpleIntegerProperty idPropProperty() {
        idProp.set(id);
        return idProp;
    }

    public SimpleStringProperty invoiceDatePropProperty() {
        invoiceDateProp.set(invoiceDate.toString());
        return invoiceDateProp;
    }

    public SimpleDoubleProperty amountOfInvoicePropProperty() {
        amountOfInvoiceProp.set(amountOfInvoice);
        return amountOfInvoiceProp;
    }


    public void clearAllIngredients() {

        ingredientCostPerInvoice.clear();
        ingredientWeightPerInvoice.clear();
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceDate=" + invoiceDate +
                ", amountOfInvoice=" + amountOfInvoice +
                ", ingredients=" + ingredientWeightPerInvoice.keySet() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;

        Invoice invoice = (Invoice) o;

        if (Double.compare(invoice.amountOfInvoice, amountOfInvoice) != 0) return false;
        return invoiceDate != null ? invoiceDate.equals(invoice.invoiceDate) : invoice.invoiceDate == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = invoiceDate != null ? invoiceDate.hashCode() : 0;
        temp = Double.doubleToLongBits(amountOfInvoice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
