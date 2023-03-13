package code.smartgate.model;

import java.time.LocalDate;

import code.smartgate.entity.Visit;

public class VisitModelHelper extends Visit{
	private LocalDate fromDate;
	private LocalDate toDate;
	private String visitorPhotograph;
	private String vehiclePhotograph;
	//It is transient variable to hold visitor or meeting person mobile number
	private Long anyMobileNumber;
	//It is transient variable to hold optional meeting person username
	private String meetingPersonUsername2;
	
	public String getVisitorPhotograph() {
		return visitorPhotograph;
	}
	public void setVisitorPhotograph(String visitorPhotograph) {
		this.visitorPhotograph = visitorPhotograph;
	}
	public String getVehiclePhotograph() {
		return vehiclePhotograph;
	}
	public void setVehiclePhotograph(String vehiclePhotograph) {
		this.vehiclePhotograph = vehiclePhotograph;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public Long getAnyMobileNumber() {
		return anyMobileNumber;
	}
	public void setAnyMobileNumber(Long anyMobileNumber) {
		this.anyMobileNumber = anyMobileNumber;
	}
	
	public String getMeetingPersonUsername2() {
		return meetingPersonUsername2;
	}
	public void setMeetingPersonUsername2(String meetingPersonUsername2) {
		this.meetingPersonUsername2 = meetingPersonUsername2;
	}
	@Override
	public String toString() {
		return "VisitModelHelper [fromDate=" + fromDate + ", toDate=" + toDate + ", visitorPhotograph="
				+ visitorPhotograph + ", vehiclePhotograph=" + vehiclePhotograph + ", anyMobileNumber="
				+ anyMobileNumber + ", meetingPersonUsername2=" + meetingPersonUsername2 + "]";
	}
	
	
	
	

	
}
