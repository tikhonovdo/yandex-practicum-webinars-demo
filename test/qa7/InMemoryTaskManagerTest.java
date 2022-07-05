package qa7;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static qa7.Status.NEW;

public class InMemoryTaskManagerTest {


    private InMemoryTaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    public void addNewTaskTest() {
        // Given
        Task task = new Task();
        task.setName("Task name");
        task.setDescription("Task description");

        // When
        int taskId = taskManager.addNewTask(task);

        // Then
        assertEquals(1, taskId);
    }

    @Test
    public void getTaskTest() {
        // Given
        Task task = new Task("Task name", "Task description", NEW);
        int taskId = taskManager.addNewTask(task);

        // When
        Task actualTask = taskManager.getTask(taskId);

        // Then
        assertEquals(task.getName(), actualTask.getName());
        assertEquals(task.getDescription(), actualTask.getDescription());
        assertEquals(task.getStatus(), actualTask.getStatus());
    }

    @Test
    public void getTaskNotFoundTest() {
        // Given
        Task task = new Task("Task name", "Task description", NEW);
        taskManager.addNewTask(task);

        // When
        Task actualTask = taskManager.getTask(2);

        // Then
        assertNull(actualTask, "Задачи на возвращаются.");
    }

    @Test
    void getTasksTest() {
        Task task1 = new Task("Task name1", "Task description1", NEW);
        Task task2 = new Task("Task name2", "Task description2", NEW);

        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);
        final List<Task> tasks = taskManager.getTasks();

        Task actualTask = tasks.get(1);
        assertNotNull(tasks, "Задачи на возвращаются.");
        assertEquals(2, tasks.size(), "Неверное количество задач.");
        assertEquals(task2.getName(), actualTask.getName(), "Имена задач не совпадают.");
        assertEquals(task2.getDescription(), actualTask.getDescription(), "Описание задачи не совпадают.");
        assertEquals(task2.getStatus(), actualTask.getStatus(), "Статусы задач не совпадают.");
    }

    @Test
    void getPrioritizedTasksTest() {
        Task task1 = new Task(1, LocalDateTime.of(
                DateTimeFormatter.ISO_LOCAL_DATE.parse("2022-07-05", LocalDate::from), LocalTime.MIDNIGHT
        ), Duration.ofDays(3));
        Task task2 = new Task(2, LocalDateTime.of(
                DateTimeFormatter.ISO_LOCAL_DATE.parse("2022-07-07", LocalDate::from), LocalTime.MIDNIGHT
        ), Duration.ofDays(1));

        taskManager.addNewTask(task2);
        taskManager.addNewTask(task1);
        final List<Task> tasks = taskManager.getPrioritizedTasks();

        // это только один из случаев пересечения
        boolean isTask1IntersectsWithTask2 = task1.getEndTime().isBefore(task2.getStartTime()) &&
                task1.getStartTime().isBefore(task2.getStartTime());

        Task actualTask = tasks.get(1);
        assertEquals(2, tasks.size(), "Неверное количество задач.");
        assertEquals(task2.getName(), actualTask.getName(), "Имена задач не совпадают.");
        assertEquals(task2.getDescription(), actualTask.getDescription(), "Описание задачи не совпадают.");
        assertEquals(task2.getStatus(), actualTask.getStatus(), "Статусы задач не совпадают.");
    }

}
