package com.nishh.springbatchexample1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private Integer id;
	private String name;

	private String dept;

	private String salary;

	public User() {

	}

	public User(Integer id, String name, String dept, String salary) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
	}

	public String getDept() {
		return dept;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSalary() {
		return salary;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dept=" + dept + ", salary=" + salary + "]";
	}

}
