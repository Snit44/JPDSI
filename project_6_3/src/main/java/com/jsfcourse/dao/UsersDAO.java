package com.jsfcourse.dao;

import com.jsfcourse.entities.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Map;

@Stateless
public class UsersDAO {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public void create(Users user) {
        em.persist(user);
    }

    public Users merge(Users user) {
        return em.merge(user);
    }

    public void remove(Users user) {
        em.remove(em.merge(user));
    }

    public Users find(Object id) {
        return em.find(Users.class, id);
    }

    public List<Users> getFullList() {
        Query query = em.createQuery("select u from Users u order by u.username");
        return query.getResultList();
    }

    public List<Users> getList(Map<String, Object> searchParams) {
        String select = "select u ";
        String from = "from Users u ";
        String where = "";
        String orderBy = "order by u.username";

        String username = (String) searchParams.get("username");
        String email = (String) searchParams.get("email");

        if (username != null && !username.isBlank()) {
            where += (where.isEmpty() ? "where " : " and ");
            where += "lower(u.username) like :username ";
        }

        if (email != null && !email.isBlank()) {
            where += (where.isEmpty() ? "where " : " and ");
            where += "lower(u.email) like :email ";
        }

        String jpql = select + from + where + " " + orderBy;

        Query query = em.createQuery(jpql);

        if (username != null && !username.isBlank()) {
            query.setParameter("username", username.toLowerCase() + "%");
        }

        if (email != null && !email.isBlank()) {
            query.setParameter("email", email.toLowerCase() + "%");
        }

        return query.getResultList();
    }
}
