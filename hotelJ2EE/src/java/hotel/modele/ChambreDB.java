package hotel.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Spymannn
 */
public class ChambreDB extends Chambre implements CRUD {
    /*
     * Connexion à la base de données
     */

    protected static Connection dbConnect = null;

    //Constructeur par défaut
    public ChambreDB() {
    }

    /**
     * Constructeur paramétré
     *
     * @param numero
     * @param id_modele
     * @param etage
     * @param lit_bebe
     * @param prix_default
     */
    public ChambreDB(String numero, int id_modele, int etage, int lit_bebe, float prix_default) {
        super(numero, id_modele, etage, lit_bebe, prix_default);
    }

    /**
     * Constructeur à utiliser lorsque la Chambre est présente dans la BDD mais
     * qu'on ne connait que le numéro, à utiliser avec read
     *
     * @param numero
     */
    public ChambreDB(String numero) {
        super(numero, 0, 0, 0, 0);
    }

    /**
     * Méthode statique qui permet de partager la connexion entre tous les
     * objets de ChambreDB
     *
     * @param nouvdbConnect
     */
    public static void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }

    /**
     * Méthode d'ajout d'une chambre dans la base de données
     *
     * @throws Exception
     */
    public void create() throws Exception {
        CallableStatement cstmt = null;
        boolean ok = true;
        try {
            String rep = "call createChambre(?,?,?,?,?)";
            cstmt = dbConnect.prepareCall(rep);
            cstmt.setString(1, numero);
            cstmt.setInt(2, id_modele);
            cstmt.setInt(3, etage);
            cstmt.setInt(4, lit_bebe);
            cstmt.setFloat(5, prix_default);

            ok = this.vLit_bebe(lit_bebe);
            if (!ok) {
                throw new Exception("Erreur dans l'entrée de lit_bebe");
            }
            ok = this.vPrix_default(prix_default);
            if (!ok) {
                throw new Exception("Erreur dans le prix par défaut");
            }
            cstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur dans la création" + e.getMessage());
        } finally {
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Lecture d'une chambre par son numéro
     *
     * @throws Exception
     */
    public void read() throws Exception {
        String req = "{?=call readchambre1(?)}";
        CallableStatement cstmt = null;

        try {
            cstmt = dbConnect.prepareCall(req);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, numero);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            if (rs.next()) {
                this.id_modele = rs.getInt("id_modele");
                this.etage = rs.getInt("etage");
                this.lit_bebe = rs.getInt("lit_bebe");
                this.prix_default = rs.getFloat("prix_default");
            } else {
                throw new Exception("Numéro de chambre inconnu");
            }
        } catch (Exception e) {
            throw new Exception("Erreur de lecture" + e.getMessage());
        } finally {
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Recherche des chambres entre 2 prix
     *
     * @param prix1
     * @param prix2
     * @return
     * @throws Exception
     */
    public static ArrayList<ChambreDB> rechChambrePrix(float prix1, float prix2) throws Exception {
        ArrayList<ChambreDB> tabChambre = new ArrayList<ChambreDB>();
        
        String req = "{?=call readchambre2(?,?)}";
        CallableStatement cstmt = null;
        boolean ok = true;

        try {
            cstmt = dbConnect.prepareCall(req);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setFloat(2, prix1);
            cstmt.setFloat(3, prix2);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            ok = false;
            while (rs.next()) {
                ok = true;
                String numChambre = rs.getString("numero");
                
                int idModele = rs.getInt("id_modele");
                int stage = rs.getInt("etage");
                int litBebe = rs.getInt("lit_bebe");
                float prixDefault = rs.getFloat("prix_default");
                tabChambre.add(new ChambreDB(numChambre, idModele, stage, litBebe, prixDefault));
            }
            
            if (!ok) {
                throw new Exception("Pas de chambre entre ces 2 prix");
            } else {
                return tabChambre;
            }
        } catch (Exception e) {
            throw new Exception("Erreur de lecture" + e.getMessage());
        } finally {
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     *
     */
    public static ArrayList<ChambreDB> rechChambreDate(Date d1, Date d2) throws Exception {
        ArrayList<ChambreDB> tabChambre = new ArrayList<ChambreDB>();
        ArrayList<ChambreDB> tabNumeroChambre = new ArrayList<ChambreDB>();
        String req1 = "{?=call readchambre3(?,?)}";
        String req = "{?=call verifchambre(?,?,?)}";
        CallableStatement cstmt = null;
        CallableStatement cstmt2 = null;
        boolean ok = true;

        try {
            cstmt = dbConnect.prepareCall(req1);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setDate(2, d1);
            cstmt.setDate(3, d2);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            ok = false;
            while (rs.next()) {
                ok = true;
                String numChambre = rs.getString("numero");
                tabNumeroChambre.add(new ChambreDB(numChambre, 0, 0, 0, 0));
                cstmt2 = dbConnect.prepareCall(req);
                cstmt2.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
                cstmt2.setString(2, numChambre);
                cstmt2.setDate(3, d1);
                cstmt2.setDate(4, d2);
                cstmt2.executeQuery();
                if (cstmt2.getObject(1) != null) {
                    ResultSet rs1 = (ResultSet) cstmt2.getObject(1);
                    if (rs1.next()) {
                        int idModele = rs1.getInt("id_modele");
                        int stage = rs1.getInt("etage");
                        int litBebe = rs1.getInt("lit_bebe");
                        float prixDefault = rs1.getFloat("prix_default");
                        tabChambre.add(new ChambreDB(numChambre, idModele, stage, litBebe, prixDefault));
                    }
                }              
/*
                int idModele = rs.getInt("id_modele");
                int stage = rs.getInt("etage");
                int litBebe = rs.getInt("lit_bebe");
                float prixDefault = rs.getFloat("prix_default");
                tabChambre.add(new ChambreDB(numChambre, idModele, stage, litBebe, prixDefault));*/
            }
            if (!ok) {
                throw new Exception("Pas de chambre entre ces 2 dates");
            } else {
                return tabChambre;
            }
        } catch (Exception e) {
            throw new Exception("Erreur de lecture" + e.getMessage());
        } finally {
            try {
                cstmt.close();
                cstmt2.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Mise à jour des données de la chambre via son numero
     *
     * @throws Exception
     */
    public void update() throws Exception {
        CallableStatement cstmt = null;
        try {
            String req = "call updateChambre(?,?,?,?)";
            cstmt = dbConnect.prepareCall(req);
            PreparedStatement pstm = dbConnect.prepareStatement(req);
            cstmt.setString(1, numero);
            cstmt.setInt(2, id_modele);
            cstmt.setInt(3, lit_bebe);
            cstmt.setFloat(4, prix_default);
            cstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur de mise à jour" + e.getMessage());
        } finally {
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Fonction de suppression d'une chambre avec son numéro
     *
     * @throws Exception
     */
    public void delete() throws Exception {
        CallableStatement cstmt = null;
        try {
            String req = "call deletechambre(?)";
            cstmt = dbConnect.prepareCall(req);
            cstmt.setString(1, numero);
            cstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur de suppression" + e.getMessage());
        } finally {
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }

}
