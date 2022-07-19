package qa7;

import qa6.QA6;

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
    List<Task> getTasks() throws QA6.ManagerSaveException;

    void updateTask(Task task);
}
