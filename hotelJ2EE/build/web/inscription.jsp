<%-- 
    Document   : inscription
    Created on : 24-mai-2014, 13:34:33
    Author     : Spymannn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html>
<script type="text/javascript">
    //<![CDATA[

    function valider(){
      if(document.inscrip.nom.value ==""){
          alert("Nom manquant !");
          return false;
      }
      else if(document.inscrip.prenom.value =="" ) {
          alert("Prenom manquant !");
          return false;
      }
      else if(document.inscrip.adresse.value =="" ) {
          alert("Adresse manquante !");
          return false;
      }
      else if(document.inscrip.codePostal.value =="" ) {
          alert("Code postal manquant !");
          return false;
      }
      else if(document.inscrip.localite.value =="" ) {
          alert("Localite manquante !");
          return false;
      }
      else if(document.inscrip.mail.value =="" ) {
          alert("Adresse email manquante !");
          return false;
      }
      else if(document.inscrip.mdp.value =="" ) {
          alert("Mot de passe manquant !");
          return false;
      }
      else {
        return true;
      }
    }

    //]]>
    </script>

<logic:notPresent name="client" scope="session">
<form action="inscriptionMembre" onsubmit="return valider()" method="post" name="inscrip">
    <p> Nom : <input type="text" name="nom"/></p>
    <p> Prenom : <input type="text" name="prenom"/></p>
    <p> Adresse : <input type="text" name="adresse"/></p>
    <p> code postal : <input type="text" name="codePostal"/></p>
    <p> Localité : <input type="text" name="localite"/></p>
    <p> Adresse email : <input type="text" name="mail"/></p>
    <p> Mot de passe : <input type="password" name="mdp"/></p>

    <input type="submit" name="inscription" value="S'inscrire"/>
    <input type="reset" name="Annuler" value="annuler"/>

</form>
</logic:notPresent>
<logic:present  name="client" scope="session">
    <bean:define id="client" name="client" scope="session"/>
    <logic:empty name="client">
        Attention votre identite est vide
    </logic:empty>
    <logic:notEmpty name="client">
        Vous êtes déjà connecté avec l'adresse mail ${client.email} <br/>
        Veuillez vous déconnecter si vous voulez continuer.<br/>
        <form action="Deconnexion" method="post">
            <input type="submit" name="deconnexion" value="deconnexion">
       </form>
    </logic:notEmpty>
</logic:present>
<%@ include file="erreur.jsp" %>
