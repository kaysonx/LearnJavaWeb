package me.utlight.entity;

import java.util.List;

//一对一关联查询demo
public class Classes2 {

	private int id;
	private String name;
	
	private Teacher teacher;
	
	private List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Classes2 [id=" + id + ", name=" + name + ", teacher=" + teacher + ", students=" + students + "]";
	}

	
	
	
}
