/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import Beans.Gebruikers;
import Beans.Groepen;
import Beans.Groepsindeling;
import Beans.MainBeanRemote;
import Beans.Nietvoorkeur;
import Beans.Voorkeur;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Clock;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jasper.tagplugins.jstl.ForEach;

//<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
import javax.servlet.http.HttpSession;

/**
 *
 * @author kevin
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB private MainBeanRemote mb;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            System.out.println("CONTROLLER");
        
            HttpSession session = request.getSession();
            String id = request.getUserPrincipal().getName();   //Naam van de principal (gebruiker) is de ID.
            String voornaam = mb.getVoornaamById(id);           //Voornaam van ingelogd persoon
            String achternaam = mb.getAchternaamById(id);       //Achternaam van ingelogd persoon
            String naamIngelogd = voornaam + " " + achternaam;
            //session.setAttribute("voornaam", voornaam);
            session.setAttribute("naamIngelogd", naamIngelogd);
            
            System.out.println("DEBUG: In Servlet"); 
            
            if(request.isUserInRole("student")){
                System.out.println("Dit is een student");
                
                //Haal alle studenten uit de database
                List<String> lijstNamen_van_studenten = HaalAlleStudentenUitDB(naamIngelogd);
                session.setAttribute("namen", lijstNamen_van_studenten); 
                
                //Decide to which page you have to go
                if(request.getParameter("from")!= null){
                    System.out.println("DEBUG: Hiddenfield 'from' != null");
                    switch(request.getParameter("from")){
                        case "Save":
                            deleteNvkAndVkFromDB(id,request);
                            AddNvkAndVkToDB(id,request);                                            //Get from request, Save to DB
                            setupNvkAndVkNames(id, lijstNamen_van_studenten, request);              //Get from DB, order, filter, add to request
                            
                            gotoPage("menu.jsp",request,response);
                            break;
                        case "Bevestig":
                            deleteNvkAndVkFromDB(id,request);
                            AddNvkAndVkToDB(id,request);                                            //Get from request, Save to DB
                            setupNvkAndVkNames(id, lijstNamen_van_studenten, request);              //Get from DB, order, filter, add to request
                            mb.updateStudentBevestigt(id);                      //Set studentBevestigt op true ('1') voor de ingelogde gebruiker
                            gotoPage("finaal.jsp", request, response);
                            break;
                        /*case "voorlopigeKeuze":
                            gotoPage("finaal.jsp", request, response);
                            break;*/
                        case "afmelden":
                            //session.invalidate();     //Oproepen in de logout.jsp pagina
                            gotoPage("logout.jsp", request, response);
                            break;
                        default:
                            break;
                    }
                }    
                else{
                    System.out.println("DEBUG: Hiddenfield 'from' == null");
                    //Je komt van login.jsp
                    
                    int groepNummer = 0;
                    List<Groepsindeling> g = mb.getGroepen();
                    for(int i=0;i<g.size();i++)
                    {
                        if(g.get(i).getGebruikerId().getGebruikerId().equals(id))
                        {
                            groepNummer = g.get(i).getGroepnummer();
                        }
                    }
                    
                    //System.out.println("DEBUG: gebruiker: " + id + " heeft groepnummer = " + groepNummer);    //DEBUG
                  
                    //if(mb.isGroepBevestigt(groepNummer))
                    if(groepNummer != 0)
                    {
                        //System.out.println("DEBUG: student zit in een groep");                                //DEBUG
                        
                        List<Groepsindeling> groepsIndeling = mb.getStudentenInGroep(groepNummer);
                        List<String> studentenInGroep = new ArrayList<String>();
                        for(int i=0; i<groepsIndeling.size(); i++)
                        {
                            String naam = groepsIndeling.get(i).getGebruikerId().getVoornaam()+" "+groepsIndeling.get(i).getGebruikerId().getAchternaam();
                            studentenInGroep.add(naam);
                        }
                              
                        request.setAttribute("groepnummer", groepNummer);
                        request.setAttribute("groepstudenten2", studentenInGroep);
                        gotoPage("overzichtVoorStudent.jsp", request, response);
                    }
                    else{
                        
                        System.out.println("DEBUG: groep is NIET bevestigt");
                        System.out.println("DEBUG: groep is NIET bevestigt");
                        setupNvkAndVkNames(id, lijstNamen_van_studenten, request);                      //Get from DB, order, filter, add to request
                        if(mb.isStudentBevestigt(id).equals("1")){
                            gotoPage("finaal.jsp",request,response);
                        }
                        else{
                            gotoPage("menu.jsp",request,response);
                        }                   
                    }
                    
                }
                
            }
            if(request.isUserInRole("docent")){
                System.out.println("DEBUG: Dit is een Docent");  

                if(request.getParameter("from")!= null){
                    System.out.println("DEBUG: 'from' != null");
                    switch(request.getParameter("from")){
                        case"groepsIndeling1":
                            System.out.println("DEBUG: 'from' groepsIndeling1 naar edit");
                            String groep = request.getParameter("Groepen");
                            int groepNummer = Integer.parseInt(groep.replaceAll("[^0-9]", ""));//vervang alles wat geen getal is door ""
                            System.out.println("Edit groep "+groepNummer);
                            
                            if(mb.isGroepBevestigt(groepNummer))
                            {
                                List<Groepsindeling> groepsIndeling = mb.getStudentenInGroep(groepNummer);
                                List<String> studentenInGroep = new ArrayList<String>();
                                for(int i=0; i<groepsIndeling.size(); i++)
                                {
                                    String naam = groepsIndeling.get(i).getGebruikerId().getVoornaam()+" "+groepsIndeling.get(i).getGebruikerId().getAchternaam();
                                    studentenInGroep.add(naam);
                                }
                                
                                session.setAttribute("groepnummer", groepNummer);
                                request.setAttribute("groepstudenten2", studentenInGroep);
                                gotoPage("overzicht.jsp", request, response);
                            }
                            else
                            {
                                if(mb.groepLeeg(groepNummer) == 0)
                                {
                                    System.out.println("groep is niet leeg");
                                    List<Groepsindeling> studentenInGroep = mb.getStudentenInGroep(groepNummer);
                                    List<String> studentenVoorkeur = getVoorkeurStudenten(studentenInGroep);
                                    List<String> studentenNietVoorkeur = getNietVoorkeurStudenten(studentenInGroep);
                                    List<Groepen> alleStudenten = mb.getIds_van_studenten();

                                    filterStudenten(studentenInGroep, alleStudenten);

                                    session.setAttribute("studentenVoorkeur", studentenVoorkeur);
                                    session.setAttribute("studentenNietVoorkeur", studentenNietVoorkeur);
                                    session.setAttribute("studentenInGroep", studentenInGroep);
                                    session.setAttribute("groepnummer", groepNummer);
                                    session.setAttribute("alleStudenten", alleStudenten);
                                }
                                else{
                                    System.out.println("groep is leeg");

                                    List<Groepen> alleStudenten = mb.getIds_van_studenten();

                                    session.setAttribute("studentenVoorkeur", null);
                                    session.setAttribute("studentenInGroep", null);
                                    session.setAttribute("groepnummer", groepNummer);
                                    session.setAttribute("alleStudenten", alleStudenten);
                                }
                                gotoPage("editGroep.jsp", request, response);
                            }
                            break;
                            
                        case"groepsIndeling2":
                            System.out.println("DEBUG: 'from' groepsIndeling2 naar nieuw");
                            mb.voegGroepToe();
                            session.setAttribute("aantalGroepen", mb.getAantalGroepen());
                            gotoPage("groepsIndeling.jsp",request,response);
                            break;
                           
                        case"Save":
                            System.out.println("DEBUG: 'from' edit1 naar groepsIndeling");
                            String[] groepstudenten = request.getParameterValues("studentenInGroep");
                            groepNummer = (int)session.getAttribute("groepnummer");
                            System.out.println("DEBUG: groepnummer is"+groepNummer);                            
                            
                            List<Groepsindeling> studentenInGroepInDb = (List<Groepsindeling>)session.getAttribute("studentenInGroep");
                            System.out.println("DEBUG: studentenInGroepInDb is "+studentenInGroepInDb); 
                            
                            if(studentenInGroepInDb==null)
                            {
                                //Oorspronkelijk een lege lijst in database. Wss een nieuwe groep aangemaakt
                                insertStudentenInGroep(groepstudenten,groepNummer);
                            }
                            else
                            {
                                checkForDeleteStudentInGroep(studentenInGroepInDb,groepstudenten);
                                checkForInsertStudentInGroep(studentenInGroepInDb,groepstudenten);
                            }
                            
                            gotoPage("groepsIndeling.jsp",request,response);
                            break;
                            
                        case "Bevestig":
                            System.out.println("DEBUG: 'from' edit2 naar overzicht");
                            String[] groepstudenten2 = request.getParameterValues("studentenInGroep");
                            groepNummer = (int)session.getAttribute("groepnummer");
                            System.out.println("DEBUG: groepnummer is "+groepNummer);                            
                            
                            studentenInGroepInDb = (List<Groepsindeling>)session.getAttribute("studentenInGroep");
                            System.out.println("DEBUG: studentenInGroepInDb is "+studentenInGroepInDb); 
                            
                            if(studentenInGroepInDb==null)
                            {
                                //Oorspronkelijk een lege lijst in database. Wss een nieuwe groep aangemaakt
                                insertStudentenInGroep(groepstudenten2,groepNummer);
                            }
                            else
                            {
                                checkForDeleteStudentInGroep(studentenInGroepInDb,groepstudenten2);
                                checkForInsertStudentInGroep(studentenInGroepInDb,groepstudenten2);
                            }
                            
                            mb.bevestigGroep(groepNummer);
                            request.setAttribute("groepstudenten2", groepstudenten2);
                            
                            gotoPage("overzicht.jsp", request, response);
                            break;
                        case"overzicht":
                            gotoPage("groepsIndeling.jsp", request, response);
                            break;
                             
                        case "afmelden":
                            //session.invalidate();     //Oproepen in de logout.jsp pagina
                            gotoPage("logout.jsp", request, response);
                            break;
                        default:
                            break;
                    }
                }    
                else{
                    System.out.println("DEBUG: 'from' == null");
                    session.setAttribute("aantalGroepen", mb.getAantalGroepen());
                    gotoPage("groepsIndeling.jsp",request,response);
                }
            }
        }
    }
    
    public void insertStudentenInGroep(String[] studenten, int groepnummer)
    {
        //Eerst updaten om een NULL waarde te overschrijven en de rest inserten
        mb.updateGroepsindeling(mb.getIdByFullName(studenten[0]), groepnummer);
        
        for(int i=1; i< studenten.length; i++)
        {
            mb.insertStudentInGroep(mb.getIdByFullName(studenten[i]), groepnummer);
        }
    }
    
    public void checkForInsertStudentInGroep(List<Groepsindeling> studentenInDatabase, String[] studenten)
    {
        boolean gevonden = false;
        int groepnummer = studentenInDatabase.get(0).getGroepnummer();
        System.out.println("DEBUG: Insert moet geburen in groep "+groepnummer);
        for(int i=0; i<studenten.length; i++)
        {
            for(int j=0; j<studentenInDatabase.size(); j++)
            {
                if(mb.getIdByFullName(studenten[i]).equals(studentenInDatabase.get(j).getGebruikerId().getGebruikerId()))  
                {
                    System.out.println(studentenInDatabase.get(j).getGebruikerId().getVoornaam()+" gevonden in lijst");
                    gevonden = true;
                    break;
                }
                else
                {
                    gevonden = false;
                }
            }
            if(!gevonden)
            {
                //Studenten Inserten in database
                System.out.println(studenten[i]+" zit niet in de lijst. Dus Insert met id gelijk aan "+mb.getIdByFullName(studenten[i]));
                mb.insertStudentInGroep(mb.getIdByFullName(studenten[i]), groepnummer);
            }
        }
    }
    
    public void checkForDeleteStudentInGroep(List<Groepsindeling> studentenInDatabase, String[] studenten)
    {
        boolean gevonden = false;     
        for(int i=0; i<studentenInDatabase.size(); i++)
        {
            //System.out.println("student "+studentenInDatabase.get(i).getGebruikerId().getVoornaam()+" met id nummer "+studentenInDatabase.get(i).getGebruikerId().getGebruikerId());
            for(int j=0;j<studenten.length;j++)
            {
                if(studentenInDatabase.get(i).getGebruikerId().getGebruikerId().equals(mb.getIdByFullName(studenten[j])))
                {
                    System.out.println(studentenInDatabase.get(i).getGebruikerId().getVoornaam()+" gevonden in lijst");
                    gevonden=true;
                    break;
                }
                else
                {
                    gevonden = false;
                }
            }
            if(!gevonden)
            {
                //Student verwijderen uit database
                System.out.println(studentenInDatabase.get(i).getGebruikerId().getVoornaam()+" zit niet in de lijst. Dus delete met id gelijk aan "+studentenInDatabase.get(i).getId());
                mb.deleteStudentUitGroep(studentenInDatabase.get(i).getId());
            }
        }
    }
    
    public void filterStudenten(List<Groepsindeling> studenten, List<Groepen> alleStudenten)
    {
        Iterator<Groepsindeling> i = studenten.iterator();
        Iterator<Groepen> j = alleStudenten.iterator();
        while(i.hasNext())
        {
            Groepsindeling student1 = i.next();
            while(j.hasNext())
            {
                Groepen student2 = j.next();
                System.out.println(student1.getGebruikerId().getGebruikerId()+" vergelijken met "+student2.getId());
                if(student1.getGebruikerId().getGebruikerId().equals(student2.getId()))
                {
                     System.out.println(student1.getGebruikerId().getVoornaam()+" zit al in de lijst");
                     j.remove();
                     j = alleStudenten.iterator();//reset iterator omdat je terug van in het begin van de lijst moet vergelijken
                     break;
                }
            }
        }
    }
    
    public void filterStrings(List<String> studenten, List<String> alleStudenten)
    {
        Iterator<String> i = studenten.iterator();
        Iterator<String> j = alleStudenten.iterator();
        while(i.hasNext())
        {
            String student1 = i.next();
            while(j.hasNext())
            {
                String student2 = j.next();
                if(student1.equals(student2))
                {
                     System.out.println(student1 + " zit al in de lijst");
                     j.remove();
                     break;
                }
            }
        }
    }
    
    public List<String> HaalAlleStudentenUitDB(String naamIngelogd)
    {
        List<Groepen> lijstIds_van_studenten = mb.getIds_van_studenten();       //Get alle id's van de studenten
        List<String> lijstNamen_van_studenten = new ArrayList<String>();
                
        if(lijstIds_van_studenten.isEmpty()) System.out.println("Lijst studenten is LEEG");    
        else
        {
            System.out.println("Lijst studenten is NIET LEEG");    
            System.out.println("Lijst: "+ lijstIds_van_studenten);
            
            for(int i=0;i<lijstIds_van_studenten.size();i++)            //Get alle namen by id's van tabel gebruikers (enkel studenten).
            {                                    
                Groepen gr = lijstIds_van_studenten.get(i);
                Gebruikers g = gr.getGebruikers();
                String id2 = g.getGebruikerId();                        //Gebruiker ID ophalen
                String naam = mb.getVoornaamById(id2) + " " + mb.getAchternaamById(id2);  //Voor-en achternaam ophalen met ID en concatineren
                if(naam.equals(naamIngelogd)) continue;                 //Persoon zelf moet niet in de lijst zitten
                lijstNamen_van_studenten.add(naam);                     //Naam toevoegen aan lijst
            }
            Collections.sort(lijstNamen_van_studenten);                 //Sorteer namen alfabetisch
            System.out.println("NAMEN: " + lijstNamen_van_studenten);   //Debug                  
        }
        return lijstNamen_van_studenten;
    }
    
    public List<String> HaalAlleNvkUitDB(String gid)
    {
        List<String> list_nvk_names = new ArrayList<String>();
        List<Nietvoorkeur> list_nvk = mb.getNietVoorkeurByGebruikerId(gid);
        
        //Get Full name and add to the list
        if(list_nvk != null)
        {
            for(int i=0;i<list_nvk.size();i++){             
                String nvk_id = list_nvk.get(i).getNvk();
                list_nvk_names.add(mb.getVoornaamById(nvk_id) + " " + mb.getAchternaamById(nvk_id));
            }
        }
        return list_nvk_names;
    }
    
    public List<String> HaalAlleVkUitDB(String gid)
    {
        List<String> list_vk_names = new ArrayList<String>();
        List<Voorkeur> list_vk = mb.getVoorkeurByGebruikerId(gid);
        
        if(list_vk != null)
        {
            for(int i=0;i<list_vk.size();i++){
                String vk_id = list_vk.get(i).getVk();
                list_vk_names.add(mb.getVoornaamById(vk_id) + " " + mb.getAchternaamById(vk_id));
            }
        }
        return list_vk_names;
    }
    
    public List<String> getVoorkeurStudenten(List<Groepsindeling> studentenInGroep)
    {
        List<Voorkeur> voorkeur  = null;
        List<String> s = new ArrayList<>();
        for(int i=0; i<studentenInGroep.size(); i++)
        {
            Gebruikers g = studentenInGroep.get(i).getGebruikerId();
            voorkeur = mb.getVoorkeurByGebruikerId(g.getGebruikerId());
            for(int j=0;j<voorkeur.size();j++)
            {
                //System.out.println(g.getVoornaam()+" wilt samenzitten met "+mb.getVoornaamById(voorkeur.get(j).getVk()));
                s.add(g.getVoornaam()+" "+g.getAchternaam()+" wilt samenzitten met "+mb.getVoornaamById(voorkeur.get(j).getVk())+" "+mb.getAchternaamById(voorkeur.get(j).getVk()));
                }                
        }
        return s;
    }
    
    public void AddNvkAndVkToDB(String id, HttpServletRequest request)
    {
        //Niet Voorkeur en Voorkeur ophalen uit de request (menu.jsp)
        String[] arrayNVK = request.getParameterValues("nietvoorkeur");
        String[] arrayVK = request.getParameterValues("voorkeur");
                            
        //Niet voorkeur en Voorkeur toevoegen aan de database
        if(arrayNVK != null)
        {
            for(int i=0;i<arrayNVK.length;i++)
            {
                String id_nvk = mb.getIdByFullName(arrayNVK[i]);                //Get ID van student
                System.out.print("NVK naam: "+arrayNVK[i] + " | ID: "+id_nvk);  //DEBUG
                mb.voegNvkToe(id, id_nvk);                                      //Voegtoe aan DB met check of het al bestaat.
            }
        }
        if(arrayVK != null)
        {
            for(int i=0;i<arrayVK.length;i++){
                String id_vk = mb.getIdByFullName(arrayVK[i]);                 //Get ID van student
                System.out.print("VK naam: "+arrayVK[i] + " | ID: "+id_vk);    //DEBUG
                mb.voegVkToe(id, id_vk);                                       //Voegtoe aan DB met check of het al bestaat.
            }
        }
    }
    
    public void deleteNvkAndVkFromDB(String id, HttpServletRequest request)
    {
        boolean gevonden = false;       //Voor NVK (Niet Voorkeur)
        boolean gevonden2 = false;      //Voor VK  (Voorkeur)
        
        String[] arrayNVK = request.getParameterValues("nietvoorkeur");
        String[] arrayVK = request.getParameterValues("voorkeur");
        
        //Get alle Voorkeur en nietvoorkeur id's uit de DB voor de ingelogde gebruiker id 
        List<String> list_nvk_names = HaalAlleNvkUitDB(id);
        List<String> list_vk_names = HaalAlleVkUitDB(id);
                    
        if(arrayNVK == null)
        {       
            mb.deleteNvkByGid(id);                  //Verwijder alles uit de NVK tabel
        }
        else{
            //Vergelijk lijst v/d request met lijst uit de DB  voor NVK         
            for(int i=0; i<list_nvk_names.size(); i++)
            {
                for(int j=0;j<arrayNVK.length;j++)
                {
                    System.out.println(list_nvk_names.get(i) + " vergelijken met " +arrayNVK[j]);
                    if(arrayNVK[j].equals(list_nvk_names.get(i)))
                    {
                        gevonden=true;
                        break;
                    }
                    else
                    {
                        gevonden=false;
                    }
                }
                if(!gevonden)
                {
                    //Student verwijderen uit database
                    System.out.println(list_nvk_names.get(i)+" moet gedelete worden.");
                    mb.deleteNvkByGidAndNvk(id, mb.getIdByFullName(list_nvk_names.get(i)));
                }
            }
        }
              
        if(arrayVK == null)
        {       
            mb.deleteVkByGid(id);                  //Verwijder alles uit de VK tabel
        }
        else{
            //Vergelijk lijst v/d request met lijst uit de DB  voor NVK         
            for(int i=0; i<list_vk_names.size(); i++)
            {
                for(int j=0;j<arrayVK.length;j++)
                {
                    System.out.println(list_vk_names.get(i) + " vergelijken met " +arrayVK[j]);
                    if(arrayVK[j].equals(list_vk_names.get(i)))
                    {
                        gevonden2=true;
                        break;
                    }
                    else
                    {
                        gevonden2=false;
                    }
                }
                if(!gevonden2)
                {
                    //Student verwijderen uit database
                    System.out.println(list_vk_names.get(i)+" moet gedelete worden.");
                    mb.deleteVkByGidAndVk(id, mb.getIdByFullName(list_vk_names.get(i)));
                }
            }
        }
              
        
    }
    
    public List<String> getNietVoorkeurStudenten(List<Groepsindeling> studentenInGroep)
    {
        List<Nietvoorkeur> nietVoorkeur  = null;
        List<String> s = new ArrayList<>();
        for(int i=0; i<studentenInGroep.size(); i++)
        {
            Gebruikers g = studentenInGroep.get(i).getGebruikerId();
            nietVoorkeur = mb.getNietVoorkeurByGebruikerId(g.getGebruikerId());
            for(int j=0;j<nietVoorkeur.size();j++)
            {
                System.out.println(g.getVoornaam()+" wilt niet samenzitten met "+mb.getVoornaamById(nietVoorkeur.get(j).getNvk()));
                s.add(g.getVoornaam()+" "+g.getAchternaam()+" wilt niet samenzitten met "+mb.getVoornaamById(nietVoorkeur.get(j).getNvk())+" "+mb.getAchternaamById(nietVoorkeur.get(j).getNvk()));
                }                
        }
        return s;
    }
    
    public void setupNvkAndVkNames(String id, List<String> lijstNamen_van_studenten, HttpServletRequest request)
    {
        //Get alle Voorkeur en nietvoorkeur id's uit de DB voor de ingelogde gebruiker id
        List<String> list_nvk_names = HaalAlleNvkUitDB(id);
        List<String> list_vk_names = HaalAlleVkUitDB(id);

        Collections.sort(list_nvk_names);      //Sorteer namen alfabetisch
        Collections.sort(list_vk_names);       //Sorteer namen alfabetisch

        filterStrings(list_nvk_names, lijstNamen_van_studenten);    
        filterStrings(list_vk_names, lijstNamen_van_studenten);

        request.setAttribute("list_nvk_names", list_nvk_names);
        request.setAttribute("list_vk_names", list_vk_names);
        request.setAttribute("lijstNamen_van_studenten", lijstNamen_van_studenten); 
    }
        
    protected void gotoPage(String jsp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        RequestDispatcher view = request.getRequestDispatcher(jsp);
        view.forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
