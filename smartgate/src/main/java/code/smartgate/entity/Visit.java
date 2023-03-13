package code.smartgate.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import code.user.entity.User;

@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /*visitor name*/
    private String visitorFirstName;
    private String visitorMiddleName;
    private String visitorLastName;
    private String meetingPersonName;
    private Long visitorMobileNumber;
    private Long meetingPersonMobileNumber;
    private String meetingPersonUsername;
    private String location;
    private String purpose;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdTime;
    private String vehicleNumber;
    //visit approved by gate admin
    private boolean visitApprovedByGa;
    //visit approved by University Of Hyderabad stake holder
    private Boolean visitApprovedByUohSh;
    //exit approved by gate admin
    private boolean exitApprovedByGa;
    private boolean canceledByGa;
    
    @ManyToOne
    @JsonIgnore
    private User user;
    
    @Transient
    private static short expiryDuration;


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getVisitorFirstName() {
		return visitorFirstName;
	}



	public void setVisitorFirstName(String visitorFirstName) {
		this.visitorFirstName = visitorFirstName;
	}



	public String getVisitorMiddleName() {
		return visitorMiddleName;
	}



	public void setVisitorMiddleName(String visitorMiddleName) {
		this.visitorMiddleName = visitorMiddleName;
	}



	public String getVisitorLastName() {
		return visitorLastName;
	}



	public void setVisitorLastName(String visitorLastName) {
		this.visitorLastName = visitorLastName;
	}



	public String getMeetingPersonName() {
		return meetingPersonName;
	}



	public void setMeetingPersonName(String meetingPersonName) {
		this.meetingPersonName = meetingPersonName;
	}



	public Long getVisitorMobileNumber() {
		return visitorMobileNumber;
	}



	public void setVisitorMobileNumber(Long visitorMobileNumber) {
		this.visitorMobileNumber = visitorMobileNumber;
	}



	public Long getMeetingPersonMobileNumber() {
		return meetingPersonMobileNumber;
	}



	public void setMeetingPersonMobileNumber(Long meetingPersonMobileNumber) {
		this.meetingPersonMobileNumber = meetingPersonMobileNumber;
	}



	public String getMeetingPersonUsername() {
		return meetingPersonUsername;
	}



	public void setMeetingPersonUsername(String meetingPersonUsername) {
		this.meetingPersonUsername = meetingPersonUsername;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getPurpose() {
		return purpose;
	}



	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}



	public LocalDateTime getStartTime() {
		return startTime;
	}



	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}



	public LocalDateTime getEndTime() {
		return endTime;
	}



	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}



	public LocalDateTime getCreatedTime() {
		return createdTime;
	}



	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}



	public String getVehicleNumber() {
		return vehicleNumber;
	}



	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}



	public boolean isVisitApprovedByGa() {
		return visitApprovedByGa;
	}



	public void setVisitApprovedByGa(boolean visitApprovedByGa) {
		this.visitApprovedByGa = visitApprovedByGa;
	}



	public Boolean getVisitApprovedByUohSh() {
		return visitApprovedByUohSh;
	}



	public void setVisitApprovedByUohSh(Boolean visitApprovedByUohSh) {
		this.visitApprovedByUohSh = visitApprovedByUohSh;
	}



	public boolean isExitApprovedByGa() {
		return exitApprovedByGa;
	}



	public void setExitApprovedByGa(boolean exitApprovedByGa) {
		this.exitApprovedByGa = exitApprovedByGa;
	}



	public boolean isCanceledByGa() {
		return canceledByGa;
	}



	public void setCanceledByGa(boolean canceledByGa) {
		this.canceledByGa = canceledByGa;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public static short getExpiryDuration() {
		return expiryDuration;
	}



	public static void setExpiryDuration(short expiryDuration) {
		Visit.expiryDuration = expiryDuration;
	}



	@Override
	public String toString() {
		return "Visit [id=" + id + ", visitorFirstName=" + visitorFirstName + ", visitorMiddleName=" + visitorMiddleName
				+ ", visitorLastName=" + visitorLastName + ", meetingPersonName=" + meetingPersonName
				+ ", visitorMobileNumber=" + visitorMobileNumber + ", meetingPersonMobileNumber="
				+ meetingPersonMobileNumber + ", meetingPersonUsername=" + meetingPersonUsername + ", location="
				+ location + ", purpose=" + purpose + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", createdTime=" + createdTime + ", vehicleNumber=" + vehicleNumber + ", visitApprovedByGa="
				+ visitApprovedByGa + ", visitApprovedByUohSh=" + visitApprovedByUohSh + ", exitApprovedByGa="
				+ exitApprovedByGa + ", canceledByGa=" + canceledByGa + ", user=" + user + "]";
	}



	public Visit() {
		super();
	}	
    
    
}
