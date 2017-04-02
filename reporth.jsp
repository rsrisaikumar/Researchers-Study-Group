<%-- 
    Document   : reporth
    Created on : Feb 5, 2016, 5:20:55 PM
    Author     : Abhishek Banerjee
--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%@ include file="terminate.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<a href="main.jsp?user=Hello,${theUser.getName()}" id="back_to_page">&laquo;Back to the Main Page</a>
<br>
 <div class="table-responsive">
<table class="table" >
        <%--Column Names --%>
        <c:if test="${ReportHistory.size()==0}">
<section>
    <h3 class="text-center">No Reports. . .</h3>
</section>
            
        </c:if>
        <c:if test="${ReportHistory.size()!=0}">
        <tr>
            <th>Report Date</th>
            <th>Report Question</th>		
            <th>Report Status</th>
            
        </tr>
        <c:forEach items="${ReportHistory}" var="element">
            <tr>
                <td><c:out value="${element.get(1)}"/></td>
                <td><c:out value="${element.get(2)}"/></td>
                <td><c:out value="${element.get(3)}"/></td>
            </tr>
        </c:forEach>
            </c:if>
    </table>
    </div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>