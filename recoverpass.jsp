<%-- 
    Document   : forgotpass
    Created on : Apr 6, 2016, 9:26:20 PM
    Author     : saiku
--%>

<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">Reset your Password</h3>
<%-- Code to display Page Name --%>
<a href="login.jsp" id="back_to_page">&laquo;Back to the Login Page</a>
<%-- Section to input Contact details and Send Message --%>
<p><i>${rp_msg}</i></p>
<section>
    <form class="form-horizontal" action="FPController" method="post">
        <input type="hidden" name="action" value="recoverpassword"/> 
        <input type="hidden" name="token" value="${token}"/>
        <div class="form-group">
        <label class="col-sm-4 control-label">Password *</label>
        <div class="col-sm-4">
        <input type="password" class="form-control" name="password" required/>
        </div>
            </div>                    

        <div class="form-group">
        <label class="col-sm-4 control-label">Confirm Password *</label>
        <div class="col-sm-4">
        <input type="password" class="form-control" name="confirm_password" required/>
        </div>
            </div>                    

        <div class="form-group">
        <div class="col-sm-offset-5 col-sm-4">
        <button type="submit"  class="btn btn-primary">Reset Password</button>
		</div>
            </div>
            
    </form>
</section>

<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>