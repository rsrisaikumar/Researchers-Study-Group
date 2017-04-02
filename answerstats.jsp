<%-- 
    Document   : answerstats
    Created on : Apr 17, 2016, 3:59:25 PM
    Author     : saiku
--%>

<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">Answers</h3>
 <%-- Code to add new study   --%>
 <%-- Code to go Back to the Main Page  --%>
<a href="home.jsp" id="back_to_page">&laquo;Sign up to participate</a><!DOCTYPE html>
<section>

<div class="table-responsive">
    <table class="table" >
        <tr>
            <th>Question</th>
        </tr>
                    <tr>
                <td><c:out value="${StudyStat.getQuestion()}"/></td>
            </tr>
    </table>
            <br><h3>Responses</h3><br>
            <center>
<c:if test="${isAnswer!=null}">        
<c:forEach begin="0" end="2" var="current">
<c:out value="${StudyStat.getAnswerChoice().get(current)}"/>:>
<c:out value="${AllAnswers[StudyStat.getAnswerChoice().get(current)]}"/>
<br/>
</c:forEach>
    </c:if>
       </center>
</div>
</section>
<%@ include file="footer.jsp" %>