/**
 *  Document: aboutl.jsp
 *	Created On: Feb 4, 2016
 *	Authors: Deepak Rohan
 */
//new_study_answers
//var answers = $("#new_study_answers").val()
//alert(answers)
//
//$('#new_study_answers').on('change', function() {
//	 alert( $(this).find(":selected").val() );
//});


   function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#blah')
                    .attr('src', e.target.result)
                    .width(150)
                    .height(200);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }




var answers = 3;
var i =1;

 $(document).ready(function() {
	 
	 while(answers > 0){
         var div = $("<div />");
         	
     		div.html(GetDynamicTextBox(""));
//     		alert("This is before: "+answers);
     		$("#TextBoxContainer").append(div);
     		answers--;
     		i++;
//     		alert("This is after: "+answers);
     }
	 
	      $('#new_study_answers').change(function() {
	    	  	answers = $(this).val();
//	            alert(answers);
	            $("#TextBoxContainer").html("");
//	            var i =1;
	            i = 1;
	            
	            while(answers > 0){
		            var div = $("<div />");
		            	
	            		div.html(GetDynamicTextBox(""));
//	            		alert("This is before: "+answers);
	            		$("#TextBoxContainer").append(div);
	            		answers--;
	            		i++;
//	            		alert("This is after: "+answers);
	            }
	      });
	});
 
 function GetDynamicTextBox(value) {
     var t=i-1;
	    return '<div class="form-group"><label class="col-sm-4 control-label">Answer '+i+' *</label><div class="col-sm-4"><input name = "DynamicTextBox'+t+'" class="form-control" type="text" value = "' + value + '" required /></div></div>'
	}

