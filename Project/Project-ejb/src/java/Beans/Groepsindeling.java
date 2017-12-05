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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "groepsindeling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groepsindeling.findAll", query = "SELECT g FROM Groepsindeling g")
    , @NamedQuery(name = "Groepsindeling.findByIndelingId", query = "SELECT g FROM Groepsindeling g WHERE g.indelingId = :indelingId")})
public class Groepsindeling implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "indeling_id")
    private Integer indelingId;
    @JoinColumn(name = "gebruiker_id", referencedColumnName = "gebruiker_id")
    @ManyToOne
    private Gebruikers gebruikerId;

    public Groepsindeling() {
    }

    public Groepsindeling(Integer indelingId) {
        this.indelingId = indelingId;
    }

    public Integer getIndelingId() {
        return indelingId;
    }

    public void setIndelingId(Integer indelingId) {
        this.indelingId = indelingId;
    }

    public Gebruikers getGebruikerId() {
        return gebruikerId;
    }

    public void setGebruikerId(Gebruikers gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indelingId != null ? indelingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groepsindeling)) {
            return false;
        }
        Groepsindeling other = (Groepsindeling) object;
        if ((this.indelingId == null && other.indelingId != null) || (this.indelingId != null && !this.indelingId.equals(other.indelingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Groepsindeling[ indelingId=" + indelingId + " ]";
    }
    
}
