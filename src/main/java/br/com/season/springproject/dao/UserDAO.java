package br.com.season.springproject.dao;

import java.util.List;

import br.com.season.springproject.entity.User;

public interface UserDAO {
	
	User findById(Integer id);
	
	List<User> findAll();
	
	void save(User user);
	
	void update(User user);
	
	void delete(User user);
}
