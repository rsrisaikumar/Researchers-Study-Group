<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%@ include file="terminateadmin.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<h3><span id="studies">Reported Questions</span></h3><br/>
<a href="admin.jsp?user=Hello,${theAdmin.getName()}" id="back_to_page">&laquo;Back to the Main Page</a><br/>
<br/><br/><br/>


<!-- TODO: Add more code to get the table here.
  -->
  <div class="table-responsive">
  <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Question</th>
            <th>Action</th>
            <th>Total Reported</th>
        </tr>
        <c:forEach items="${AdminReportCheck}" var="element">
            <tr>
                <c:if test="${element.getStatus().equals('Disapproved')||element.getStatus().equals('Pending')}">
                <td><c:out value="${element.getQuestion()}"/></td> 
                <td>
                    <form action="StudyController" method="post"><input type="hidden" name="action" value="approve">
                        <input type="hidden" name="StudyCode" value="${element.getStudyCode()}">
                        <input type="submit" class="btn btn-primary" style="float:left;" value="Approve">
                    </form>
                    <form action="StudyController" method="post"><input type="hidden" name="action" value="disapprove">
                        <input type="hidden" name="StudyCode" value="${element.getStudyCode()}">
                        <input type="submit" class="btn btn-primary" style="float:left;"  value="Dispprove">
                    </form>
                    </td>
                    <td><c:out value="${element.getNumOfParticipants()}"/></td>
                </c:if>
            </tr>
        </c:forEach>
        
        </table>
        </div>
  
  
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>