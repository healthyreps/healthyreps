package com.sapient.healthyreps.entity;

public class Planner {

	private int user_id;
	private String timing;
	private String exercise_perform;
	private String target;
	private String diet_goal;
	private String date;

	public int getUser_id() {
		return user_id;
	}

	public Planner() {

	}

	public Planner(int user_id, String timing, String exercise_perform, String target, String diet_goal, String date) {
		super();
		this.user_id = user_id;
		this.timing = timing;
		this.exercise_perform = exercise_perform;
		this.target = target;
		this.diet_goal = diet_goal;
		this.date = date;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public String getExercise_perform() {
		return exercise_perform;
	}

	public void setExercise_perform(String exercise_perform) {
		this.exercise_perform = exercise_perform;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDiet_goal() {
		return diet_goal;
	}

	public void setDiet_goal(String diet_goal) {
		this.diet_goal = diet_goal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Planner [user_id=" + user_id + ", timing=" + timing + ", exercise_perform=" + exercise_perform
				+ ", target=" + target + ", diet_goal=" + diet_goal + ", date=" + date + "]";
	}

}
