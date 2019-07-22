package edu.gt.tmb.dao.test;

import org.junit.Test;

import edu.gt.tmb.dao.UserDao;
import edu.gt.tmb.entity.User;

public class UserTest {
	private UserDao userdao = new UserDao();
	@Test
	public void createUserTest() {
		User userNeethu = new User();
		userNeethu.setId("0011");
		userNeethu.setFirstName("Nikita");
		userNeethu.setMinit(null);
		userNeethu.setLastName("Bipin");
		userNeethu.setPassengerEmail("neethu.bipin@yahoo.com");
		userNeethu.setPassword("test");
		
		userdao.addUser(userNeethu);
		userdao.deleteUser(userNeethu.getId());
		
	}
}
