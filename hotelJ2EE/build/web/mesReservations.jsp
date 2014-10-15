<%-- 
    Document   : mesReservations
    Created on : 26-mai-2014, 22:34:33
    Author     : Spymannn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<logic:notPresent name="client" scope="session">
    <p>Vous n'êtes pas connecté, vous ne pouvez donc pas voir vos réservations </p>
</logic:notPresent>
<logic:present  name="client" scope="session">
    <bean:define id="client" name="client" scope="session"/>
    <logic:empty name="client">
        Attention votre identite est vide
    </logic:empty>
    <logic:notEmpty name="client">
        Bonjour, ${client.prenom} ${client.nom} <br/>
        <logic:notPresent name="tabReserv" scope="session">
            <p>Pas de réservation pour vous ! </p>
        </logic:notPresent>
        <logic:present name="tabReserv" scope="session">
        Voici vos réservations !.<br/>
        <form action="suppressionReservation" method="post">
        <table >
            <th>  Numéro de réservation  </th>
            <th>  Date jour de départ  </th>
            <th>  Date jour d'arrivée  </th>
            <th>  Prix total  </th>
           
            <logic:iterate id="reserv" name="tabReserv">
                <tr align="center">
                    <td> ${reserv.nReservation} </td>
                    <td> ${reserv.dateJourDe} </td>
                    <td> ${reserv.dateJourA} </td>
                    <td> ${reserv.prixTotal} </td>
                    <td> 
                        <input type="hidden" name="nReserv" value="${reserv.nReservation}"/>
                        <input src="img/supprimer.png" type="image" name="envoie"/>
                    </td>
                </tr>  
            </logic:iterate>
       </logic:present>
        </table>
        
    </logic:notEmpty>
</logic:present>