<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%@ include file="terminate.jsp" %>

<%-- Code to display items in List --%>
<nav id="menu">
<%--<c:if test="${param.user == 'Hello,'}${theUser.getName() }">--%>
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
<%--
</c:if>
<c:if test="${param.user == 'Hello,Admin' }">
		<li><a href="home.jsp?user=Hello,Admin">Home</a></li>
        <li><a href="reportques.jsp?user=Hello,Admin">Reported Questions</a></li>
</c:if-->
--%>
</nav>
<%-- Section tag is used to write description  --%>
<section class="main">
<h3>How it Works</h3>
    <p>This site was built to help researchers conduct their user studies</p>
    <p>1 participation = 1 coin</p>
    <p><b>To participate,</b> go to "Participate" section and choose a study to complete</p>
    <p><b>To get participants,</b> submit your study here to start getting Participations. In order to do so, you must have enough coins in Your account</p>
</section>

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>