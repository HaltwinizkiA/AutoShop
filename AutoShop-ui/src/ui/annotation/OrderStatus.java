package ui.annotation;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    IN_PROGRESS,
    CLOSED,
    CANCELED,
    ACCEPTED,
    ;

    @Override
    public String toString() {
        return "Order Status is "+this.name();
    }
}
