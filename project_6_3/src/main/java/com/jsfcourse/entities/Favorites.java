/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author yursa
 */
@Entity
@Table(name = "favorites")
@NamedQueries({
    @NamedQuery(name = "Favorites.findAll", query = "SELECT f FROM Favorites f"),
    @NamedQuery(name = "Favorites.findByFavoriteId", query = "SELECT f FROM Favorites f WHERE f.favoriteId = :favoriteId"),
    @NamedQuery(name = "Favorites.findByCreatedAt", query = "SELECT f FROM Favorites f WHERE f.createdAt = :createdAt")})
public class Favorites implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "favorite_id")
    private Integer favoriteId;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "listing_id", referencedColumnName = "listing_id")
    @ManyToOne(optional = false)
    private Listings listingId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public Favorites() {
    }

    public Favorites(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Listings getListingId() {
        return listingId;
    }

    public void setListingId(Listings listingId) {
        this.listingId = listingId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (favoriteId != null ? favoriteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favorites)) {
            return false;
        }
        Favorites other = (Favorites) object;
        if ((this.favoriteId == null && other.favoriteId != null) || (this.favoriteId != null && !this.favoriteId.equals(other.favoriteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Favorites[ favoriteId=" + favoriteId + " ]";
    }
    
}
