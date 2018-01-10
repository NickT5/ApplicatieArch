/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nick
 */
@Entity
@Table(name = "gebruikers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gebruikers.findAll", query = "SELECT g FROM Gebruikers g")
    , @NamedQuery(name = "Gebruikers.findByGebruikerId", query = "SELECT g FROM Gebruikers g WHERE g.gebruikerId = :gebruikerId")
    , @NamedQuery(name = "Gebruikers.findByPaswoord", query = "SELECT g FROM Gebruikers g WHERE g.paswoord = :paswoord")
    , @NamedQuery(name = "Gebruikers.findByVoornaam", query = "SELECT g FROM Gebruikers g WHERE g.voornaam = :voornaam")
    , @NamedQuery(name = "Gebruikers.findByAchternaam", query = "SELECT g FROM Gebruikers g WHERE g.achternaam = :achternaam")
    , @NamedQuery(name = "Gebruikers.findByFullName", query = "SELECT g FROM Gebruikers g WHERE g.voornaam = :voornaam AND g.achternaam = :achternaam")
    , @NamedQuery(name = "Gebruikers.updateStudentBevestigt", query = "UPDATE Gebruikers g SET g.studentBevestigt = '1' WHERE g.gebruikerId = :gid")   
    , @NamedQuery(name = "Gebruikers.findByStudentBevestigt", query = "SELECT g FROM Gebruikers g WHERE g.studentBevestigt = :studentBevestigt")})
public class Gebruikers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "gebruiker_id")
    private String gebruikerId;
    @Size(max = 20)
    @Column(name = "paswoord")
    private String paswoord;
    @Size(max = 20)
    @Column(name = "voornaam")
    private String voornaam;
    @Size(max = 20)
    @Column(name = "achternaam")
    private String achternaam;
    @Size(max = 1)
    @Column(name = "student_bevestigt")
    private String studentBevestigt;
    @OneToMany(mappedBy = "gebruikerId")
    private Collection<Groepsindeling> groepsindelingCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "gebruikers")
    private Groepen groepen;

    public Gebruikers() {
    }

    public Gebruikers(String gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public String getGebruikerId() {
        return gebruikerId;
    }

    public void setGebruikerId(String gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getStudentBevestigt() {
        return studentBevestigt;
    }

    public void setStudentBevestigt(String studentBevestigt) {
        this.studentBevestigt = studentBevestigt;
    }

    @XmlTransient
    public Collection<Groepsindeling> getGroepsindelingCollection() {
        return groepsindelingCollection;
    }

    public void setGroepsindelingCollection(Collection<Groepsindeling> groepsindelingCollection) {
        this.groepsindelingCollection = groepsindelingCollection;
    }

    public Groepen getGroepen() {
        return groepen;
    }

    public void setGroepen(Groepen groepen) {
        this.groepen = groepen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gebruikerId != null ? gebruikerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gebruikers)) {
            return false;
        }
        Gebruikers other = (Gebruikers) object;
        if ((this.gebruikerId == null && other.gebruikerId != null) || (this.gebruikerId != null && !this.gebruikerId.equals(other.gebruikerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Gebruikers[ gebruikerId=" + gebruikerId + " ]";
    }
    
}
