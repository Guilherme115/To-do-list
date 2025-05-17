package org.project.service;

import org.project.dao.TaskDAO;
import org.project.excpetion.NonEmpty;
import org.project.model.Task;

import java.util.List;

public class TaskService {

    TaskDAO taskDAO = new TaskDAO();

    public List<Task> getAllTasks() {
        return taskDAO.getAlltask();
    }

    public void AdicionarTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new NonEmpty("Título da tarefa é obrigatório!");

        }

        taskDAO.addToBank(task);

    }

    public void RemoverID(int id) throws IllegalAccessException {


        taskDAO.deleteToBank(id);
    }

    public void AtualizarTask(int id) {


        taskDAO.atualizarBanco(id);

    }
}


