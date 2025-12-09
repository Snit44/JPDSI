package com.jsfcourse.users;

import com.jsfcourse.dao.UsersDAO;
import com.jsfcourse.entities.Users;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RegisterBB {

    private String username;
    private String email;
    private String password;

    @EJB
    private UsersDAO usersDAO;

    public String register() {
        Users u = new Users();
        u.setUsername(username);
        u.setEmail(email);
        u.setHashedPassword(password);
        u.setRoleId(null);
        usersDAO.create(u);

        return "login?faces-redirect=true";
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
