package qa5.enumcomparator;

public class OrderInfo {

    final long id;
    OrderStatus status;
    String description;

    public OrderInfo(long id, OrderStatus status, String description) {
        this.id = id;
        this.status = status;
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
