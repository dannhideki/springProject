package br.com.season.springproject.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.season.springproject.config.TestJPAContextConfiguration;
import br.com.season.springproject.dao.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestJPAContextConfiguration.class})
@Transactional
@Rollback
public class UserTest {
	
	@Autowired
	private UserDAO dao;

	@Test
	public void shouldSaveNewUser(){
		User user = new User();
		user.setLastName("Teste");
		user.setFirstName("teste");
		user.setCpf("1234");
		user.setUsername("teste");
		dao.save(user);
		Assert.assertNotNull(user.getId());
	}
	
	
	
	
	
	
	
	
	
}
