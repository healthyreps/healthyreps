package com.sapient.healthyreps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.PlannerDAO;
import com.sapient.healthyreps.entity.Planner;

@CrossOrigin
@RestController
public class PlannerController {
	@Autowired
	PlannerDAO plannerdao;

	@PostMapping("/api/planner")
	public boolean insertPlanner(@RequestBody Planner planner) {
		return plannerdao.insertPlanner(planner);
	}

	@DeleteMapping("/api/planner/{user_id}")
	public boolean deletePlanner(@PathVariable int user_id) {
		return plannerdao.deletePlanner(user_id);
	}

	@GetMapping("/api/planner/{user_id}")
	public Planner getUser(@PathVariable int user_id) {
		return plannerdao.getPlanner(user_id);
	}

	@PutMapping("/api/planner/updateExercise/{user_id}")
	public boolean updateExercise_perform(@RequestBody String exercise_perform, @PathVariable int user_id) {
		return plannerdao.updateExercise_perform(exercise_perform, user_id);
	}

	@PutMapping("/api/planner/updateDiet/{user_id}")
	public boolean updateDiet_goal(@RequestBody String diet_goal, @PathVariable int user_id) {
		return plannerdao.updateDiet_goal(diet_goal, user_id);
	}

}
