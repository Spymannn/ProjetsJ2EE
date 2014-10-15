<%-- 
    Document   : confirmationSuppression
    Created on : 13-juin-2014, 13:36:51
    Author     : Spymannn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<form action="ConfirmationSupp" method="post">
    <logic:present name="reservSupp" scope="session">
        <logic:notEmpty name="reservSupp" scope="session">
            <p>Confirmez-vous la suppression pour la réservation ${reservSupp.nReservation} ?</p>

            <input type="submit" name="confirmerSupp" value="Je confirme"/>
            <input type="submit" name="confirmerSupp" value="J'annule">
        </logic:notEmpty>
    </logic:present>
</form>