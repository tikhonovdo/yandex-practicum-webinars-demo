package qa7;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    private Map<Integer, Task> tasks = new HashMap<>();
    private Set<Task> sortedByStartTimeTasks = new TreeSet<>(Comparator.comparing(Task::getStartTime));
    private int idGenerator = 1;

    @Override
    public int addNewTask(Task task) {
        Task newTask = new Task(task);
        newTask.setId(getNextId());
        tasks.put(newTask.getId(), newTask);
        sortedByStartTimeTasks.add(newTask);
        return newTask.getId();
    }

    @Override
    public Task getTask(int id) {
        Task result = tasks.get(id);
        if (result == null) {
            return new Task();
        }
        return result;
    }

    public List<Task> getPrioritizedTasks() {
        return sortedByStartTimeTasks.stream().toList();
    }

    @Override
    public List<Task> getTasks() {
        return tasks.values()
                .stream()
                .sorted(Comparator.comparingInt(Task::getId))
                .toList();
    }

    @Override
    public void updateTask(Task task) {
        Task newTask = new Task(task);
        newTask.setId(task.getId());
        tasks.put(newTask.getId(), newTask);
    }

    private int getNextId() {
        return idGenerator++;
    }
}
