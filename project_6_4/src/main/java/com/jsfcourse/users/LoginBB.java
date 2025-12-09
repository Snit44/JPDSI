package com.jsfcourse.users;

import com.jsfcourse.dao.UsersDAO;
import com.jsfcourse.entities.Users;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LoginBB implements Serializable {

    private String email;
    private String password;
    private Users loggedUser;

    @EJB
    private UsersDAO usersDAO;

    public String login() {
        List<Users> users = usersDAO.getFullList();

        for (Users u : users) {
            if (u.getEmail().equals(email) && u.getHashedPassword().equals(password)) {
                loggedUser = u;
                return "usersTable?faces-redirect=true";
            }
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Invalid email or password", null));

        return null;
    }

    public void redirectIfNotLogged() {
        if (loggedUser == null) {
            try {
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect("login.xhtml");
            } catch (IOException ignored) {}
        }
    }

    public void logout() {
        loggedUser = null;
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("login.xhtml");
        } catch (IOException ignored) {}
    }

    public boolean isLogged() {
        return loggedUser != null;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Users getLoggedUser() { return loggedUser; }
}
