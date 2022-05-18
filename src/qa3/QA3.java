package qa3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QA3 {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Collection<Integer> numberCollection = numbers;
        Status status = Status.valueOf("DONE");
        System.out.println(Status.values()[status.ordinal()]);
    }


}

