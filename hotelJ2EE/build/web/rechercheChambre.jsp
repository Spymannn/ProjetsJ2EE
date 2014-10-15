<%-- 
    Document   : rechercheChambre
    Created on : 24-mai-2014, 12:53:39
    Author     : Spymannn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<head>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script>
    $(function() {
    $( "#dateDebut" ).datepicker({ dateFormat: "dd/mm/yy" });
    $( "#dateFin" ).datepicker({ dateFormat: "dd/mm/yy" });
    });
    </script>
    
    <script type="text/javascript">
    //<![CDATA[

    function valider(){
      // si la valeur du champ prenom est non vide
      if(document.rechCham.dateDebut.value != "" && document.rechCham.dateFin.value != "") {
        return true;
      }
      else {
        // sinon on affiche un message
        alert("Date manquante !");
        // et on indique de ne pas envoyer le formulaire
        return false;
      }
    }

    //]]>
    </script>
</head>
<body>
    
    <form action="rechercheChambre" onsubmit="return valider()" method="post" name="rechCham">

        <p>Date de début : <input type="text" id="dateDebut" name="dateDebut"></p>
        <p>Date de fin : <input type="text" id="dateFin" name="dateFin"></p>
        <p>Nombre de personnes : 
            <SELECT name="nbrePersonnes">
                <OPTION VALUE="un">1</OPTION>
                <OPTION VALUE="deux">2</OPTION>
                <OPTION VALUE="trois">3</OPTION>
                <OPTION VALUE="quatre">4</OPTION>
                <OPTION VALUE="cinq">5</OPTION>
            </SELECT>  
        </p>
        <p>Voulez-vous un lit bébé ? 
        oui : <INPUT type=radio name="litbebe" value="oui">
	non : <INPUT type=radio name="litbebe" value="non" checked>
	</p>
        <p>tranche de prix (par nuit) : 
            <SELECT name="prix1">
                <OPTION VALUE="0">0</OPTION>
                <OPTION VALUE="20">20</OPTION>
                <OPTION VALUE="40">40</OPTION>
                <OPTION VALUE="60">60</OPTION>
                <OPTION VALUE="80">80</OPTION>
            </SELECT> 
            <SELECT name="prix2">
                <OPTION VALUE="100">100</OPTION>
                <OPTION VALUE="80">80</OPTION>
                <OPTION VALUE="60">60</OPTION>
                <OPTION VALUE="50">50</OPTION>
                <OPTION VALUE="40">40</OPTION>
            </SELECT> 
        </p>
        <input type="submit" name="rechercher" value="recherche"/>
        <input type="reset" name="annuler" value="annuler"/>
    </form>
    <%@ include file="erreur.jsp" %> 
    
</body>