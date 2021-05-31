package com.sapient.healthyreps.entity;

public class Planner {

	private int userId;
	private String timing;
	private String exercisePerform;
	private String target;
	private String dietGoal;
	private String date;

	public int getUserId() {
		return userId;
	}

	public Planner() {

	}

	public Planner(int userId, String timing, String exercisePerform, String target, String dietGoal, String date) {
		super();
		this.userId = userId;
		this.timing = timing;
		this.exercisePerform = exercisePerform;
		this.target = target;
		this.dietGoal = dietGoal;
		this.date = date;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public String getExercisePerform() {
		return exercisePerform;
	}

	public void setExercisePerform(String exercisePerform) {
		this.exercisePerform = exercisePerform;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDietGoal() {
		return dietGoal;
	}

	public void setDietGoal(String dietGoal) {
		this.dietGoal = dietGoal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Planner [userId=" + userId + ", timing=" + timing + ", exercisePerform=" + exercisePerform
				+ ", target=" + target + ", dietGoal=" + dietGoal + ", date=" + date + "]";
	}

}
