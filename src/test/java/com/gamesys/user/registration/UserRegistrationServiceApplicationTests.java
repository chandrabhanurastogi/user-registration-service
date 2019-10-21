package com.gamesys.user.registration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegistrationServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	UserRegistrationServiceApplication userRegistrationServiceApplication;

	@Test
	public void testIssuerNumberLoadAtStartup(){
		String [] blockedIssuerNumbers = new String[] {"--iin.blocked='123456;234567;456789'"};
		ApplicationArguments args = new DefaultApplicationArguments(blockedIssuerNumbers);
		userRegistrationServiceApplication.run(args);
	}

}
