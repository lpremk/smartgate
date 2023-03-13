package code.smartgate.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import code.smartgate.entity.Visit;
import code.smartgate.model.VisitModelHelper;
import code.smartgate.repository.VisitRepository;
import code.user.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class VisitController implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private VisitRepository visitRepository;
	
	@PostMapping("/visit/create")
	public ResponseEntity<String> createVisit(
			@RequestBody VisitModelHelper visitModelHelper
			) {
//		System.out.println("visit create is called");
		String username="user1";
		Visit visit=new Visit();
		visit.setId(visitModelHelper.getId());
		visit.setVisitorFirstName(visitModelHelper.getVisitorFirstName());
		visit.setVisitorMiddleName(visitModelHelper.getVisitorMiddleName());
		visit.setVisitorLastName(visitModelHelper.getVisitorLastName());
		visit.setLocation(visitModelHelper.getLocation());
		visit.setMeetingPersonName(visitModelHelper.getMeetingPersonName());
		visit.setCreatedTime(LocalDateTime.now());
		visit.setPurpose(visitModelHelper.getPurpose());
		visit.setVisitorMobileNumber(visitModelHelper.getVisitorMobileNumber());
		visit.setMeetingPersonMobileNumber(visitModelHelper.getMeetingPersonMobileNumber());
		visit.setVehicleNumber(visitModelHelper.getVehicleNumber());
		visit.setVisitApprovedByGa(false);
		visit.setVisitApprovedByUohSh(false);
		visit.setExitApprovedByGa(false);
		visit.setStartTime(visitModelHelper.getStartTime());
		visit.setEndTime(visitModelHelper.getEndTime());
//		visit.setMeetingPersonUsername(visitModelHelper.getMeetingPersonUsername());
		
		/*---Start meetingPersonUsername---*/
		/**
		 * set meeting person username if it exist
		 * other wise check for meeting person username2 and set it if it exist
		 * if both not exist set meeting person username as null.
		 */
		String meetingPersonUsername=visitModelHelper.getMeetingPersonUsername();
		String meetingPersonUsername2=visitModelHelper.getMeetingPersonUsername2();
		boolean meetingPersonUsernameExist=userRepository.findByUsername(meetingPersonUsername).isPresent();
		boolean meetingPersonUsername2Exist=userRepository.findByUsername(meetingPersonUsername2).isPresent();
		
		if(meetingPersonUsernameExist) {
			visit.setMeetingPersonUsername(meetingPersonUsername);
		}
		else {
			if(meetingPersonUsername2Exist) {
				visit.setMeetingPersonUsername(meetingPersonUsername2);
			}
			else {
				visit.setMeetingPersonUsername(null);
			}
		}
		/*---End meetingPersonUsername---*/
		
//		Optional<User> userOpt=userRepository.findByUsername(username);  
		visit.setCreatedTime(LocalDateTime.now());
		visitRepository.save(visit);
//		System.out.println(visit.toString());
//		System.out.println(visitModelHelper.toString());
		
		
		return new ResponseEntity<>("""
				{ 
				"created":true,
				"message":"visit created successfully"
				}
				""", HttpStatus.OK);
	}
	
	@PostMapping("/visit/read/{visitId}")
	public ResponseEntity<VisitModelHelper> createVisit(
			@PathVariable Long visitId
			) {
		Visit visit=visitRepository.findById(visitId).get();
		VisitModelHelper visitModelHelper=new VisitModelHelper();
		visitModelHelper.setId(visit.getId());
		visitModelHelper.setVisitorFirstName(visit.getVisitorFirstName());
		visitModelHelper.setVisitorMiddleName(visit.getVisitorMiddleName());
		visitModelHelper.setVisitorLastName(visit.getVisitorLastName());
		visitModelHelper.setLocation(visit.getLocation());
		visitModelHelper.setMeetingPersonName(visit.getMeetingPersonName());
		visitModelHelper.setCreatedTime(visit.getCreatedTime());
		visitModelHelper.setPurpose(visit.getPurpose());
		visitModelHelper.setVisitorMobileNumber(visit.getVisitorMobileNumber());
		visitModelHelper.setVehicleNumber(visit.getVehicleNumber());
		visitModelHelper.setVisitApprovedByGa(visit.isVisitApprovedByGa());
		visitModelHelper.setVisitApprovedByUohSh(visit.getVisitApprovedByUohSh());
		visitModelHelper.setExitApprovedByGa(visit.isExitApprovedByGa());
		visitModelHelper.setStartTime(visit.getStartTime());
		visitModelHelper.setEndTime(visit.getEndTime());
		visitModelHelper.setMeetingPersonUsername(visit.getMeetingPersonUsername());
		return new ResponseEntity<>(visitModelHelper, HttpStatus.OK);
	}
	/**
	 * This api is to display search normal visit by visit id to Gate Admin
	 */
	@PostMapping("/visit/read-normal/{visitId}")
	public ResponseEntity<Visit> createNormalVisit(
			@PathVariable Long visitId
			) {
		Visit visit=null;
		
		boolean canceled=visitRepository.findById(visitId).get().isCanceledByGa();
		if(canceled==true) {
			//do nothing
		}
		if(canceled==false) {
			visit=visitRepository.findById(visitId).get();
		}
		
		return new ResponseEntity<>(visit, HttpStatus.OK);
	}
	/**
	 * This api is to display search Canceled visit by visit id to Gate Admin
	 */
	@PostMapping("/visit/read-canceled/{visitId}")
	public ResponseEntity<Visit> createCanceledVisit(
			@PathVariable Long visitId
			) {
		Visit visit=null;
		
		boolean canceled=visitRepository.findById(visitId).get().isCanceledByGa();
		if(canceled==true) {
			visit=visitRepository.findById(visitId).get();
		}
		if(canceled==false) {
			//do nothing
		}
		
		return new ResponseEntity<>(visit, HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-today-pending-visits")
	public ResponseEntity<List<Visit>> readAllTodayPendingVisits(
			) {
		List<Visit> visits=visitRepository.getTodayPendingVisits(LocalDate.now()); 
			
		return new ResponseEntity<>(visits, HttpStatus.OK); 
	}
	
	@PostMapping("/visit/read-all-from-to-pending-visits")
	public ResponseEntity<List<Visit>> readAllFromToPendingVisits(
			@RequestBody VisitModelHelper visitModelHelper) {
		System.out.println("exexuted at "+LocalDateTime.now());
		List<Visit> visits=visitRepository.getFromToPendingVisits(visitModelHelper.getFromDate(),visitModelHelper.getToDate()); 
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-today-pending-exits")
	public ResponseEntity<List<Visit>> readAllTodayPendingExits() {
		List<Visit> visits=visitRepository.getTodayPendingExits(LocalDate.now()); 
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-from-to-pending-exits")
	public ResponseEntity<List<Visit>> readAllFromToPendingExits(
			@RequestBody VisitModelHelper visitModelHelper) {
		
		List<Visit> visits=visitRepository.getFromToPendingExits(visitModelHelper.getFromDate(),visitModelHelper.getToDate()); 
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-today-successful-exits")
	public ResponseEntity<List<Visit>> readAllTodaySuccessfulExits() {
		List<Visit> visits=visitRepository.getTodaySuccessfulExits(LocalDate.now()); 
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-from-to-successful-exits")
	public ResponseEntity<List<Visit>> readAllFromToSuccessfulExits(
			@RequestBody VisitModelHelper visitModelHelper) {
		
		List<Visit> visits=visitRepository.getFromToSuccessfulExits(visitModelHelper.getFromDate(),visitModelHelper.getToDate()); 
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-today-canceled-visits")
	public ResponseEntity<List<Visit>> readAllTodayCanceledVisits() {
		List<Visit> visits=visitRepository.getTodayCanceledVisits(LocalDate.now()); 
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-from-to-canceled-visits")
	public ResponseEntity<List<Visit>> readAllFromToCanceledVisits(
			@RequestBody VisitModelHelper visitModelHelper) {
		
		List<Visit> visits=visitRepository.getFromToCanceledVisits(visitModelHelper.getFromDate(),visitModelHelper.getToDate()); 
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	
	@PostMapping("/visit/update-visit-approved-by-ga-to-true")
	public ResponseEntity<String> updateVisitApprovedByGaToTrue(
			@RequestBody VisitModelHelper visitModelHelper){
		System.out.println("request arrived");
		Optional<Visit> visitOpt= visitRepository.findById(visitModelHelper.getId());
		Visit visit=visitOpt.get();
		visit.setVisitApprovedByGa(true);
		visitRepository.save(visit);
		return  new ResponseEntity<>("""
				{"updated":true}
				""", HttpStatus.OK);
	}
	
	@PostMapping("/visit/update-exit-approved-by-ga-to-true")
	public ResponseEntity<String> updateExitApprovedByGaToTrue(
			@RequestBody VisitModelHelper visitModelHelper){
		Optional<Visit> visitOpt= visitRepository.findById(visitModelHelper.getId());
		Visit visit=visitOpt.get();
		visit.setExitApprovedByGa(true);
		visitRepository.save(visit);
		return  new ResponseEntity<>("""
				{"updated":true}
				""", HttpStatus.OK);
	}
	
	
	@PostMapping("/visit/update-canceled-by-ga-to-true")
	public ResponseEntity<String> updateExitApprovedByGaToTrue3(
			@RequestBody VisitModelHelper visitModelHelper){
		Optional<Visit> visitOpt= visitRepository.findById(visitModelHelper.getId());
		Visit visit=visitOpt.get();
		visit.setCanceledByGa(true);
		visitRepository.save(visit);
		return  new ResponseEntity<>("""
				{"updated":true}
				""", HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-by-any-mobile-number")
	public ResponseEntity<List<Visit>> readAllVisitsByAnyMobileNumber(
			@RequestBody VisitModelHelper visitModelHelper
			) {
//		System.out.println("getFromDate "+visitModelHelper.getFromDate()+""
//				+ " getToDate "+visitModelHelper.getToDate()+""
//						+ " getAnyMobileNumber "+visitModelHelper.getAnyMobileNumber());
		
		List<Visit> visits=visitRepository.
				getAllVisitsByAnyMobileNumber(
						visitModelHelper.getFromDate(),
						visitModelHelper.getToDate(),
						visitModelHelper.getAnyMobileNumber());
		
		visits.forEach(visit->System.out.println(visit.toString()));	
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-canceled-by-any-mobile-number")
	public ResponseEntity<List<Visit>> readAllCanceledVisitsByAnyMobileNumber(
			@RequestBody VisitModelHelper visitModelHelper
			) {
//		System.out.println("getFromDate "+visitModelHelper.getFromDate()+""
//				+ " getToDate "+visitModelHelper.getToDate()+""
//						+ " getAnyMobileNumber "+visitModelHelper.getAnyMobileNumber());
		
		List<Visit> visits=visitRepository.
				getAllCanceledVisitsByAnyMobileNumber(
						visitModelHelper.getFromDate(),
						visitModelHelper.getToDate(),
						visitModelHelper.getAnyMobileNumber());
		
		visits.forEach(visit->System.out.println(visit.toString()));	
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-by-username")
	public ResponseEntity<List<Visit>> readAllNormalVisitsByUsername(
			@RequestBody VisitModelHelper visitModelHelper
			) {
//		System.out.println("getFromDate "+visitModelHelper.getFromDate()+""
//				+ " getToDate "+visitModelHelper.getToDate()+""
//						+ " getMeetingPersonUsername "+visitModelHelper.getMeetingPersonUsername());
		
		List<Visit> visits=visitRepository.
				getAllNormalVisitsByUsername(
						visitModelHelper.getFromDate(),
						visitModelHelper.getToDate(),
						visitModelHelper.getMeetingPersonUsername());
		
		visits.forEach(visit->System.out.println(visit.toString()));	
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	@PostMapping("/visit/read-all-canceled-by-username")
	public ResponseEntity<List<Visit>> readAllCanceledVisitsByUsername(
			@RequestBody VisitModelHelper visitModelHelper
			) {
//		System.out.println("getFromDate "+visitModelHelper.getFromDate()+""
//				+ " getToDate "+visitModelHelper.getToDate()+""
//						+ " getMeetingPersonUsername "+visitModelHelper.getMeetingPersonUsername());
		
		List<Visit> visits=visitRepository.
				getAllCanceledVisitsByUsername(
						visitModelHelper.getFromDate(),
						visitModelHelper.getToDate(),
						visitModelHelper.getMeetingPersonUsername());
		
		visits.forEach(visit->System.out.println(visit.toString()));	
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	
	@PostMapping("/visit/read-all-by-authenticated-user")
	public ResponseEntity<List<Visit>> readAllNormalVisitsByAuthenticatedUser(
			@RequestBody VisitModelHelper visitModelHelper
			) {
		System.out.println("getFromDate "+visitModelHelper.getFromDate()+""
				+ " getToDate "+visitModelHelper.getToDate());
		String username="user1";
		List<Visit> visits=visitRepository.
				getAllNormalVisitsByUsername(
						visitModelHelper.getFromDate(),
						visitModelHelper.getToDate(),
						username);
		
		visits.forEach(visit->System.out.println(visit.toString()));	
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
	@PostMapping("/visit/read-all-canceled-by-authenticated-user")
	public ResponseEntity<List<Visit>> readAllCanceledVisitsByAuthenticatedUser(
			@RequestBody VisitModelHelper visitModelHelper
			) {
		System.out.println("getFromDate "+visitModelHelper.getFromDate()+""
				+ " getToDate "+visitModelHelper.getToDate());
		String username="user1";
		List<Visit> visits=visitRepository.
				getAllCanceledVisitsByUsername(
						visitModelHelper.getFromDate(),
						visitModelHelper.getToDate(),
						username);
		
		visits.forEach(visit->System.out.println(visit.toString()));	
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}

	@PostMapping("/visit/update-visit-approved-by-uoh-sh-to-true")
	public ResponseEntity<String> updateVisitApprovedByUohShToTrue(
			@RequestBody VisitModelHelper visitModelHelper){
		System.out.println("request arrived");
		Optional<Visit> visitOpt= visitRepository.findById(visitModelHelper.getId());
		Visit visit=visitOpt.get();
		visit.setVisitApprovedByUohSh(true);
		visitRepository.save(visit);
		return  new ResponseEntity<>("""
				{"updated":true}
				""", HttpStatus.OK);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
//		System.out.println(LocalDate.now());
	}
	
	
	
	
	
	
}
