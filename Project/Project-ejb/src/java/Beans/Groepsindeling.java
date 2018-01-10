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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "groepsindeling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groepsindeling.findAll", query = "SELECT g FROM Groepsindeling g")
    , @NamedQuery(name = "Groepsindeling.deleteById", query = "DELETE FROM Groepsindeling g WHERE g.id = :id")
    , @NamedQuery(name = "Groepsindeling.updateByGroepnummer", query = "UPDATE Groepsindeling g SET g.gebruikerId = :gebruikerId WHERE g.groepnummer = :groepnummer")
    , @NamedQuery(name = "Groepsindeling.aantalGroepen", query = "SELECT g.groepnummer FROM Groepsindeling g GROUP BY g.groepnummer")
    , @NamedQuery(name = "Groepsindeling.findLastGroepnummer", query = "SELECT max(g.groepnummer) FROM Groepsindeling g")
    , @NamedQuery(name = "Groepsindeling.findById", query = "SELECT g FROM Groepsindeling g WHERE g.id = :id")
    , @NamedQuery(name = "Groepsindeling.findByGroepnummer", query = "SELECT g FROM Groepsindeling g WHERE g.groepnummer = :groepnummer")
    , @NamedQuery(name = "Groepsindeling.findByGroepBevestigt", query = "SELECT g FROM Groepsindeling g WHERE g.groepBevestigt = :groepBevestigt")})
public class Groepsindeling implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "groepnummer")
    private Integer groepnummer;
    @Size(max = 1)
    @Column(name = "groep_bevestigt")
    private String groepBevestigt;
    @JoinColumn(name = "gebruiker_id", referencedColumnName = "gebruiker_id")
    @ManyToOne
    private Gebruikers gebruikerId;

    public Groepsindeling() {
    }

    public Groepsindeling(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroepnummer() {
        return groepnummer;
    }

    public void setGroepnummer(Integer groepnummer) {
        this.groepnummer = groepnummer;
    }

    public String getGroepBevestigt() {
        return groepBevestigt;
    }

    public void setGroepBevestigt(String groepBevestigt) {
        this.groepBevestigt = groepBevestigt;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groepsindeling)) {
            return false;
        }
        Groepsindeling other = (Groepsindeling) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Groepsindeling[ id=" + id + " ]";
    }
    
}
