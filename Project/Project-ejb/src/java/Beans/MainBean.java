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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    /*@Override
    public void hoi()
    {
        
    }*/
    
    public List getStudentenInGroep(int nr)
    {
        return em.createNamedQuery("Groepsindeling.findByGroepnummer").setParameter("groepnummer", nr).getResultList();
    }

    @Override
    public List getVoorkeurByGebruikerId(String gid)
    {
        return em.createNamedQuery("Voorkeur.findByGebruikerId").setParameter("gebruikerId", gid ).getResultList(); 
    }

    @Override
    public List getNietVoorkeurByGebruikerId(String gid)
    {
        return em.createNamedQuery("Nietvoorkeur.findByGebruikerId").setParameter("gebruikerId", gid).getResultList(); 
    }
    
    @Override
    public String getIdByFullName(String name)
    {  
        String[] name_split = name.split(" ",2);
        //System.out.print("DEBUG voornaam: "+name_split[0]);//DEBUG
        //System.out.print("DEBUG achternaam: "+name_split[1]);//DEBUG
        Gebruikers g = (Gebruikers) em.createNamedQuery("Gebruikers.findByFullName").setParameter("voornaam", name_split[0]).setParameter("achternaam", name_split[1]).getSingleResult();
        return g.getGebruikerId();
    }
    
    @Override
    public void voegNvkToe(String gid, String nvk)
    {
        //Check eerst of de record al bestaat in de DB. Zoniet, voeg het dan toe.
        boolean bestaat = true;
        Query q = em.createNamedQuery("Nietvoorkeur.bestaat");
        q.setParameter("gebruikerId",gid).setParameter("nvk", nvk);
        try{
            q.getSingleResult();
           System.out.print("De record " + gid + " - " + nvk +" bestaat al in de tabel Nietvoorkeur");          //DEBUG
        }catch(NoResultException e){
            bestaat = false;
            System.out.print("De record " + gid + " - " + nvk +" bestaat nog niet in de tabel Nietvoorkeur");   //DEBUG
        }
        if(!bestaat)
        {   //Als het nog niet bestaat, moet je het opslaan in de DB
            Nietvoorkeur nietvoorkeur = new Nietvoorkeur();
            nietvoorkeur.setGebruikerId(gid);
            nietvoorkeur.setNvk(nvk);
            em.persist(nietvoorkeur);
        }
    }
    
    @Override
    public void voegVkToe(String gid, String vk)
    {
        //Check eerst of de record al bestaat in de DB. Zoniet, voeg het dan toe.
        boolean bestaat = true;
        Query q = em.createNamedQuery("Voorkeur.bestaat");
        q.setParameter("gebruikerId",gid).setParameter("vk", vk);
        try{
           q.getSingleResult();
           System.out.print("De record " + gid + " - " + vk +" bestaat al in de tabel Voorkeur");          //DEBUG
        }catch(NoResultException e){
            bestaat = false;
            System.out.print("De record " + gid + " - " + vk +" bestaat nog niet in de tabel Voorkeur");   //DEBUG
        }
        if(!bestaat)
        {   //Als het nog niet bestaat, moet je het opslaan in de DB
            Voorkeur voorkeur = new Voorkeur();
            voorkeur.setGebruikerId(gid);
            voorkeur.setVk(vk);
            em.persist(voorkeur);
        }
    }
    
}
