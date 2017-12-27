/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nick
 */
@Entity
@Table(name = "voorkeur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voorkeur.findAll", query = "SELECT v FROM Voorkeur v")
    , @NamedQuery(name = "Voorkeur.findById", query = "SELECT v FROM Voorkeur v WHERE v.id = :id")
    , @NamedQuery(name = "Voorkeur.bestaat", query = "SELECT v FROM Voorkeur v WHERE v.gebruikerId = :gebruikerId AND v.vk = :vk")
    , @NamedQuery(name = "Voorkeur.findByGebruikerId", query = "SELECT v FROM Voorkeur v WHERE v.gebruikerId = :gebruikerId")
    , @NamedQuery(name = "Voorkeur.findByVk", query = "SELECT v FROM Voorkeur v WHERE v.vk = :vk")})
public class Voorkeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "gebruiker_id")
    private String gebruikerId;
    @Size(max = 20)
    @Column(name = "vk")
    private String vk;

    public Voorkeur() {
    }

    public Voorkeur(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGebruikerId() {
        return gebruikerId;
    }

    public void setGebruikerId(String gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voorkeur)) {
            return false;
        }
        Voorkeur other = (Voorkeur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Voorkeur[ id=" + id + " ]";
    }
    
}
