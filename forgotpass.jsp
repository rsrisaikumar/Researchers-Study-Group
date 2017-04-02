<%-- 
    Document   : forgotpass
    Created on : Apr 4, 2016, 8:54:20 PM
    Author     : saiku
--%>

<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">Recover your Password</h3>
<%-- Code to display Page Name --%>
<a href="login.jsp" id="back_to_page">&laquo;Back to the Login Page</a>
<%-- Section to input Contact details and Send Message --%>
<p><i>${fp_msg}</i></p>
<section>
    <form class="form-horizontal" action="FPController" method="post">
        <input type="hidden" name="action" value="forgotpassword"/>       
        <div class="form-group">
        <label class="col-sm-4 control-label">Email *</label>
        <div class="col-sm-4">
        <input type="email" class="form-control" name="email" required/>
        </div>
            </div>                    
        <div class="form-group">
        <div class="col-sm-offset-5 col-sm-4">
        <button type="submit"  class="btn btn-primary">Send Reset Password Link</button>
		</div>
            </div>
            
    </form>
</section>

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>