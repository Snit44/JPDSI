package com.jsfcourse.users;

import com.jsfcourse.dao.UsersDAO;
import com.jsfcourse.entities.Users;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Date;

@Named
@RequestScoped
public class UsersFormBB implements Serializable {

    private Users user = new Users();

    @EJB
    private UsersDAO usersDAO;

    public Users getUser() {
        return user;
    }

    public String save() {
        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        usersDAO.create(user);
        return "usersTable?faces-redirect=true";
    }
}
