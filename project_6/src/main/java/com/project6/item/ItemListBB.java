package com.project6.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.project6.dao.ItemDAO;
import com.project6.entities.Item;

@Named
@RequestScoped
public class ItemListBB {
    private static final String PAGE_ITEM_EDIT = "itemEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String title;
    private Integer categoryId;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    ItemDAO itemDAO;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Item> getFullList() {
        return itemDAO.getFullList();
    }

    public List<Item> getList() {
        List<Item> list = null;

        Map<String, Object> searchParams = new HashMap<String, Object>();

        if (title != null && title.length() > 0) {
            searchParams.put("title", title);
        }

        if (categoryId != null) {
            searchParams.put("categoryId", categoryId);
        }

        // 2. Get list
        list = itemDAO.getList(searchParams);

        return list;
    }

    public String newItem() {
        Item item = new Item();
        flash.put("item", item);

        return PAGE_ITEM_EDIT;
    }

    public String editItem(Item item) {
        flash.put("item", item);

        return PAGE_ITEM_EDIT;
    }

    public String deleteItem(Item item) {
        itemDAO.remove(item);
        return PAGE_STAY_AT_THE_SAME;
    }
}
