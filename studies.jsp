<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%@ include file="terminate.jsp" %>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<%-- Code to display Page Name --%>
<h3 id="page_name">My Studies</h3>
 <%-- Code to add new study   --%>
<h3 id="add_new_study"><a href="newstudy.jsp?user=Hello,Kim" >Add a new study</a></h3>

 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp?user=Hello,${theUser.getName()}" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section>

<div class="table-responsive">
    <table class="table" >
        <tr>
            <th>Study Name</th>
            <th>Requested Participants</th>     
            <th>Participations</th>
            <th>Status</th>
            <th>Action</th>
            <th>Answers</th>
            <th>Share</th>
        </tr>
        
        <c:forEach items="${MyStudies}" var="element">
            <tr>
                <td><c:out value="${element.getStudyName()}"/></td>
                <td><c:out value="${element.getRequestedParticipants()}"/></td>
                <td><c:out value="${element.getNumOfParticipants()}"/></td>
                <td><form action="StudyController" method="post"><input type="hidden" name="action" value="<c:out value='${element.getStatus()}'/>">
                        <input type="hidden" name="StudyCode" value="${element.getStudyCode()}">
                        <button type="submit" class="btn btn-primary"><c:if test="${element.getStatus().equals('start')}">Stop</c:if><c:if test="${element.getStatus().equals('stop')}">Start</c:if></button></form></td>
                <td><form action="StudyController" method="post"><input type="hidden" name="action" value="edit">
                        <input type="hidden" name="StudyCode" value="${element.getStudyCode()}">
                    <button type="submit" class="btn btn-primary">Edit</button></form></td>
                <td><form action="StudyController" method="post"><input type="hidden" name="action" value="getanswer">
                        <input type="hidden" name="StudyCode" value="${element.getStudyCode()}">
                    <button type="submit" class="btn btn-primary">Answers</button></form></td>
                     <td><div class="fb-share-button" data-href="https://nbad3-sskassignment1.rhcloud.com/Assignment-4/ShareStudyController?action=active&studyid=${element.getStudyCode()}" data-layout="button"></div></td>
            </tr>
        </c:forEach>
    </table>
</div>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>