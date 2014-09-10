package com.kaykisiz.web.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.kaykisiz.web.entity.posts.Post;

public class BaseDao<T, ID extends Serializable> {

	private SessionFactory sessionFactory;

	private Class<T> persistenceClass;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		this.persistenceClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Class<T> getPersistenceClass() {
		return persistenceClass;
	}

	@Transactional
	public T insert(T entity) throws Exception {

		getSessionFactory().getCurrentSession().save(entity);
		getSessionFactory().getCurrentSession().flush();
		getSessionFactory().getCurrentSession().refresh(entity);

		return entity;
	}

	//
	// tüm kayırlardan son 20 gönderiyi çeker.
	//
	@Transactional
	@SuppressWarnings("unchecked")
	public List<T> fetchRecordsAll() throws Exception {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(getPersistenceClass());
		return criteria.addOrder(Order.desc("createdDate")).setMaxResults(20)
				.list();
	}

	//
	// Gellen isime göre son 20 gönderiyi listeler.
	//
	@Transactional
	@SuppressWarnings("unchecked")
	public List<T> fetchPostRecordsUserLike(String user) throws Exception {

		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(getPersistenceClass());
		Criterion c1 = Restrictions.like("content", "@" + user,
				MatchMode.ANYWHERE);

		criteria.add(c1).addOrder(Order.desc("createdDate")).setMaxResults(20);

		return criteria.list();
	}

	//
	// gelen iisme ait tüm kayıtları siler.
	//
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(String user) throws Exception {
		// id'leri alabilmek için önce kullanıcıya aiit gönderiler belirlenir.
		List<Post> posts = (List<Post>) fetchPostRecordsUserLike(user);
		T entity;
		// her gönderi id adresine göre tek tek silinir.
		for (Post post : posts) {
			System.out.println(post.getId());
			entity = (T) getSessionFactory().getCurrentSession().get(
					getPersistenceClass(), post.getId());
			getSessionFactory().getCurrentSession().delete(entity);
		}

	}
}
