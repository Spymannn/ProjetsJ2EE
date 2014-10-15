<%-- 
    Document   : accueil
    Created on : 24-mai-2014, 12:27:25
    Author     : Spymannn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<script type="text/javascript">
    //<![CDATA[

    function valider(){
      if(document.conn.email.value ==""){
          alert("Login manquant !");
          return false;
      }
      else if(document.conn.mdp.value =="" ) {
          alert("Mot de passe manquant !");
          return false;
      }
      else {
        return true;
      }
    }

    //]]>
    </script>
<p><h1>Bienvenue sur le site de notre Hotel online</h1><p/>

<logic:notPresent name="client" scope="session">
    <form action="connexion" method="post" onsubmit="return valider()" name="conn">
        <p> login : <input type="text" name="email"/></p>
        <p> Mot de passe : <input type="password" name="mdp"/></p>
        <input type="submit" name="login" value="login"/>
    </form>
</logic:notPresent>
<logic:present  name="client" scope="session">
    <bean:define id="client" name="client" scope="session"/>
    <logic:empty name="client">
        Attention votre identite est vide
    </logic:empty>
    <logic:notEmpty name="client">
        Bonjour, ${client.prenom} ${client.nom}<br/>
        Pas de nouvelles pour aujourd'hui.<br/>
        <form action="Deconnexion" method="post">
            <input type="submit" name="deconnexion" value="deconnexion">
       </form>
    </logic:notEmpty>
</logic:present>


<%@ include file="erreur.jsp" %> 



