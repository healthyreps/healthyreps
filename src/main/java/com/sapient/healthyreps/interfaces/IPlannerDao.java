package com.sapient.healthyreps.interfaces;

import com.sapient.healthyreps.entity.Planner;

public interface IPlannerDAO {
	public boolean insertPlanner(Planner planner);

	public boolean deletePlanner(int user_id);

	public Planner getPlanner(int user_id);

	boolean updateExercise_perform(String exercise_perform, int user_id);

	boolean updateDiet_goal(String diet_goal, int user_id);
}
