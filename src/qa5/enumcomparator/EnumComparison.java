package qa5.enumcomparator;

import java.util.*;

public class EnumComparison {

    public static void main(String[] args) {
        List<OrderInfo> objects = generate(9);

        for (OrderInfo object : objects)
            System.out.println(object);


//        objects.sort();
        Collections.sort(objects, new OrderInfoComparator());
    }

    private static List<OrderInfo> generate(int count) {
        List<OrderInfo> result = new ArrayList<>();
        var random = new Random();
        OrderStatus[] values = OrderStatus.values();
        for (int i = 0; i < count; i++) {
            result.add(new OrderInfo(
                    random.nextInt(100),
                    values[random.nextInt(values.length)],
                    String.format("item #%d in order of generation", i)
            ));
        }

        return result;
    }

    private static class OrderInfoComparator implements Comparator<OrderInfo> {

        @Override
        public int compare(OrderInfo o1, OrderInfo o2) {
            return Long.compare(o1.id, o2.id);
        }
    }

}
