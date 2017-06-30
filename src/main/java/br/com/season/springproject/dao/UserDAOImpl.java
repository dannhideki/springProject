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
		return em.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	@Override
	public void save(User user) {
		em.persist(user);
	}

	@Override
	public User update(User user) {
		em.merge(user);
		return user;
	}

	@Override
	public void delete(User user) {
		em.remove(findById(user.getId()));
	}

	@Override
	public User findByCpf(String cpf) {
		return em.createNamedQuery("User.findByCpf", User.class)
				.setParameter("cpf", cpf)
				.getSingleResult();
	}

}
