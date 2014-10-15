<%-- 
    Document   : erreur
    Created on : 24-mai-2014, 12:29:46
    Author     : Spymannn
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<logic:notEmpty name="erreur">
        Les erreurs suivantes ont été rencontrées :<br/>
        <ul>
        <logic:iterate id="uneErreur" name="erreur" >
            <li>   ${uneErreur}</li>
        </logic:iterate>
        <%-- Cette page d'erreur regarde si la liste des erreurs est vide <logic:notEmpty, si c'est pas vide
        on fait un <ul> <li> --%>
        </ul>
 </logic:notEmpty>
