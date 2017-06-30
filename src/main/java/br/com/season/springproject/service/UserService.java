package br.com.season.springproject.service;

import br.com.season.springproject.entity.User;
import br.com.season.springproject.generic.JPAGeneric;

public interface UserService extends JPAGeneric<User>{
	
	User findByCpf(String cpf);

}
