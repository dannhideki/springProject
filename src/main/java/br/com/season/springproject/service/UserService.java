package br.com.season.springproject.service;

import java.util.List;

import br.com.season.springproject.entity.User;
import br.com.season.springproject.entity.UserProfile;
import br.com.season.springproject.generic.JPAGeneric;

public interface UserService extends JPAGeneric<User>{
	
	User findByCpf(String cpf);

	List<User> findBy(String lastName, String firstName, String cpf);

	User findUsername(String username);

	UserProfile findRoleById(Integer id);

	List<UserProfile> findAllRoles();
}
