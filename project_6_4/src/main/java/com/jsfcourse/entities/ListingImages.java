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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author yursa
 */
@Entity
@Table(name = "listing_images")
@NamedQueries({
    @NamedQuery(name = "ListingImages.findAll", query = "SELECT l FROM ListingImages l"),
    @NamedQuery(name = "ListingImages.findByImageId", query = "SELECT l FROM ListingImages l WHERE l.imageId = :imageId"),
    @NamedQuery(name = "ListingImages.findByImageUrl", query = "SELECT l FROM ListingImages l WHERE l.imageUrl = :imageUrl"),
    @NamedQuery(name = "ListingImages.findByCreatedAt", query = "SELECT l FROM ListingImages l WHERE l.createdAt = :createdAt"),
    @NamedQuery(name = "ListingImages.findByUpdatedAt", query = "SELECT l FROM ListingImages l WHERE l.updatedAt = :updatedAt")})
public class ListingImages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "image_id")
    private Integer imageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "listing_id", referencedColumnName = "listing_id")
    @ManyToOne(optional = false)
    private Listings listingId;

    public ListingImages() {
    }

    public ListingImages(Integer imageId) {
        this.imageId = imageId;
    }

    public ListingImages(Integer imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Listings getListingId() {
        return listingId;
    }

    public void setListingId(Listings listingId) {
        this.listingId = listingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListingImages)) {
            return false;
        }
        ListingImages other = (ListingImages) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.ListingImages[ imageId=" + imageId + " ]";
    }
    
}
