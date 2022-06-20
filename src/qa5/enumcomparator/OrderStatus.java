package qa5.enumcomparator;

import qa5.QA5;

public enum OrderStatus implements Comparable<OrderStatus> {

    CREATED(false, "CONFIRMED"),

    CONFIRMED(false, "ASSEMBLING"),

    ASSEMBLING(false, "SHIPPED"),

    SHIPPED(false, "RECEIVED"),

    RECEIVED(false, "COMPLETED"),

    COMPLETED(true, null),

    CANCELLED(true, null);

    private final boolean isTerminal;
    private final String defaultNextState;

    OrderStatus(boolean isTerminal, String defaultNextState) {
        this.isTerminal = isTerminal;
        this.defaultNextState = defaultNextState;
    }

    public boolean isTerminalState() {
        return isTerminal;
    }

    public OrderStatus getDefaultNextState() {
        return !isTerminal ? OrderStatus.valueOf(this.defaultNextState) : null;
    }

}