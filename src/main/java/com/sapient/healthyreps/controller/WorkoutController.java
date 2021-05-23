package com.sapient.healthyreps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.WorkoutDAO;
import com.sapient.healthyreps.entity.Workout;

@RestController
public class WorkoutController {

	@Autowired
	WorkoutDAO workoutdao;

	@PostMapping("/workout")
	public boolean insertWork(@RequestBody Workout workout) {
		return workoutdao.insertWork(workout);
	}

	@DeleteMapping("/workout/{user_id}")
	public boolean deleteWork(@PathVariable int user_id) {
		return workoutdao.deleteWork(user_id);
	}

	@RequestMapping(value = "/workout/{user_id}", method = RequestMethod.GET)
	public Workout getWork(@PathVariable int user_id) {
		return workoutdao.getSetsReps(user_id);

	}

	@PutMapping("/workout/{user_id}")
	public boolean updateWork(@RequestBody Workout workout, @PathVariable int user_id) {
		return workoutdao.updateWork(workout, user_id);
	}
}
