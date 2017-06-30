package br.com.season.springproject.dao;

import br.com.season.springproject.entity.User;
import br.com.season.springproject.generic.JPAGeneric;

public interface UserDAO extends JPAGeneric<User>{
	
	User findByCpf(String cpf);
}
