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
@Table(name = "nietvoorkeur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nietvoorkeur.findAll", query = "SELECT n FROM Nietvoorkeur n")
    , @NamedQuery(name = "Nietvoorkeur.findById", query = "SELECT n FROM Nietvoorkeur n WHERE n.id = :id")
    , @NamedQuery(name = "Nietvoorkeur.bestaat", query = "SELECT n FROM Nietvoorkeur n WHERE n.gebruikerId = :gebruikerId AND n.nvk = :nvk")
    , @NamedQuery(name = "Nietvoorkeur.findByGebruikerId", query = "SELECT n FROM Nietvoorkeur n WHERE n.gebruikerId = :gebruikerId")
    , @NamedQuery(name = "Nietvoorkeur.findByNvk", query = "SELECT n FROM Nietvoorkeur n WHERE n.nvk = :nvk")})
public class Nietvoorkeur implements Serializable {

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
    @Column(name = "nvk")
    private String nvk;

    public Nietvoorkeur() {
    }

    public Nietvoorkeur(Integer id) {
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

    public String getNvk() {
        return nvk;
    }

    public void setNvk(String nvk) {
        this.nvk = nvk;
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
        if (!(object instanceof Nietvoorkeur)) {
            return false;
        }
        Nietvoorkeur other = (Nietvoorkeur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Nietvoorkeur[ id=" + id + " ]";
    }
    
}
