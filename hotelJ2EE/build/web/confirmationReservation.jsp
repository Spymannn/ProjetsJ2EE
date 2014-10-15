<%-- 
    Document   : confirmationReservation
    Created on : 12-juin-2014, 22:20:14
    Author     : Spymannn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<form action="ConfirmationReserv" method="post">
    <p>Confirmez-vous la réservation pour les dates du ${dateDebut} jusqu'au ${dateFin}
        pour les chambres : 
    <table>
        <logic:iterate id="numero" name="numChambres">
        <tr>
             <td>${numero}</td>
        </tr>
        </logic:iterate>
    </table> ?
    <input type="submit" name="confirmer" value="Je confirme"/>
    <input type="submit" name="confirmer" value="J'annule">
</form>
