
package hotel.modele;

/**
 * @author Spymannn
 */
import java.sql.*;
import java.util.*;

public class ClientDB extends Client implements CRUD{
    protected static Connection dbConnect=null;
    
    //Constructeur par défaut
    public ClientDB(){}
    
    //Constructeur paramétré 1
    public ClientDB(String nom,String prenom,String adresse,String npostal,String localite,String email, String mdp){
        super(0,nom,prenom,adresse,npostal,localite,email,mdp);      
    }
    
    //Constructeur paramétré 2
    public ClientDB(int id_client,String nom,String prenom,String adresse,String npostal,String localite,String email, String mdp){
        super(id_client,nom,prenom,adresse,npostal,localite,email,mdp);
    }
    
    //Constructeur paramétré 3
    public ClientDB(int id_client){
        super(id_client,"","","","","","","");
    }
    
    //Méthode statique qui permet de partager la connexion entre toutes les instances de ClientDB
    public static void setConnection(Connection nouvdbConnect){
        dbConnect=nouvdbConnect;
    }
    
    //Enregistrement d'un nouveau Client à la base de données
    public void create() throws Exception{
        CallableStatement cstmt=null;
        try{
            String call="call createCli(?,?,?,?,?,?,?,?)";
            cstmt = dbConnect.prepareCall(call);
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setString(2,nom);
            cstmt.setString(3,prenom);
            cstmt.setString(4,adresse);
            cstmt.setString(5,npostal);
            cstmt.setString(6,localite);
            cstmt.setString(7,email);
            cstmt.setString(8,mdp);
            cstmt.executeUpdate();
            this.id_client=cstmt.getInt(1);
            
        }
        catch(Exception e){
            throw new Exception("Erreur de création : "+e.getMessage());
        }
        finally{// ce qui est effectué dans tout les cas, vu au cours rapidement
            try{
                cstmt.close();//on doit fermer le CallableStatement
            }
            catch(Exception e){
                System.out.println("Erreur lors de la fermeture du Callable Statement");
            }
        }
    }
    
    //Récupération des données d'un client par rapport à son ID
    public void read() throws Exception{
        String rep="{?=call readcli(?)}";
        
        CallableStatement cstmt=null;
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,id_client);
            cstmt.executeQuery();
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            if(rs.next()){
                this.nom=rs.getString("NOM");
                this.prenom=rs.getString("PRENOM");
                this.adresse=rs.getString("ADRESSE");
                this.npostal=rs.getString("NPOSTAL");
                this.localite=rs.getString("LOCALITE");
                this.email = rs.getString("email");
                this.mdp = rs.getString("password");
            }
            else
                throw new Exception("Code inconnu");
        } catch(Exception e){
            throw new Exception("Erreur lecture"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            } catch(Exception e){}  
        }
    }
    /*
     * Méthode statique qui permet de rechercher un Client avec son nom
     */
    public static ArrayList<ClientDB> rechercheNom(String nom) throws Exception{
        ArrayList<ClientDB> tab=new ArrayList<ClientDB>();
        String rep="{?=call readclinom(?)}";
        
        CallableStatement cstmt=null;
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2,nom);
            cstmt.executeQuery();
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            boolean found=false;
            while(rs.next()){
                found=true;
                int id_cl=rs.getInt("ID_CLIENT");
                String name=rs.getString("NOM");
                String prenom=rs.getString("PRENOM");
                String adresse=rs.getString("ADRESSE");
                String localite=rs.getString("LOCALITE");
                String npostal=rs.getString("NPOSTAL");
                String email = rs.getString("email");
                String mdp = rs.getString("password");
                tab.add(new ClientDB(id_cl,name,prenom,adresse,npostal,localite,email,mdp));
            }
            if(!found) throw new Exception("Nom inconnu!");
            else
                return tab;
        } catch(Exception e){
            throw new Exception("Erreur lecture "+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            } catch(Exception e){}
        }
    }
    /*
     * Méthode statique qui permet de rechercher un Client avec son nom
     */
    public static ClientDB rechercheEmail(String email) throws Exception{
        ClientDB client=null;
        String rep="{?=call readcliEmail(?)}";
        
        CallableStatement cstmt=null;
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2,email);
            cstmt.executeQuery();
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            boolean found=false;
            while(rs.next()){
                found=true;
                 int id_cl=rs.getInt("ID_CLIENT");
                String name=rs.getString("NOM");
                String prenom=rs.getString("PRENOM");
                String adresse=rs.getString("ADRESSE");
                String localite=rs.getString("LOCALITE");
                String npostal=rs.getString("NPOSTAL");
                String mdp = rs.getString("password");
                client = new ClientDB(id_cl,name,prenom,adresse,localite,npostal,email,mdp);
            }
            if(!found) throw new Exception("email inconnu!");
            else
                return client;
        } catch(Exception e){
            throw new Exception("Erreur lecture "+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            } catch(Exception e){}
        }
    }
    /*
     * Méthode qui permet de mettre à jour un client
     * via son identifiant
     */
    public void update() throws Exception{
        CallableStatement cstmt=null;
        try{
            String rep="call updatecli(?,?,?,?,?,?)";
            cstmt=dbConnect.prepareCall(rep);
            PreparedStatement p=dbConnect.prepareStatement(rep);
            cstmt.setInt(1,id_client);
            cstmt.setString(2,nom);
            cstmt.setString(3,prenom);
            cstmt.setString(4,adresse);
            cstmt.setString(5,npostal);
            cstmt.setString(6,localite);
            //int nl = cstmt.executeUpdate();
            //if(nl==0) throw new Exception("Aucune ligne mise à jour");
            cstmt.executeUpdate();
        } catch(Exception e){
            throw new Exception("Erreur dans la mise à jour "+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            } catch(Exception e){}
        }
    }
    /*
     * Méthode qui permet d'effacer un client via son id
     */
    public void delete() throws Exception{
        CallableStatement cstmt=null;
        try{
            String rep="call delcli(?)";
            cstmt=dbConnect.prepareCall(rep);
            cstmt.setInt(1,id_client);
            /*int nl = cstmt.executeUpdate();
            if(nl==0) throw new Exception("Aucune ligne supprimée"); */
            cstmt.executeUpdate();
            //System.out.println("nbre de ligne : "+nl);
        } catch(Exception e){
            throw new Exception("Erreur durant la suppression "+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            } catch(Exception e){}
        }
    }
}
