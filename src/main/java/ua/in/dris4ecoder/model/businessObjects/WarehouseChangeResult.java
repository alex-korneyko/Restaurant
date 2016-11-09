package ua.in.dris4ecoder.model.businessObjects;

/**
 * Created by Alex Korneyko on 06.11.2016 14:07.
 */
public class WarehouseChangeResult {

    private boolean changeSuccessfully;

    private int invoiceId;

    private Ingredient ingredient;

    private String message;

    public WarehouseChangeResult() {
    }

    public WarehouseChangeResult(boolean changeSuccessfully) {
        this.changeSuccessfully = changeSuccessfully;
    }

    public WarehouseChangeResult(boolean changeSuccessfully, int invoiceId) {
        this.changeSuccessfully = changeSuccessfully;
        this.invoiceId = invoiceId;
    }

    public WarehouseChangeResult(boolean changeSuccessfully, String message) {
        this.changeSuccessfully = changeSuccessfully;
        this.message = message;
    }

    public WarehouseChangeResult(boolean changeSuccessfully, int invoiceId, String message) {
        this.changeSuccessfully = changeSuccessfully;
        this.invoiceId = invoiceId;
        this.message = message;
    }

    public WarehouseChangeResult(boolean changeSuccessfully, Ingredient ingredient, String message) {
        this.changeSuccessfully = changeSuccessfully;
        this.ingredient = ingredient;
        this.message = message;
    }

    public boolean isChangeSuccessfully() {
        return changeSuccessfully;
    }

    public void setChangeSuccessfully(boolean changeSuccessfully) {
        this.changeSuccessfully = changeSuccessfully;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
