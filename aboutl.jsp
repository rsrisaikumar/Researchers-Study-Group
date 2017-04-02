<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%@ include file="terminate.jsp" %>

<%-- Code to display items in List --%>

<c:if test="${theUser.getName()!=null}">
<nav id="menu">
    <ul>
        <li>Coins (<span class="count">${theUser.getNumCoins()}</span>) </li>
        <li>Participants (<span class="count">${theUser.getNumOfParticipants()}</span>) </li>
        <li>Participation (<span class="count">${theUser.getNumParticipation()}</span>) </li>
        <li><br></li>
        <li><form action="StudyController" method="post"><input type="hidden" name="action" value="join"><input type="submit" style="background-color: transparent; border: 0;" value="Home"></form></li>
        <li><form action="StudyController" method="post"><input type="hidden" name="action" value="participate"><input type="submit" style="background-color: transparent; border: 0;" value="Participate"></form></li>
        <li><form action="StudyController" method="post"><input type="hidden" name="action" value="studies"><input type="submit" style="background-color: transparent; border: 0;" value="My Studies"></form></li>
        <li><a href="recommend.jsp?user=Hello,${theUser.getName()}">Recommend</a></li>
        <li><form action="RCController" method="post"><input type="hidden" name="action" value="joinc"><input type="submit" style="background-color: transparent; border: 0;" value="Contact"></form></li>
    </ul>
    </nav>
    </c:if>


 <c:if test="${theAdmin.getName()!=null}">
 <nav id="menu">
 <ul>
	    <li><a href="home.jsp?user=Hello,${theAdmin.getName()}">Home</a></li>
        <li><a href="reportques.jsp?user=Hello,${theAdmin.getName()}">Reported Questions</a></li>
  </ul>
 </nav>
</c:if>

<%-- Section tag is used to write description  --%>
<section class="main">
    <h3>About us</h3>
    <p class="lead">Researchers Exchange Participations is a platform for researchers 
        to exchange participations</p>
    <p class="lead">The aim of this platform is to encourage researchers participate in each others
        user studies. Moreover, recruiting serious participants has been always a burden on
        a researcher's shoulder, thus, this platform gives researchers the opportunity
        to do that effectively and in a suitable time.</p>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>