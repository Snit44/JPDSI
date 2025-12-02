package com.project6.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.project6.entities.Item;


@Stateless
public class ItemDAO {
    private final static String UNIT_NAME = "project6-PU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Item item) {
        em.persist(item);
    }

    public Item merge(Item item) {
        return em.merge(item);
    }

    public void remove(Item item) {
        em.remove(em.merge(item));
    }

    public Item find(Object id) {
        return em.find(Item.class, id);
    }

    public List<Item> getFullList() {
        List<Item> list = null;

        Query query = em.createQuery("select i from Item i");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Item> getList(Map<String, Object> searchParams) {
        List<Item> list = null;

        String select = "select i ";
        String from = "from Item i ";
        String where = "";
        String orderby = "order by i.title asc";

        String title = (String) searchParams.get("title");
        if (title != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "i.title like :title ";
        }

        Integer categoryId = (Integer) searchParams.get("categoryId");
        if (categoryId != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "i.categoryId = :categoryId ";
        }

 
        Query query = em.createQuery(select + from + where + orderby);


        if (title != null) {
            query.setParameter("title", title + "%");
        }

        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
