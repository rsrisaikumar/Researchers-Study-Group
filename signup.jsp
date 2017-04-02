<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Include tag is used to import header page --%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@include file="header.jsp" %>
<script src="js/passwordstrength.js"></script>
<%--Code to signup form --%>
<section>
<br/><br/><br/>
<p><i>${signup_msg}</i></p>
        <form class="form-horizontal" action="UserController" method="post">        
            <input type="hidden" name="action" value="signup" />
            <input type="hidden" name="rec_id" value="${rec_id}" />
            <input type="hidden" name="rec_token" value="${rec_token}" />
            <div class="form-group">
            <label class="col-sm-4 control-label">Name *</label>
            <div class="col-sm-4">
            <input type="text" class="form-control" name="name" required/>
            </div>
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label">Email *</label>
            <div class="col-sm-4">
            <input type="email" class="form-control" name="email" required/>
            </div>
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label">Password *</label>
            <div class="col-sm-4">
            <input type="password" id="password" class="form-control" name="password" required/>
            <span id="result"></span>
            </div>
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label">Confirm Password *</label>
            <div class="col-sm-4">
            <input type="password" class="form-control" id="confirm_password" name="confirm_password" required />
            <span id="result2"></span>
            </div>
	    </div>
	    <div class="form-group">
            <div class="col-sm-offset-5">
            <input type="submit" value="Create Account" class="btn btn-primary">
            </div>
            </div>
            <br><br/><br/>
        </form>
        </section>
  
<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>