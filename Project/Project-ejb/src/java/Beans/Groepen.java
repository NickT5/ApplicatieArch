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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "groepen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groepen.findAll", query = "SELECT g FROM Groepen g")
    , @NamedQuery(name = "Groepen.findById", query = "SELECT g FROM Groepen g WHERE g.id = :id")
    , @NamedQuery(name = "Groepen.findBySoort", query = "SELECT g FROM Groepen g WHERE g.soort = :soort")})
public class Groepen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id")
    private String id;
    @Size(max = 20)
    @Column(name = "soort")
    private String soort;
    @JoinColumn(name = "id", referencedColumnName = "gebruiker_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Gebruikers gebruikers;

    public Groepen() {
    }

    public Groepen(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public Gebruikers getGebruikers() {
        return gebruikers;
    }

    public void setGebruikers(Gebruikers gebruikers) {
        this.gebruikers = gebruikers;
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
        if (!(object instanceof Groepen)) {
            return false;
        }
        Groepen other = (Groepen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Groepen[ id=" + id + " ]";
    }
    
}
