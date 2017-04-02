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
<%-- Code to Display Question--%>
<section class="question_section">
    <h3><span class="label label-default" >Question</span></h3>
    <%-- Img tag to display image--%>
    <img src="${StudyQuestion.getImageURL()}" class="img-responsive" height="250" width="250"/>
    <p class="text-left">${StudyQuestion.getQuestion()}(3 strongly agree - 5 strongly disagree)</p>
<%--Code to rating the Question --%>
        <form action="StudyController" method="post">
            <input type="hidden" name="action" value="answer">
            <input type="hidden" name="StudyCode" value="${StudyQuestion.getStudyCode()}">
            <c:forEach items="${StudyQuestion.getAnswerChoice()}" var="element">
                <div class="radio">
                    <input type="radio" name="choice" value="<c:out value='${element}'/>" required><c:out value='${element}'/>
                </div>                
             </c:forEach>       
<%-- Code to submit the Rating  --%>
    
         <div class="form-group">
        <div class="col-sm-offset-3 col-sm-4">
        <button type="submit"  class="btn btn-primary">Submit</button>
         </div>
            </div>
            <br/><br/><br/>   
        </form>
        
    
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>