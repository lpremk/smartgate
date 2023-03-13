package code.smartgate.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import code.smartgate.entity.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") = DATE_FORMAT(:todayDate ,"%M %d %Y");
			""",nativeQuery = true)
	List<Visit> getTodayVisits(@Param("todayDate") LocalDate todayDate); 
	
	
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") = DATE_FORMAT(:todayDate ,"%M %d %Y") AND visit_approved_by_ga=false AND exit_approved_by_ga=false AND canceled_by_ga=false;
			""",nativeQuery = true)
	List<Visit> getTodayPendingVisits(@Param("todayDate") LocalDate todayDate); 
	
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") = DATE_FORMAT(:todayDate ,"%M %d %Y") AND visit_approved_by_ga=true AND exit_approved_by_ga=false AND canceled_by_ga=false;
			""",nativeQuery = true)
	List<Visit> getTodayPendingExits(@Param("todayDate") LocalDate todayDate); 
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") = DATE_FORMAT(:todayDate ,"%M %d %Y") AND visit_approved_by_ga=true AND exit_approved_by_ga=true AND canceled_by_ga=false;
			""",nativeQuery = true)
	List<Visit> getTodaySuccessfulExits(@Param("todayDate") LocalDate todayDate); 
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") = DATE_FORMAT(:todayDate ,"%M %d %Y") AND canceled_by_ga=true;
			""",nativeQuery = true)
	List<Visit> getTodayCanceledVisits(@Param("todayDate") LocalDate todayDate);
	
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") BETWEEN DATE_FORMAT(:fromDate ,"%M %d %Y") AND DATE_FORMAT(:toDate ,"%M %d %Y") AND visit_approved_by_ga=false AND exit_approved_by_ga=false AND canceled_by_ga=false;
			""",nativeQuery = true)
	List<Visit> getFromToPendingVisits(@Param("fromDate") LocalDate fromDate,@Param("toDate") LocalDate toDate); 
	
	
	@Query(value = """ 
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") BETWEEN DATE_FORMAT(:fromDate ,"%M %d %Y") AND DATE_FORMAT(:toDate ,"%M %d %Y") AND visit_approved_by_ga=true AND exit_approved_by_ga=false AND canceled_by_ga=false;
			""",nativeQuery = true)
	List<Visit> getFromToPendingExits(@Param("fromDate") LocalDate fromDate,@Param("toDate") LocalDate toDate); 
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") BETWEEN DATE_FORMAT(:fromDate ,"%M %d %Y") AND DATE_FORMAT(:toDate ,"%M %d %Y") AND visit_approved_by_ga=true AND exit_approved_by_ga=true AND canceled_by_ga=false;
			""",nativeQuery = true)
	List<Visit> getFromToSuccessfulExits(@Param("fromDate") LocalDate fromDate,@Param("toDate") LocalDate toDate);  
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") BETWEEN DATE_FORMAT(:fromDate ,"%M %d %Y") AND DATE_FORMAT(:toDate ,"%M %d %Y") AND canceled_by_ga=true;
			""",nativeQuery = true)
	List<Visit> getFromToCanceledVisits(@Param("fromDate") LocalDate fromDate,@Param("toDate") LocalDate toDate);  
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") BETWEEN DATE_FORMAT(?1 ,"%M %d %Y") AND DATE_FORMAT(?2 ,"%M %d %Y") AND  (meeting_person_mobile_number=?3 OR visitor_mobile_number=?3) AND canceled_by_ga=false;
			""",nativeQuery = true)
	List<Visit> getAllVisitsByAnyMobileNumber(LocalDate fromDate,LocalDate toDate,Long anyMobileNumber);
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") BETWEEN DATE_FORMAT(?1 ,"%M %d %Y") AND DATE_FORMAT(?2 ,"%M %d %Y") AND  (meeting_person_mobile_number=?3 OR visitor_mobile_number=?3) AND canceled_by_ga=true;
			""",nativeQuery = true)
	List<Visit> getAllCanceledVisitsByAnyMobileNumber(LocalDate fromDate,LocalDate toDate,Long anyMobileNumber);
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") BETWEEN DATE_FORMAT(?1 ,"%M %d %Y") AND DATE_FORMAT(?2 ,"%M %d %Y") AND  (meeting_person_username=?3) AND canceled_by_ga=false;
			""",nativeQuery = true)
	List<Visit> getAllNormalVisitsByUsername(LocalDate fromDate,LocalDate toDate,String meetingPersonUsername);
	
	@Query(value = """
			SELECT * FROM visit where DATE_FORMAT(created_time ,"%M %d %Y") BETWEEN DATE_FORMAT(?1 ,"%M %d %Y") AND DATE_FORMAT(?2 ,"%M %d %Y") AND  (meeting_person_username=?3) AND canceled_by_ga=true;
			""",nativeQuery = true)
	List<Visit> getAllCanceledVisitsByUsername(LocalDate fromDate,LocalDate toDate,String meetingPersonUsername);
}
