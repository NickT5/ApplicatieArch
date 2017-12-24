/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kevin
 */
@Stateless
public class MainBean implements MainBeanRemote {
    @PersistenceContext private EntityManager em;
    
    
    public List getIds_van_studenten()
    {    
        //Get all id's from soort=studenten van tabel groepen
        List ids_studenten = em.createNamedQuery("Groepen.findBySoort").setParameter("soort","student").getResultList();
         
        //System.out.println("MAINBEAN: "+ ids_studenten);
          
        return ids_studenten;
    }
    
    public String getStudentVoornaamById(String id)
    {
        //TODO
        Gebruikers gebruiker = (Gebruikers) em.createNamedQuery("Gebruikers.findByGebruikerId").setParameter("gebruikerId", id).getSingleResult();
        String vn = gebruiker.getVoornaam();
        System.out.println("MAINBEAN VN: "+ vn);
        return vn;
    }
    
}
