/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hotel.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.sql.Date;

/**
 *
 * @author Spymannn
 */
public class ReservationDB extends Reservation implements CRUD{
    protected static Connection dbConnect=null;
    /**
     * Constructeur par défaut
     */
    public ReservationDB(){}
    /**
     * Constructeur paramétré
     * @param nReservation
     * @param dateJourDe
     * @param idClient
     * @param dateJourA
     * @param prixTotal
     * @param nbreLitBebeReserv
     * @param acompte
     * @param dateReserv
     * @param dateLim
     * @param dateVers 
     */
    public ReservationDB(int nReservation,Date dateJourDe,int idClient,Date dateJourA,
            float prixTotal,int nbreLitBebeReserv,float acompte,Date dateReserv,
            Date dateLim,Date dateVers){
        super(nReservation,dateJourDe,idClient,dateJourA,prixTotal,
                nbreLitBebeReserv,acompte,dateReserv,dateLim,dateVers);
    }
    /**
     * Constructeur paramétré lors de la création d'une reservation
     * @param dateJourDe
     * @param idClient
     * @param dateJourA
     * @param nbreLitBebeReserv
     * @param dateLim 
     */
    public ReservationDB(Date dateJourDe,int idClient,Date dateJourA,
            int nbreLitBebeReserv,Date dateLim){
        super(0,dateJourDe,idClient,dateJourA,0,nbreLitBebeReserv,0,null,dateLim,null);
    }
    /**
     * Constructeur avec comme paramètre l'id du client 
     * @param idClient 
     */
    /*public ReservationDB(int idClient){
        super(0,null,idClient,null,0,0,0,null,null,null);
    }*/
    /**
     * Constructeur avec numReservation
     */
    public ReservationDB(int nReserv){
        super(nReserv,null,0,null,0,0,0,null,null,null);
    }
    /**
     * Méthode statique qui permet de partager
     * la connexion entre tous les objets de ReservationDB
     * @param nouvdbConnect 
     */
    public static void setConnection(Connection nouvdbConnect){
        dbConnect=nouvdbConnect;
    }
    
