package com.workers.jdbcexample.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.workers.jdbcexample.dao.WorkerDao;
import com.workers.jdbcexample.model.Worker;
import com.workers.jdbcexample.util.DatabaseConnection;

@Repository
public class WorkerRepository implements WorkerDao {
	private Connection connection;

    public WorkerRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public int add(Worker worker) throws SQLException {
        String addQuery = "insert into worker values(?,?,?,?,?,?,?)";
        int row = 0;
        try (PreparedStatement statement = connection.prepareStatement(addQuery)) {
            statement.setInt(1, worker.getworkerId());
            statement.setString(2, worker.getfirstName());
            statement.setString(3, worker.getlastName());
            statement.setString(4, worker.getSalary());
            statement.setDate(5, worker.getjoiningDate());
            statement.setString(6, worker.getDepartment());
            statement.setString(7, worker.getEmail());
            row = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public void delete(int workerId) throws SQLException {
        String delQuery = "delete from worker where WORKER_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(delQuery)) {
            statement.setInt(1, workerId);
            int rows = statement.executeUpdate();
            System.out.println(rows + "worker deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Worker getWorker(int workerId) throws SQLException {
        Worker worker = null;
        String getQuery = "select * from worker where WORKER_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(getQuery)) {
            statement.setInt(1, workerId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                worker = new Worker(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
                        rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public List<Worker> getWorkers() throws SQLException {
        List<Worker> list = new ArrayList<>();
        String getQuery = "select * from worker";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(new Worker(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
                        rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void update(Worker emp) throws SQLException {
        String updateQuery = "update worker set first_name=?,last_name=?,salary=?,joining_date=?,department=?,email=? where worker_id=?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, emp.getfirstName());
            statement.setString(2, emp.getlastName());
            statement.setString(3, emp.getSalary());
            statement.setDate(4, emp.getjoiningDate());
            statement.setString(5, emp.getDepartment());
            statement.setString(6, emp.getEmail());
            statement.setInt(7, emp.getworkerId());

            int rows = statement.executeUpdate();
            System.out.println(rows + " worker updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

