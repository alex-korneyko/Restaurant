package ua.in.dris4ecoder.model.businessObjects;

/**
 * Created by Alex Korneyko on 01.08.2016 20:19.
 */
public enum OrderDishStatus {

    IN_QUEUE, /*в очереди*/
    IN_PROCESS, /*выполняется на кухне*/
    PREPARED, /*выполнен на кухне*/
    CLOSED /*закрыт*/;

    @Override
    public String toString() {

        switch (this) {
            case IN_QUEUE: return "in queue";
            case IN_PROCESS: return "in process";
            case PREPARED: return "prepared";
            case CLOSED: return "closed";
            default: throw new IllegalArgumentException();
        }
    }
}
