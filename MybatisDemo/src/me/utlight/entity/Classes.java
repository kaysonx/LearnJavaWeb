package me.utlight.entity;

//һ��һ������ѯdemo
public class Classes {

	private int id;
	private String name;
	
	private Teacher teacher;

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
		return "Class [id=" + id + ", name=" + name + ", teacher=" + teacher + "]";
	}
	
	
}
