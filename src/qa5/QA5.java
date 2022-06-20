package qa5;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;

public class QA5 {

    public static void main(String[] args) {
        Map<ObjectWithEnum, Integer> map = new HashMap<>();
        Object object = new Object();
        new ArrayList().add(new int[1]);

        Random random = new Random();
        ObjectWithEnum objectWithEnum = new ObjectWithEnum(
                random.nextLong(),
                DayOfWeek.of(random.nextInt(6) + 1),
                "item #1"
        );
        map.put(objectWithEnum, 42);
        System.out.println(map.get(objectWithEnum));

        objectWithEnum.id = random.nextLong();
        System.out.println(map.get(objectWithEnum));
    }

    private static List<ObjectWithEnum> generate(int count) {
        List<ObjectWithEnum> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            result.add(new ObjectWithEnum(
                    random.nextLong(),
                    DayOfWeek.of(random.nextInt(6) + 1),
                    String.format("item #%d in order of generation", i)
            ));
        }
        return result;
    }

    private static class ObjectWithEnum {
        long id;
        DayOfWeek dayOfWeek;
        String description;

        public ObjectWithEnum(long id, DayOfWeek dayOfWeek, String description) {
            this.id = id;
            this.dayOfWeek = dayOfWeek;
            this.description = description;
        }

        @Override
        public String toString() {
            return "ObjectWithEnum{" +
                    "id=" + id +
                    ", dayOfWeek=" + dayOfWeek +
                    ", description='" + description + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ObjectWithEnum that = (ObjectWithEnum) o;
            return id == that.id && dayOfWeek == that.dayOfWeek && Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }


}

