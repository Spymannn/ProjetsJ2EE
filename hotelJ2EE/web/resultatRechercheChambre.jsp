<%-- 
    Document   : resultatRechercheChambre
    Created on : 24-mai-2014, 18:46:54
    Author     : Spymannn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

    <logic:present name="listeChambres" scope="session">
<p>Chambre correspondant à votre recherche<p/>
<form action="CreationReservation" method="post">
    <table border="1">
        <th>Numéro de chambre</th>
        <th>Etage</th>
        <th>Lit bébé</th>
        <th>Prix par nuit</th>
        <logic:iterate id="uneChambre" name="listeChambres">
            <tr>
                <td> ${uneChambre.numero} </td>
                <td> ${uneChambre.etage} </td>
                <td> ${uneChambre.lit_bebe} </td>
                <td> ${uneChambre.prix_default} </td>
                <logic:present name="client" scope="session">
                    <td><input type="checkbox" name="numChambre" value="${uneChambre.numero}"/></td>
                </logic:present>
            </tr>   
        </logic:iterate>
    </table>
    <logic:present name="client" scope="session">
        <input type="submit" name="action" value="Reserver"/>
    </logic:present>
    <logic:notPresent name="client" scope="session">
       <input type="submit" name="action" value="Se connecter"/>
    </logic:notPresent>
        <input type="submit" name="action" value="Nouvelle recherche"/>
</form>
    </logic:present>
<%@ include file="erreur.jsp" %> 
