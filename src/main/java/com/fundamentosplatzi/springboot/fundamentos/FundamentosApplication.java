package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyOwnBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	private MyOwnBeanWithDependency myOwnBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo, UserRepository userRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDatabase();
		getInformationJpqlFromUser();
	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("User with metod findByUseremail" +
				userRepository.findByUserEmail("j@mail.com")
						.orElseThrow(()-> new RuntimeException("Don't find the User")));
	}

	private void saveUsersInDatabase(){
		User user1 = new User("James", "james@mail.com", LocalDate.of(2001, 01, 01));
		User user2 = new User("Julie", "julie@mail.com", LocalDate.of(2001, 02, 02));
		User user3 = new User("Daniela", "daniela@mail.com", LocalDate.of(2001, 03, 03));
		User user4 = new User("User4", "user4@mail.com", LocalDate.of(2001, 04, 04));
		User user5 = new User("User5", "user5@mail.com", LocalDate.of(2001, 05, 05));
		User user6 = new User("User6", "user6@mail.com", LocalDate.of(2001, 06, 06));
		User user7 = new User("User7", "user7@mail.com", LocalDate.of(2001, 07, 07));
		User user8 = new User("User8", "user8@mail.com", LocalDate.of(2001, 8, 8));
		User user9 = new User("User9", "user9@mail.com", LocalDate.of(2001, 9, 9));
		User user10 = new User("User10", "user10@mail.com", LocalDate.of(2001, 10, 10));
		User user11 = new User("User11", "user11@mail.com", LocalDate.of(2001, 11, 11));
		User user12 = new User("User12", "user12@mail.com", LocalDate.of(2001, 12, 12));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		//this.myOwnBeanWithDependency.displayElements();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + " La edad es: " + userPojo.getAge());
		try{
			//error
			int value = 10/0;
			LOGGER.debug("My value: " + value);
		}catch (Exception e){
			LOGGER.error("This is a mistake by split for cero" + e.getMessage());
		}
	}

}
