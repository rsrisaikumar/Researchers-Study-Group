<%--
	Document: confirmpass.jsp
	Created On: Apr 6, 2016
	Authors: Sri Sai Kumar

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<a href="login.jsp" id="back_to_page">&laquo;Back to the Login Page</a>
<%-- Section tag is used to display Message Sent   --%>
<section>
    <h3 class="text-center">${confirm_msg}</h3>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>