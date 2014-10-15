package servlets;

import hotel.modele.ChambreDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class rechercheChambre extends HttpServlet {

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
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String litbebe = request.getParameter("litbebe");
        String prix1S = request.getParameter("prix1");
        String prix2S = request.getParameter("prix2");
        float prix1 = Float.valueOf(prix1S);
        float prix2 = Float.valueOf(prix2S);

        String suite="/index.jsp?page=resultatRechercheChambre.jsp";
        ArrayList<String> erreurs=new ArrayList<String>();
        HttpSession session=request.getSession(true);
        
        if(litbebe.equals("oui")){
            session.setAttribute("litBebee","1");
        }
        else{
            session.setAttribute("litBebee","0");
        }
        try{
            java.sql.Date debut = new java.sql.Date(format.parse(dateDebut).getTime());
            java.sql.Date fin = new java.sql.Date(format.parse(dateFin).getTime());
            //date du jour afin de vérifier si la date n'est pas antérieur
            Calendar date = Calendar.getInstance();
            date.add(date.DATE,-1);//On retire un jour au jour d'aujourd'hui
            java.util.Date dateJour = date.getTime();
            java.sql.Date today = new java.sql.Date(0);
            
            today.setTime(dateJour.getTime());
            
            if(debut.compareTo(today)==1){
                if(debut.compareTo(fin)==-1){
                    session.setAttribute("dateDebut",debut);
                    session.setAttribute("dateFin",fin);
                    ArrayList<ChambreDB> listeDate = ChambreDB.rechChambreDate(debut,fin);
                    ArrayList<ChambreDB> listePrix = ChambreDB.rechChambrePrix(prix1,prix2);
                    ArrayList<ChambreDB> listeFinal = new ArrayList<ChambreDB>();
                    for(ChambreDB d : listeDate){
                        for(ChambreDB p : listePrix){
                            if(p.getNumero().equals(d.getNumero())){
                                if(litbebe.equals("oui") && p.getLit_bebe()==1){
                                    listeFinal.add(p);
                                }
                                if(litbebe.equals("non") && p.getLit_bebe()==0){
                                    listeFinal.add(p);
                                }
                            }
                        }
                    }
                    session.setAttribute("listeChambres", listeFinal);
                }
                else{
                    erreurs.add("Mauvaise entrée pour les dates !");
                    suite="/index.jsp?page=rechercheChambre.jsp";
                }
            }
            else{
                erreurs.add("Date déjà passée !");
                suite="/index.jsp?page=rechercheChambre.jsp";
            }
        }
        catch(Exception e){
            suite="/index.jsp?page=rechercheChambre.jsp";
            erreurs.add("Erreur ou pas de chambre correspondantes aux critères");
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
