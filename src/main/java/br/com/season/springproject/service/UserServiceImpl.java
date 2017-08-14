package br.com.season.springproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.season.springproject.dao.UserDAO;
import br.com.season.springproject.entity.User;
import br.com.season.springproject.entity.UserProfile;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User findById(Integer id) {
		return userDAO.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.save(user);
	}

	@Override
	public void delete(User user) {
		userDAO.delete(user);
	}

	@Override
	public User update(User user) {
		userDAO.update(user);
		return user;
	}

	@Override
	public User findByCpf(String cpf) {
		return userDAO.findByCpf(cpf);
	}

	@Override
	public List<User> findBy(String lastName, String firstName, String cpf) {
		return userDAO.findBy(lastName, firstName, cpf);
	}

	@Override
	public User findUsername(String username) {
		return userDAO.findUsername(username);
	}
	
	
	
	
	
	
	
	

}
