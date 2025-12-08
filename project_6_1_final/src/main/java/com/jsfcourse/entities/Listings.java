/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author yursa
 */
@Entity
@Table(name = "listings")
@NamedQueries({
    @NamedQuery(name = "Listings.findAll", query = "SELECT l FROM Listings l"),
    @NamedQuery(name = "Listings.findByListingId", query = "SELECT l FROM Listings l WHERE l.listingId = :listingId"),
    @NamedQuery(name = "Listings.findByTitle", query = "SELECT l FROM Listings l WHERE l.title = :title"),
    @NamedQuery(name = "Listings.findByPrice", query = "SELECT l FROM Listings l WHERE l.price = :price"),
    @NamedQuery(name = "Listings.findByCreatedAt", query = "SELECT l FROM Listings l WHERE l.createdAt = :createdAt"),
    @NamedQuery(name = "Listings.findByUpdatedAt", query = "SELECT l FROM Listings l WHERE l.updatedAt = :updatedAt"),
    @NamedQuery(name = "Listings.findByStatus", query = "SELECT l FROM Listings l WHERE l.status = :status")})
public class Listings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "listing_id")
    private Integer listingId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Size(max = 6)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listingId")
    private Collection<Favorites> favoritesCollection;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne
    private Categories categoryId;
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users ownerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listingId")
    private Collection<ListingImages> listingImagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listingId")
    private Collection<Orders> ordersCollection;

    public Listings() {
    }

    public Listings(Integer listingId) {
        this.listingId = listingId;
    }

    public Listings(Integer listingId, String title, BigDecimal price) {
        this.listingId = listingId;
        this.title = title;
        this.price = price;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<Favorites> getFavoritesCollection() {
        return favoritesCollection;
    }

    public void setFavoritesCollection(Collection<Favorites> favoritesCollection) {
        this.favoritesCollection = favoritesCollection;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }

    public Users getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Users ownerId) {
        this.ownerId = ownerId;
    }

    public Collection<ListingImages> getListingImagesCollection() {
        return listingImagesCollection;
    }

    public void setListingImagesCollection(Collection<ListingImages> listingImagesCollection) {
        this.listingImagesCollection = listingImagesCollection;
    }

    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listingId != null ? listingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listings)) {
            return false;
        }
        Listings other = (Listings) object;
        if ((this.listingId == null && other.listingId != null) || (this.listingId != null && !this.listingId.equals(other.listingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Listings[ listingId=" + listingId + " ]";
    }
    
}
