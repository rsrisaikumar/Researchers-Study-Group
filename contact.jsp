<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%@ include file="terminate.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">Contact</h3>
<%-- Code to go back to Main page  --%>
<a href="main.jsp?user=Hello,${theUser.getName()}" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to input Contact details and Send Message --%>
<br/><br/>
    <form class="form-horizontal" action="RCController" method="post">
        <input type="hidden" name="action" value="contact"/>
    	<div class="form-group">
        <label class="col-sm-4 control-label">Name *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="name" required />
        </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Email *</label>
        <div class="col-sm-4">
        <input type="email" class="form-control" name="email" required/>
        </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Message *</label>
         <div class="col-sm-4"> 
        <textarea name="message" class="form-control" required></textarea>
        </div>
            </div>
        <div class="form-group">
        <div class="col-sm-offset-5 col-sm-4">
        <button type="submit"  class="btn btn-primary">Submit</button>
        <br/><br/><br/><br/>
        </div>
            </div>
    </form>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>