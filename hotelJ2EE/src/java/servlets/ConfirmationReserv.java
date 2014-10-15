/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import hotel.modele.ClientDB;
import hotel.modele.PourDB;
import hotel.modele.ReservationDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class ConfirmationReserv extends HttpServlet {

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
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String confirm = request.getParameter("confirmer");
        
        
        
        String suite="/index.jsp?page=reservationReussie.jsp";
        ArrayList<String> erreurs=new ArrayList<String>();
        HttpSession session=request.getSession(true);
        String[] numChambres =(String[])session.getAttribute("numChambres");
        
        try {
            if(confirm.equals("Je confirme")){
                
                //dateDebut = new java.sql.Date(format.parse((String)session.getAttribute("dateDebut")).getTime());
                //dateFin = new java.sql.Date(format.parse((String)session.getAttribute("dateFin")).getTime());
                dateDebut = (java.sql.Date)session.getAttribute("dateDebut");
                dateFin = (java.sql.Date)session.getAttribute("dateFin");
                litbebe = Integer.parseInt((String)session.getAttribute("litBebee"));
                ClientDB clr = (ClientDB) session.getAttribute("client");
                clr.read();
                ReservationDB reservation = new ReservationDB(dateDebut,clr.getId_client(),dateFin,litbebe,null);
                reservation.create();
                reservation.read();
                int nReserv = reservation.getnReservation();
                for(int i = 0;i<numChambres.length;i++){
                    PourDB p = new PourDB(numChambres[i], nReserv);
                    p.create();
                }
                suite = "/index.jsp?page=reservationReussie.jsp";
            }
            if(confirm.equals("J'annule")){
                erreurs.add("Annulation de réservation !");
                suite = "/index.jsp?page=rechercheChambre.jsp";
            }

        }
        catch(Exception e){
            erreurs.add(e.toString());
            suite="/index.jsp?page=rechercheChambre.jsp";
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
