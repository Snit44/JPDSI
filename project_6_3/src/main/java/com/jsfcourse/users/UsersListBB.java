package com.jsfcourse.users;

import com.jsfcourse.dao.UsersDAO;
import com.jsfcourse.entities.Users;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class UsersListBB implements Serializable {

    private String username;
    private String email;

    @EJB
    private UsersDAO usersDAO;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Users> getList() {
        Map<String, Object> searchParams = new HashMap<>();

        if (username != null && !username.isBlank()) {
            searchParams.put("username", username);
        }

        if (email != null && !email.isBlank()) {
            searchParams.put("email", email);
        }

        return usersDAO.getList(searchParams);
    }

    public String clear() {
        username = null;
        email = null;
        return null;
    }

    public String delete(Integer userId) {
        Users user = usersDAO.find(userId);
        if (user != null) {
            usersDAO.remove(user);
        }
        return null;
    }
}
