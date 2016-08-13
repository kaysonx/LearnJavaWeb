package me.utlight.entity;

import java.util.Set;

public class Student {

	private int stu_id;
	private String stu_name;
	private Set<Course> courses;
	
	
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public int getStu_id() {
		return stu_id;
	}
}
