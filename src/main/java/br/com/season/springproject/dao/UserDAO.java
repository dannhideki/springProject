package br.com.season.springproject.dao;

import java.util.List;

import br.com.season.springproject.entity.User;
import br.com.season.springproject.generic.JPAGeneric;

public interface UserDAO extends JPAGeneric<User>{
	
	User findByCpf(String cpf);

	List<User> findBy(String lastName, String firstName, String cpf);
}
