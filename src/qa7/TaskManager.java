package qa7;

import java.util.List;

public interface TaskManager {

    /**
     * Добавляет новое задание
     * @return id созданного задания
     */
    int addNewTask(Task task);

    Task getTask(int id);

    List<Task> getPrioritizedTasks();

    /**
     * @return список заданий, упорядоченных по id
     */
    List<Task> getTasks();
}
