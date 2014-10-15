/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import hotel.modele.ChambreDB;
import hotel.modele.ClientDB;
import hotel.modele.PourDB;
import hotel.modele.ReservationDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spymannn
 */
public class CreationReservation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        java.sql.Date dateDebut,dateFin;
        int litbebe;
        String[] numChambres = request.getParameterValues("numChambre");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String boutonAction = request.getParameter("action");
        
        String suite="/index.jsp?page=confirmationReservation.jsp";
        ArrayList<String> erreurs=new ArrayList<String>();
        HttpSession session=request.getSession(true);
        
        
        try{
            if(boutonAction.equals("Reserver")){
                if(numChambres.length>0){
                    //dateDebut = new java.sql.Date(format.parse((String)session.getAttribute("dateDebut")).getTime());
                    //dateFin = new java.sql.Date(format.parse((String)session.getAttribute("dateFin")).getTime());
                    //litbebe = Integer.parseInt((String)session.getAttribute("litBebee"));

                    /*ClientDB clr =(ClientDB)session.getAttribute("client");
                    ReservationDB reservation = new ReservationDB(dateDebut,clr.getId_client(),dateFin,litbebe,dateDebut);
                    reservation.create();
                    int nReserv = reservation.getnReservation();
                    for(String n : numChambres){
                        PourDB p = new PourDB(n,nReserv);
                        p.create();
                    }*/
                    session.setAttribute("numChambres",numChambres);
                    suite="/index.jsp?page=confirmationReservation.jsp";
                }
                else{
                    erreurs.add("Vous n'avez rien sélectionner");
                    suite="/index.jsp?page=rechercheChambre.jsp";
                }
            }
            else if(boutonAction.equals("Se connecter")){
                suite="/index.jsp?page=accueil.jsp";
            }
            else{
                suite="/index.jsp?page=rechercheChambre.jsp";
            }
            //session.setAttribute("listeChambres", listeFinal);
        }
        catch(Exception e){
            suite="/index.jsp?page=rechercheChambre.jsp";
            erreurs.add(e.getMessage());
        }
        session.setAttribute("erreur",erreurs);
        RequestDispatcher rd;
	rd = getServletContext().getRequestDispatcher(suite);
	rd.forward(request, response);
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
