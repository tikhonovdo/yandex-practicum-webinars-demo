package qa6;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class QA6 {

    // files                \ путаница с русскоязычной терминологией (stream API, I/O streams, threads)
    // lambdas + streams    /

    public static void main(String[] args) {
        List<Integer> ints = List.of(1, 2, 3, 4);
        List<String> intsForSaving = ints.stream().map(Object::toString).collect(Collectors.toList());
        System.out.println(intsForSaving);
    }

    /*
    Из ТЗ на 6 спринт:
      > Исключения вида IOException нужно отлавливать внутри метода save и
      > кидать собственное непроверяемое исключение ManagerSaveException.
      > Благодаря этому можно не менять сигнатуру методов интерфейса менеджера.
    */
    private void shouldThrowIOException() {
        try {
            throw new IOException("boom!");
        } catch (IOException e) {
            throw new ManagerSaveException(e.getMessage());
        }
    }

    public class ManagerSaveException extends RuntimeException {
        public ManagerSaveException(String message) {
            super(message);
        }
    }
}
