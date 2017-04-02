<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<%-- Section to display studies and participate in that study--%>
<div>
   
    <h3 class="text-left">
         <form action="StudyController" method="post"><input type="hidden" name="action" value="report">
             <span class="label label-default " ><label>Studies</label></span>
               <span class="label label-default">  <input type="submit" class="participate_submit" value="Report History"></span></form>
        </h3>
    <%-- Display the studies in the table --%>
    <%-- Clicking on Participate button displays Question.jsp page where 
            you can rate the question--%>
     <div class="table-responsive">
    <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Study Name</th>
            <th>Image</th>      
            <th>Question</th>
            <th>Action</th>
            <th>Report</th>
        </tr>
        <c:forEach items="${OpenStudies}" var="element">
         <tr>
         <td><c:out value="${element.getStudyName()}"/></td>
         <td><img src="<c:out value='${element.getImageURL()}'/>" alt="Tree"></td>
         <td><c:out value="${element.getQuestion()}"/></td>
         <td><form action="StudyController" method="post"><input type="hidden" name="action" value="participate"><input type="hidden" name="StudyCode" 
                                                          value="<c:out value='${element.getStudyCode()}'/>">
                                                                    <input type="submit" class="participate_button"
                                                                                value="Participate" /></form></td>
         <td><form action="StudyController" method="post"><input type="hidden" name="action" 
                                                          value="report"><input type="hidden" name="StudyCode" 
                                                          value="<c:out value='${element.getStudyCode()}'/>">
                                                          <input type="submit" class="participate_button" value="report"></form></td>               

           </tr>            
</c:forEach>
    </table>
    </div>


<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>