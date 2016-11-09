package ua.in.dris4ecoder.model.businessObjects;

/**
 * Created by Alex Korneyko on 01.08.2016 20:19.
 */
public enum OrderDishStatus {

    EDITING, /*редактируется*/
    IN_QUEUE, /*в очереди*/
    IN_PROCESS, /*выполняется на кухне*/
    PREPARED, /*выполнен на кухне*/
    CLOSED /*закрыт*/;

    @Override
    public String toString() {

        switch (this) {
            case EDITING: return "Редактирование";
            case IN_QUEUE: return "В очереди";
            case IN_PROCESS: return "Выполняется";
            case PREPARED: return "Готов";
            case CLOSED: return "Выдан";
            default: throw new IllegalArgumentException();
        }
    }
}
