<%-- 
    Document   : menu
    Created on : 24-mai-2014, 12:19:30
    Author     : Spymannn
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<h2>Bienvenue sur notre site d'Hotel</h2>
<ul>
    <li>
        <a href="index.jsp?page=accueil.jsp">Accueil</a>
    </li>
    <li>
        <a href="index.jsp?page=inscription.jsp">Inscription</a>
    </li>
    <li>
        <a href="index.jsp?page=rechercheChambre.jsp">Recherche</a>
    </li>
    <logic:present name="client" scope="session">
        <li>
            <a href="Reservation" name="reservation">Mes réservations</a>
        </li>
    </logic:present>
    <li>
        <a href="index.jsp?page=contact.jsp">Contact</a>
    </li>
    <logic:present  name="client" scope="session">
    <li>
        
            <logic:notEmpty name="client" >
                ${client.prenom} ${client.nom} <br/>
                <a href="Deconnexion" name="deconnexion">Deconnexion</a>
            </logic:notEmpty>
                
        
    </li>
    </logic:present>
    <logic:notPresent name="client" scope="session">
        <li>
                    <a href="index.jsp?page=accueil.jsp">Connexion</a>
        </li>
                </logic:notPresent>
</ul>

