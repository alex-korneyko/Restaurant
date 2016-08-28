package ua.in.dris4ecoder.view.customControls;

/**
 * Created by Alex Korneyko on 25.08.2016 18:03.
 */
public class CustomColumn {

    private String columnName;
    private String columnProperty;
    private int columnWidth;

    public CustomColumn(String columnName, String columnProperty) {
        this.columnName = columnName;
        this.columnProperty = columnProperty;
    }

    public CustomColumn(String columnName, String columnProperty, int columnWidth) {
        this(columnName, columnProperty);
        this.columnWidth = columnWidth;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnProperty() {
        return columnProperty;
    }

    public void setColumnProperty(String columnProperty) {
        this.columnProperty = columnProperty;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }
}
