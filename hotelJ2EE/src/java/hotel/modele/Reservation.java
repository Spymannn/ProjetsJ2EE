/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hotel.modele;

import java.sql.Date;

/**
 *
 * @author Spymannn
 */
public class Reservation {
    protected int nReservation;
    protected Date dateJourDe;
    protected int idClient;
    protected Date dateJourA;
    protected float prixTotal;
    protected int nbreLitBebeReserv;
    protected float acompte;
    protected Date dateReserv;
    protected Date dateLim;
    protected Date dateVers;
    
    /**
     * Constructeur par défaut
     */
    public Reservation(){}
    /**
     * Constructeur paramétrés de Reservation
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
    public Reservation(int nReservation,Date dateJourDe,int idClient,Date dateJourA,
            float prixTotal,int nbreLitBebeReserv,float acompte,Date dateReserv,
            Date dateLim,Date dateVers){
        this.nReservation=nReservation;
        this.dateJourDe=dateJourDe;
        this.idClient=idClient;
        this.dateJourA=dateJourA;
        this.prixTotal=prixTotal;
        this.nbreLitBebeReserv=nbreLitBebeReserv;
        this.acompte=acompte;
        this.dateReserv=dateReserv;
        this.dateLim=dateLim;
        this.dateVers=dateVers;
    }

    public int getnReservation() {
        return nReservation;
    }

    public Date getDateJourDe() {
        return dateJourDe;
    }

    public int getIdClient() {
        return idClient;
    }

    public Date getDateJourA() {
        return dateJourA;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public int getNbreLitBebeReserv() {
        return nbreLitBebeReserv;
    }

    public float getAcompte() {
        return acompte;
    }

    public Date getDateReserv() {
        return dateReserv;
    }

    public Date getDateLim() {
        return dateLim;
    }

    public Date getDateVers() {
        return dateVers;
    }

    public void setnReservation(int nReservation) {
        this.nReservation = nReservation;
    }

    public void setDateJourDe(Date dateJourDe) {
        this.dateJourDe = dateJourDe;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setDateJourA(Date dateJourA) {
        this.dateJourA = dateJourA;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void setNbreLitBebeReserv(int nbreLitBebeReserv) {
        this.nbreLitBebeReserv = nbreLitBebeReserv;
    }

    public void setAcompte(float acompte) {
        this.acompte = acompte;
    }

    public void setDateReserv(Date dateReserv) {
        this.dateReserv = dateReserv;
    }

    public void setDateLim(Date dateLim) {
        this.dateLim = dateLim;
    }

    public void setDateVers(Date dateVers) {
        this.dateVers = dateVers;
    }

    @Override
    public String toString() {
        return "Reservation{" + "nReservation=" + nReservation + ", dateJourDe=" + dateJourDe + ", idClient=" + idClient + ", dateJourA=" + dateJourA + ", prixTotal=" + prixTotal + ", nbreLitBebeReserv=" + nbreLitBebeReserv + ", acompte=" + acompte + ", dateReserv=" + dateReserv + ", dateLim=" + dateLim + ", dateVers=" + dateVers + '}';
    }
            
    
}
