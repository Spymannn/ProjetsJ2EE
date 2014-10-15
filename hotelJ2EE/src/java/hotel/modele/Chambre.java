
package hotel.modele;

/**
 * Classe de gestion d'une chambre
 * @author Spymannn
 */
public class Chambre {
    protected String numero;
    protected int id_modele;
    protected int etage;
    protected int lit_bebe;
    protected float prix_default;
    
    //Constructeur par défaut
    public Chambre(){}
    
    //Constructeur paramétré
    public Chambre(String numero,int id_modele,int etage,int lit_bebe,float prix_default){
        this.numero=numero;
        this.id_modele=id_modele;
        this.etage=etage;
        this.lit_bebe=lit_bebe;
        this.prix_default=prix_default;
    }
    //getters
    public String getNumero() {
        return numero;
    }

    public int getId_modele() {
        return id_modele;
    }

    public int getEtage() {
        return etage;
    }

    public int getLit_bebe() {
        return lit_bebe;
    }

    public float getPrix_default() {
        return prix_default;
    }
    
    /**
     * setter numero
     * @param numero 
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    /**
     * setter id_modele
     * @param id_modele 
     */
    public void setId_modele(int id_modele) {
        this.id_modele = id_modele;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public void setLit_bebe(int lit_bebe) {
        this.lit_bebe = lit_bebe;
    }

    public void setPrix_default(float prix_default) {
        this.prix_default = prix_default;
    }
    /**
     * verification du prix par défaut
     * @param prix_default
     * @return 
     */
    public boolean vPrix_default(float prix_default) {
        return prix_default>0;
    }
    /**
     * vérification du lit bébé
     * @param lit_bebe
     * @return 
     */
    public boolean vLit_bebe(int lit_bebe) {
        return lit_bebe==1 || lit_bebe==0;
    }
    
    @Override
    public String toString() {
        return "\nChambre\n======= " + "\nNumero : " + numero + "\nId du modele : " + id_modele 
                + "\nEtage : " + etage + "\nLit bébé : " + lit_bebe + "\nPrix par défaut : " 
                + prix_default;
    }
    
}
