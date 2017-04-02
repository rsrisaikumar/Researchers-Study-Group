<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%@ include file="terminate.jsp" %>
<script type="text/javascript" src="js/editstudy.js"></script>
<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js">    </script>

<%-- Code to display Page Name --%>
<h3 id="page_name">Editing a study</h3>
<%-- Code to go back to Main page  --%>
<a href="main.jsp?user=Hello,${theUser.getName()}" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to input study details --%>
<section>
    <form class="form-horizontal" action="StudyController" enctype="multipart/form-data" method="post" >
        <input type="hidden" name="action" value="update">
          <input type="hidden" name="StudyCode" value="${EditStudy.getStudyCode()}">
    	<div class="form-group">
        <label class="col-sm-4 control-label">Study Name *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="study_name"  value="${EditStudy.getStudyName()}" required />
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Question Text *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="question_text"  value="${EditStudy.getQuestion()}" required/>
         </div>
            </div>
            <input type="hidden" name="datecreated" value="${EditStudy.getDateCreated()}">
            <input type="hidden" name="studystatus" value="${EditStudy.getStatus()}">
            <input type="hidden" name="nmparticipants" value="${EditStudy.getNumOfParticipants()}">
        
        
        <%-- Img tag is used to import image --%>
 
        <div class="form-group">
        <label class="col-sm-4 control-label">Image *</label>
        <div class="col-sm-4">                
         <img src="${EditStudy.getImageURL()}"  id="blah" class="img-responsive" height="25" width="50" alt="${EditStudy.getImageURL()}"/>
        <input type="file" name="file" accept="image/*" onchange="readURL(this);" class="btn btn-primary">
         </div>
            </div>
        
    
         
        <div class="form-group">
        <label class="col-sm-4 control-label"># Participants *</label>
         <div class="col-sm-4"> 
        <input type="text" class="form-control" name="participants"  value="${EditStudy.getRequestedParticipants()}" required/>
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Answers *</label>
        <div class="col-sm-4">
        <select class="form-control" name="answers"  id="new_study_answers">
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select> <br>
         </div>
            </div>
        
        
        <div id="TextBoxContainer">
    <!--Textboxes will be added here -->
		</div>
       
       
       <div class="form-group">
        <label class="col-sm-4 control-label">Description *</label>
         <div class="col-sm-4"> 
        <textarea name="description" class="form-control" required>${EditStudy.getDescription()}</textarea>
         </div>
            </div>
        
        <div class="form-group">
        <div class="col-sm-offset-5 col-sm-4">
        <button type="submit"  class="btn btn-primary">Update</button>
         </div>
            </div>
            <br/><br/><br/>
    </form>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>