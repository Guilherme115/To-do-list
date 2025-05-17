package org.project.dao;

import org.project.model.Task;
import org.project.util.DButil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public List<Task> getAlltask() {
        List<Task> tasks = new ArrayList<>();

        try {
            ResultSet rs = DButil.createconexion("SELECT * FROM task");
            while (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getNString("title"));
                t.setDescription(rs.getNString("description"));
                t.setCompleted(rs.getBoolean("isCompleted"));

                tasks.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return tasks;

    }

    public void addToBank(Task task) {
        try {
            PreparedStatement ps = DButil.addDataBase("INSERT INTO task (title, description, isCompleted) VALUES (?, ?, ?)");
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.getCompleted());
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        public void deleteToBank(int id) {
        try {
            PreparedStatement ps = DButil.addDataBase("DELETE FROM task WHERE id = ?");
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhuma task foi deletada. ID pode não existir.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar task no banco de dados", e);
        }
        }


    public void atualizarBanco(int id) {
        String sql = "UPDATE task SET isCompleted = ? WHERE id = ?";

        try (PreparedStatement stmt = DButil.addDataBase(sql)) {
            stmt.setBoolean(1, true);  // Marca como concluída
            stmt.setInt(2, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhuma task foi atualizada. ID pode não existir.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao marcar task como concluída no banco de dados", e);
        }
    }



}