    /**
     * Méthode de création d'une reservation
     * @throws Exception 
     */
    public void create() throws Exception{
        CallableStatement cstmt=null;
        try{
            String rep="call createReservation(?,?,?,?,?,?)";
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,java.sql.Types.INTEGER);
            cstmt.setDate(2,dateJourDe);
            cstmt.setInt(3,idClient);
            cstmt.setDate(4,dateJourA);
            cstmt.setInt(5,nbreLitBebeReserv);
            cstmt.setDate(6,dateLim);
            cstmt.executeUpdate(); 
            this.nReservation=cstmt.getInt(1);
            read();
        }
        catch(Exception e){
            throw new Exception("Erreur dans la création de reservation "+e.getMessage());
            //e.printStackTrace();
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }
    }
    /**
     * Méthode de lecture par le numéro de réservation
     * @throws Exception 
     */
    public void read() throws Exception{
        String rep="{?=call readreservation1(?)}";
        CallableStatement cstmt=null;
        
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,nReservation);
            cstmt.executeQuery();
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            if(rs.next()){
                this.dateJourDe=rs.getDate("date_jour_de");
                this.idClient=rs.getInt("id_client");
                this.dateJourA=rs.getDate("date_jour_a");
                this.prixTotal=rs.getFloat("prix_total");
                this.nbreLitBebeReserv=rs.getInt("nbre_lits_bebe_reserves");
                this.acompte=rs.getFloat("acompte");
                this.dateReserv=rs.getDate("date_reserv");
                this.dateLim=rs.getDate("date_limite");
                this.dateVers=rs.getDate("date_versement");
            }
            else
                throw new Exception("Numéro de réservation inconnu");
        }
        catch(Exception e){
            throw new Exception("Erreur de lecture"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }
    }
    /**
     * Méthode d'affichage complete d'une reservation avec le client et pour
     * @return
     * @throws Exception 
     */
    public static ArrayList<String> afficheInfos(int nReservation) throws Exception{
        ArrayList<String> infosReservation = new ArrayList<String>();
        String rep="{?=call readreservation2(?)}";
        CallableStatement cstmt=null;
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,nReservation);
            cstmt.executeQuery();
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            boolean ok=false;
            while(rs.next()){
                ok=true;
                int idClient=rs.getInt("id_client");
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                int nReserv=rs.getInt("n_reservation");
                String numChambre=rs.getString("numero");
                Date dateDe=rs.getDate("date_jour_de");
                Date dateA=rs.getDate("date_jour_a");
                Date dateRe=rs.getDate("date_reserv");
                Date dateLi=rs.getDate("date_limite");
                Date dateVer=rs.getDate("date_versement");
                Float ac=rs.getFloat("acompte");
                Float prixT=rs.getFloat("prix_total");
                int nbreLitBebe=rs.getInt("nbre_lits_bebe_reserves");
                String ligne="Id : "+idClient+"\nNom : "+nom+"\nPrenom : "+prenom
                        +"\nNum reserv : "+nReserv+"\nNum chambre : "+numChambre
                        +"\nDate début : "+dateDe+"\nDate fin : "+dateA
                        +"\nDate limite : "+dateLi+"\nDate versement"+dateVer
                        +"\nAcompte : "+ac+"\nPrix total : "+prixT
                        +"\nNombre de lits bébé"+nbreLitBebe;
                infosReservation.add(ligne);
            }
            if(!ok)
                throw new Exception("Numéro de réservation inéxistant");
            else
                return infosReservation;
        }
        catch(Exception e){
            throw new Exception("Erreur de lecture"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }
    }
    /**
     * Recherche des réservations pour un client
     * @param idClient
     * @return
     * @throws Exception 
     */
    public static ArrayList<ReservationDB> rechReservation(int idClient) throws Exception{
        ArrayList<ReservationDB> tabReserv = new ArrayList<ReservationDB>();
        String rep="{?=call readreservation3(?)}";
        CallableStatement cstmt=null;
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,idClient);
            cstmt.executeQuery();
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            boolean ok=false;
            while(rs.next()){
                ok=true;
                int nReserv = rs.getInt("n_reservation");
                Date dateDe=rs.getDate("date_jour_de");
                Date dateA=rs.getDate("date_jour_a");
                Date dateRe=rs.getDate("date_reserv");
                Date dateLi=rs.getDate("date_limite");
                Date dateVer=rs.getDate("date_versement");
                Float ac=rs.getFloat("acompte");
                Float prixT=rs.getFloat("prix_total");
                int nbreLitBebe=rs.getInt("nbre_lits_bebe_reserves");
                
                tabReserv.add(new ReservationDB(nReserv,dateDe,idClient,
                dateA,prixT,nbreLitBebe,ac,dateRe,dateLi,dateVer));
               
            }
            if(!ok)
                throw new Exception("Pas de réervation pour ce client");
            else
                return tabReserv;
        }
        catch(Exception e){
            throw new Exception("Erreur de lecture"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }
    }
    /**
     * Fonction de mise à jour du nombre de lit bébé dans la réservation
     * @throws Exception 
     */
    public void update() throws Exception{
        CallableStatement cstmt=null;
        try{
            String rep="call updateReservation1(?,?)";
            cstmt=dbConnect.prepareCall(rep);
            PreparedStatement pstm=dbConnect.prepareStatement(rep);
            cstmt.setInt(1,nReservation);
            cstmt.setInt(2,nbreLitBebeReserv);
            cstmt.executeUpdate(); 
        }
        catch(Exception e){
            throw new Exception("Erreur de mise à jour"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }
    }
    
    /**
     * Fonction de mise à jour de la date de versement
     * @throws Exception
     */
    public void updateDateVersement() throws Exception{
        CallableStatement cstmt=null;
        try{
            String rep="call updateReservation2(?,?)";
            cstmt=dbConnect.prepareCall(rep);
            PreparedStatement pstm=dbConnect.prepareStatement(rep);
            cstmt.setInt(1,nReservation);
            cstmt.setDate(2,(java.sql.Date)dateVers);
            cstmt.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Erreur dans la mise à jour de la date de versement"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }
    }
    /**
     * Fonction de suppression d'une réservation
     * @throws Exception 
     */
    public void delete() throws Exception{
        CallableStatement cstmt=null;
        try{
            try{
             ArrayList<PourDB> liste=PourDB.rechLignes(nReservation);
             for(PourDB cmp : liste){
                 cmp.delete();
             } 
            }
            catch(Exception e){}
            String rep="call deletereservation(?)";
            cstmt=dbConnect.prepareCall(rep);
            cstmt.setInt(1,nReservation);
            cstmt.executeUpdate();
            
        }
        catch(Exception e){
            throw new Exception("Erreur durant la suppression"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }
    }
    
}
