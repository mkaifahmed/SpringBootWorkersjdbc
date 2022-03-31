package com.workers.jdbcexample.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workers.jdbcexample.model.Worker;
import com.workers.jdbcexample.repository.WorkerRepository;

@RestController
@RequestMapping("/worker")
public class WorkerController {
	@Autowired
	private WorkerRepository repository;

	@GetMapping("/showWorker")
	public Worker showWorker() {
		try {
			return repository.getWorker(5);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/all/showWorkers")
	public List<Worker> showWorkers() {
		try {
			return repository.getWorkers();
		} catch (SQLException e) {
			e.printStackTrace();
			return List.of();
		}
	}
	
	@GetMapping("/create")
	public String create() {
		try {
			repository.add( new Worker(1, "Kaif", "Ahmed", "10000", new Date(System.currentTimeMillis()), "IT",
					"Kaif@gmail.com"));
			return "Worker Created Successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error Occured while creating worker";
		}
	}
	
	@GetMapping("/update")
	public String update() {
		try {
			repository.update( new Worker(1, "Kaif", "Ahmed", "10000", new Date(System.currentTimeMillis()), "IT",
					"mfs.akash@gmail.com"));
			
			return "Record Updated Successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error occured while updating record";
		}
	}
	
	@GetMapping("/delete")
	public String delete() {
		try {
			repository.delete(1);
			return "Record deleted successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error occured while updating record";
		}
	}
}