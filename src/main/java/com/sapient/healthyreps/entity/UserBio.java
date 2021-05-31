package com.sapient.healthyreps.entity;

public class UserBio {
	private int userId;
	private int weight;
	private int height;
	private String gender;
	private int waist;
	private int workoutPlanInDays;
	private int reputation;

	public int getUserId() {
		return userId;
	}

	public void setUser_id(int user_id) {
		this.userId = user_id;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getWaist() {
		return waist;
	}

	public void setWaist(int waist) {
		this.waist = waist;
	}

	public int getWorkoutPlanInDays() {
		return workoutPlanInDays;
	}

	public void setWorkout_plan_in_days(int workoutPlanInDays) {
		this.workoutPlanInDays = workoutPlanInDays;
	}

	public UserBio(int user_id, int weight, int height, String gender, int waist, int workoutPlanInDays) {
		super();
		this.userId = user_id;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
		this.waist = waist;
		this.workoutPlanInDays = workoutPlanInDays;
	}

	public UserBio() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "user [user_id=" + userId + ", weight=" + weight + ", height=" + height + ", gender=" + gender
				+ ", waist=" + waist + ", workout_plan=" + workoutPlanInDays + "," + "reputation=" + reputation
				+ "]";
	}

}
