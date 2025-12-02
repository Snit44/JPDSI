package com.project6.item;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.project6.dao.ItemDAO;
import com.project6.entities.Item;

@Named
@ViewScoped
public class ItemEditGETBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_ITEM_LIST = "itemList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private Item item = new Item();
    private Item loaded = null;

    @Inject
    FacesContext context;

    @EJB
    ItemDAO itemDAO;

    public Item getItem() {
        return item;
    }

    public void onLoad() throws IOException {
        if (!context.isPostback()) {
            if (!context.isValidationFailed() && item.getId() != null) {
                loaded = itemDAO.find(item.getId());
            }
            if (loaded != null) {
                item = loaded;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));

            }
        }

    }

    public String saveData() {
        if (loaded == null) {
            return PAGE_STAY_AT_THE_SAME;
        }

        try {
            if (item.getId() == null) {
            
                itemDAO.create(item);
            } else {
            
                itemDAO.merge(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_ITEM_LIST;
    }
}
