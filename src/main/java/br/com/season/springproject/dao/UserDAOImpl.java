package br.com.season.springproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.season.springproject.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public User findById(Integer id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public void save(User user) {
		em.persist(user);
	}

	@Override
	public void update(User user) {
		em.merge(user);
	}

	@Override
	public void delete(User user) {
		em.remove(findById(user.getId()));
	}

}
