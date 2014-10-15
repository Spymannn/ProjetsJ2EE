<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<head>
	<title>Spy Hotel</title>
	<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<header id="header">
    <img src="img/banniere.jpg" alt="Ma bannière" />
</header>
<nav id="menu">
    <jsp:include page="menu.jsp"/>
</nav>
	<div id="contenu">
               <logic:present parameter="page">
                      <bean:parameter id="pagedem" name="page"/>
                </logic:present>
                <logic:notPresent parameter="page">
                     <bean:define id="pagedem" value="accueil.jsp"/>
                </logic:notPresent>
                                
           <%try{%>
            <jsp:include page="${pagedem}"/> 
           <%}
            catch(Exception e){%>
            une erreur est survenue
            <%}%>
          </div>
	<footer id="footer">Responsable d'édition : 
		samir_hanini@spyhoteldeluxe.be
	</footer>
</body>
</html>
