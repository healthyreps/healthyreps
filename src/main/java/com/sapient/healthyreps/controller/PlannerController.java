package com.sapient.healthyreps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.PlannerDao;
import com.sapient.healthyreps.entity.Planner;

@RestController
public class PlannerController {
	@Autowired
	PlannerDao plannerdao;
	
	@PostMapping("/planner")
	public boolean insertPlanner(@RequestBody Planner planner) {
		return plannerdao.insertPlanner(planner);
	}
	@DeleteMapping("/planner/{user_id}")
	public boolean deletePlanner(@PathVariable int user_id) {
		return plannerdao.deletePlanner(user_id);
	}
	@GetMapping("/planner/{user_id}")
	public Planner getUser(@PathVariable int user_id) {
		return plannerdao.getPlanner(user_id);
	}
	@PutMapping("/planner/{user_id}")
	public boolean updateExercise_perform(@RequestBody String exercise_perform, int user_id) {
		return plannerdao.updateExercise_perform(exercise_perform, user_id);
	}
	@PutMapping("/planner/{user_id}")
	public boolean updateDiet_goal(@RequestBody String diet_goal, int user_id) {
		return plannerdao.updateDiet_goal(diet_goal, user_id);
	}
	
}
