package com.sapient.healthyreps.entity;

public class UserBio {
	private int userID;
	private int weight;
	private int height;
	private String gender;
	private int waist;
	private int workout_plan_in_days;
	private int reputation;

	public int getUser_id() {
		return userID;
	}

	public void setUser_id(int user_id) {
		this.userID = user_id;
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

	public int getWorkout_plan_in_days() {
		return workout_plan_in_days;
	}

	public void setWorkout_plan_in_days(int workout_plan_in_days) {
		this.workout_plan_in_days = workout_plan_in_days;
	}

	public UserBio(int user_id, int weight, int height, String gender, int waist, int workout_plan_in_days) {
		super();
		this.userID = user_id;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
		this.waist = waist;
		this.workout_plan_in_days = workout_plan_in_days;
	}

	public UserBio() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "user [user_id=" + userID + ", weight=" + weight + ", height=" + height + ", gender=" + gender
				+ ", waist=" + waist + ", workout_plan=" + workout_plan_in_days + "," + "reputation=" + reputation
				+ "]";
	}

}
