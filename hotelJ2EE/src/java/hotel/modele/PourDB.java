/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hotel.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Spymannn
 */
public class PourDB extends Pour implements CRUD{
    protected static Connection dbConnect=null;
    /**
     * Constructeur par défaut
     */
    public PourDB(){}
    /**
     * Constructeur paramétré
     * @param numero
     * @param nReservation 
     */
    public PourDB(String numero,int nReservation){
        super(numero,nReservation);
    }
    /**
     * méthode statique permettant de partager la connexion entre toutes les instances de
     * PourDB
     * @param nouvdbConnect 
     */
    public static void setConnection(Connection nouvdbConnect){
        dbConnect=nouvdbConnect;
    }
    /**
     * Méthode de création d'un Pour
     * @throws Exception 
     */
    public void create() throws Exception{
        CallableStatement cstmt=null;
        
        try{
            String rep="call createpour(?,?)";
            cstmt=dbConnect.prepareCall(rep);
            cstmt.setString(1,numero);
            cstmt.setInt(2,nReservation);
            cstmt.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Erreur durant la création de Pour "+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }
    }
    /**
     * Récupération des données d'une ligne de pour sur base du numéro
     * de chambre
     * @throws Exception 
     */
    public void read() throws Exception{
        String rep="{?=call readpour2(?)}";
        CallableStatement cstmt=null;
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2,numero);
            cstmt.executeQuery();
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            if(rs.next()){
                this.numero=rs.getString(1);
                this.nReservation=rs.getInt(2);
            }
            else
                throw new Exception("Numéro de chambre inconnu");
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
     * Méthode qui me permet de rechercher les lignes à partir du numéro de 
     * reservation dans Pour
     * @param numReserv
     * @return
     * @throws Exception 
     */
    public static ArrayList<PourDB> rechLignes(int numReserv) throws Exception{
        ArrayList<PourDB> tab=new ArrayList<PourDB>();
        String rep="{?=call readpour1(?)}";
        CallableStatement cstmt=null;
        
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,numReserv);
            cstmt.executeQuery();
            
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            boolean ok=false;
            while(rs.next()){
                ok=true;
                String numChambre=rs.getString(1);
                int nRes=rs.getInt(2);
                tab.add(new PourDB(numChambre,nRes));
            }
            if(!ok)
                throw new Exception("Numéro de réservation inconnu");
            else
                return tab;     
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
    
    public void update() throws Exception{
        
    }
    /**
     * Fonction de suppression d'un Pour
     * @throws Exception 
     */
    public void delete() throws Exception{
        CallableStatement cstmt=null;
        try{
            String rep="call deletepour(?,?)";
            cstmt=dbConnect.prepareCall(rep);
            cstmt.setString(1,numero);
            cstmt.setInt(2,nReservation);
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
