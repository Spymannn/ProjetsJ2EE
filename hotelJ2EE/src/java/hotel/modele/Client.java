
package hotel.modele;

/**
 * Classe de gestion d'un client
 * @author Spymannn
 */
public class Client {
    protected int id_client;
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected String npostal;
    protected String localite;
    protected String email;
    protected String mdp;
    
    //Constructeur par défaut
    public Client(){}
    
    //Constructeur paramétré
    public Client(int id_client,String nom,String prenom,String adresse,String npostal,String localite,String email,String mdp){
        this.id_client=id_client;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.npostal=npostal;
        this.localite=localite;
        this.email = email;
        this.mdp = mdp;
    }
    
    //getters
    public int getId_client() {
        return id_client;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNpostal() {
        return npostal;
    }

    public String getLocalite() {
        return localite;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }
    
    //setters
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNpostal(String npostal) {
        this.npostal = npostal;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Client{" + "id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", npostal=" + npostal + ", localite=" + localite + ", email=" + email + ", mdp=" + mdp + '}';
    }
    
    
    
}
