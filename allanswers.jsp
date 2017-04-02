<%-- 
    Document   : allanswers
    Created on : Mar 27, 2016, 9:54:31 PM
    Author     : saiku
--%>

<%@ include file="header.jsp" %>
<%@ include file="terminate.jsp" %>

<%-- Code to display Page Name --%>
<h3 id="page_name">Answers</h3>
 <%-- Code to add new study   --%>
 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp?user=Hello,${theUser.getName()}" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<!DOCTYPE html>
<section>

<div class="table-responsive">
    <table class="table" >
        <tr>
            <th>Email</th>
            <th>Date</th>     
            <th>Choice</th>
        </tr>
        
        <c:forEach items="${AllAnswers}" var="element">
            <tr>
                <td><c:out value="${element.getEmailOfParticipant()}"/></td>
                <td><c:out value="${element.getSubmissionDate()}"/></td>
                <td><c:out value="${element.getChoice()}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</section>
<%@ include file="footer.jsp" %>