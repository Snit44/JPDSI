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
@Table(name = "moderation")
@NamedQueries({
    @NamedQuery(name = "Moderation.findAll", query = "SELECT m FROM Moderation m"),
    @NamedQuery(name = "Moderation.findByModerationId", query = "SELECT m FROM Moderation m WHERE m.moderationId = :moderationId"),
    @NamedQuery(name = "Moderation.findByAction", query = "SELECT m FROM Moderation m WHERE m.action = :action"),
    @NamedQuery(name = "Moderation.findByCreatedAt", query = "SELECT m FROM Moderation m WHERE m.createdAt = :createdAt")})
public class Moderation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "moderation_id")
    private Integer moderationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "action")
    private String action;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "moderator_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users moderatorId;
    @JoinColumn(name = "target_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users targetId;

    public Moderation() {
    }

    public Moderation(Integer moderationId) {
        this.moderationId = moderationId;
    }

    public Moderation(Integer moderationId, String action) {
        this.moderationId = moderationId;
        this.action = action;
    }

    public Integer getModerationId() {
        return moderationId;
    }

    public void setModerationId(Integer moderationId) {
        this.moderationId = moderationId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Users getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(Users moderatorId) {
        this.moderatorId = moderatorId;
    }

    public Users getTargetId() {
        return targetId;
    }

    public void setTargetId(Users targetId) {
        this.targetId = targetId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moderationId != null ? moderationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moderation)) {
            return false;
        }
        Moderation other = (Moderation) object;
        if ((this.moderationId == null && other.moderationId != null) || (this.moderationId != null && !this.moderationId.equals(other.moderationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Moderation[ moderationId=" + moderationId + " ]";
    }
    
}
