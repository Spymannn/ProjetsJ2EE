package servlets;

import hotel.modele.ClientDB;
import hotel.modele.ReservationDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spymannn
 */
@WebServlet(name = "connexion", urlPatterns = {"/connexion"})
public class Connexion extends HttpServlet {
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
       String login = request.getParameter("login");
        String suite="/index.jsp?page=accueil.jsp";
        ArrayList<String> erreurs=new ArrayList<String>();
        HttpSession session=request.getSession(true);
        if (login.equals("login")) {//regarde si on a appuyé sur le bouton "login"
            String email = request.getParameter("email");
            String mdp = request.getParameter("mdp");
            try {
                ClientDB clr = null;
                clr = clr.rechercheEmail(email);
                //ArrayList<ReservationDB> tabReserv = ReservationDB.rechReservation(clr.getId_client());
                if (clr.getMdp().equals(mdp)) {
                    session.setAttribute("client", clr);//on va mettre en session, le
                    //client qu'on a trouvé avec son email
                    //avec clr.read();
                    //session.setAttribute("tabReserv",tabReserv);
                }
                else{
                    erreurs.add("Mauvais mot de passe entré");
                }

            } catch (Exception e) {
                erreurs.add(e.getMessage());//Si on ne trouve pas le client,ici on va noté client introuvable
                suite = "/index.jsp?page=accueil.jsp";;  //et on revient alors ensuite sur la page d'accueil
            }
        }
        session.setAttribute("erreur", erreurs);
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
