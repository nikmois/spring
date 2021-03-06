package com.example.demo.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private int ID;
	protected String FirstName;
	protected String LastName;
	protected LocalDate Birthday;
	protected double Salary;
}
