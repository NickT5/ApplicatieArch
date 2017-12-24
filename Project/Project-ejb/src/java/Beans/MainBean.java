/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.*;
import java.util.List;
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
        
    @Override
    public List getGroepen()
    {
        return em.createNamedQuery("Groepsindeling.findAll").getResultList();
    }
    
    @Override
    public List<Integer> getAantalGroepen()
    {
        return em.createNamedQuery("Groepsindeling.aantalGroepen").getResultList();
    }
    
    /**
     *
     * @param id
     * @return Voornaam van gebruiker
     */
    @Override
    public String getVoornaamById(String id)
    {
        Gebruikers g = (Gebruikers) em.createNamedQuery("Gebruikers.findByGebruikerId").setParameter("gebruikerId", id).getSingleResult(); 
        String voornaam = g.getVoornaam();
        return voornaam;
    }
    
    @Override
    public String getAchternaamById(String id)
    {
        Gebruikers g = (Gebruikers) em.createNamedQuery("Gebruikers.findByGebruikerId").setParameter("gebruikerId", id).getSingleResult(); 
        String achternaam = g.getAchternaam();
        return achternaam;  
    }

    @Override
    public void test()
    {
        
    }
    
    @Override
    public void hoi()
    {
        
    }
}
