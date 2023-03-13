package code.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import code.user.entity.User;
import code.user.repository.UserRepository;

@Service
@Order(1)
public class UserInitialData implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public void run(String... args) throws Exception {
	//		System.out.println("order 1");
	
	//		boolean is_patientPresent=patientRepository.findByFirstName("user1").isPresent();
	//		if(is_patientPresent==false) {
	//			User user = new User();
	//			user.setUsername("user1");
	//			user.setPassword("pass123");
	//			userRepository.save(user);
	//		}
		
		boolean is_user1_present=userRepository.findByUsername("user1").isPresent();
//		System.out.println("is_user1_present "+is_user1_present);
		if(is_user1_present==false) {
			User user = new User();
			user.setUsername("user1");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		
		boolean is_user2_present=userRepository.findByUsername("user2").isPresent();
		if(is_user2_present==false) {
			User user = new User();
			user.setUsername("user2");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		boolean is_user3_present=userRepository.findByUsername("user3").isPresent();
		if(is_user3_present==false) {
			User user = new User();
			user.setUsername("user3");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		boolean is_user4_present=userRepository.findByUsername("user4").isPresent();
		if(is_user4_present==false) {
			User user = new User();
			user.setUsername("user4");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		boolean is_user5_present=userRepository.findByUsername("user5").isPresent();
		if(is_user5_present==false) {
			User user = new User();
			user.setUsername("user5");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		boolean is_user6_present=userRepository.findByUsername("user6").isPresent();
		if(is_user6_present==false) {
			User user = new User();
			user.setUsername("user6");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		boolean is_user7_present=userRepository.findByUsername("user7").isPresent();
		if(is_user7_present==false) {
			User user = new User();
			user.setUsername("user7");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		boolean is_user8_present=userRepository.findByUsername("user8").isPresent();
		if(is_user8_present==false) {
			User user = new User();
			user.setUsername("user8");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		boolean is_user9_present=userRepository.findByUsername("user9").isPresent();
		if(is_user9_present==false) {
			User user = new User();
			user.setUsername("user9");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		boolean is_user10_present=userRepository.findByUsername("user10").isPresent();
		if(is_user10_present==false) {
			User user = new User();
			user.setUsername("user10");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		boolean is_gate_admin1_present=userRepository.findByUsername("gate_admin1").isPresent();
		if(is_gate_admin1_present==false) {
			User user = new User();
			user.setUsername("gate_admin1");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		
		boolean is_gate_admin2_present=userRepository.findByUsername("gate_admin2").isPresent();
		if(is_gate_admin2_present==false) {
			User user = new User();
			user.setUsername("gate_admin2");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		
		boolean is_app_admin1_present=userRepository.findByUsername("app_admin1").isPresent();
		if(is_app_admin1_present==false) {
			User user = new User();
			user.setUsername("app_admin1");
			user.setPassword("pass123");
			userRepository.save(user);
		}
		boolean is_app_admin2_present=userRepository.findByUsername("app_admin2").isPresent();
		if(is_app_admin2_present==false) {
			User user = new User();
			user.setUsername("app_admin2");
			user.setPassword("pass123");
			userRepository.save(user);
		}

//		System.out.println(userRepository.findByUsername("reg123").get().getPassword());
	}
}
