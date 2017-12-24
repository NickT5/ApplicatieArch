/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import Beans.Gebruikers;
import Beans.Groepen;
import Beans.MainBeanRemote;
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
        
            if(request.isUserInRole("student")){
                System.out.println("Student");
                
                //Haal gegevens van de database
                List<Groepen> lijstIds_van_studenten = mb.getIds_van_studenten();
                
                if(lijstIds_van_studenten.isEmpty()) System.out.println("Lijst studenten is LEEG");    
                else
                {
                    System.out.println("Lijst studenten is NIET LEEG");    
                    System.out.println("Lijst: "+ lijstIds_van_studenten);
            
                    for(int i=0;i<lijstIds_van_studenten.size();i++)
                    {
                        //Get all namen by id's van tabel gebruikers.                      
                        Groepen gr = lijstIds_van_studenten.get(i);
                        Gebruikers g = gr.getGebruikers();
                        String id = g.getGebruikerId();
                        System.out.println("ID: " + id);
                        String voornaam = mb.getStudentVoornaamById(id);
                        System.out.println("VOORNAAM: " + voornaam);
                    }
                    
                }
                
                gotoPage("menu.jsp",request,response);
            }
            if(request.isUserInRole("docent")){
                System.out.println("Docent");
                gotoPage("groepsIndeling.jsp",request,response);
            }
        }
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
