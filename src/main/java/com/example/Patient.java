package com.example;

public class Patient {
	private String instanceID;
	private String patientID;
	private String firstName;
	private String lastName;

	public String getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String pid) {
		this.patientID = pid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fName) {
		this.firstName = fName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lName) {
		this.lastName = lName;
	}
}
