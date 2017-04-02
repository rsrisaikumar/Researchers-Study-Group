<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%@include file="header.jsp" %>
<%@ include file="terminateadmin.jsp" %>

<!-- TODO: Change the menu as per the admin page -->
<nav id="menu">
    <ul>
       
        <li><a href="home.jsp?user=Hello,${theAdmin.getName()}">Home</a></li>
  <li><form action="StudyController" method="post"><input type="hidden" name="action" value="adminreportcheck">
          <input type="submit" style="background-color: transparent; border: 0;" value="Reported Questions"></form></li>
        
    </ul>
</nav>

<section class="main">
    <h3>How it Works</h3>
    <p>This site was built to help researchers conduct their user studies</p>
    <p>1 participation = 1 coin</p>
    <p><b>To participate,</b> go to "Participate" section and choose a study to complete</p>
    <p><b>To get participants,</b> submit your study here to start getting Participations. In order to do so, you must have enough coins in Your account</p>

</section>

<%@include file="footer.jsp" %>