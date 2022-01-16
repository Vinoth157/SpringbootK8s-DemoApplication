package com.vinoth.springbootk8s.studentapplication.ParentService.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Check;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"first_name","last_name"}))
@Check(constraints = "GENDER IN ('M', 'F', 'Male', 'Female')")
public class Parent {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long parentId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "gender")
	private String gender;	
	private long addressId;
	
	
}
