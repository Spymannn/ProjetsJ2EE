/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hotel.modele;

/**
 *
 * @author Spymannn
 */
public class Pour {
    protected String numero;
    protected int nReservation;
    /**
     * Constructeur par défaut
     */
    public Pour(){}
    /**
     * Constructeur paramétré de Pour
     * @param numero
     * @param nReservation 
     */
    public Pour(String numero,int nReservation){
        this.numero=numero;
        this.nReservation=nReservation;
    }

    public String getNumero() {
        return numero;
    }

    public int getnReservation() {
        return nReservation;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setnReservation(int nReservation) {
        this.nReservation = nReservation;
    }

    @Override
    public String toString() {
        return "Pour{" + "numero=" + numero + ", nReservation=" + nReservation + '}';
    }
    
    
}
