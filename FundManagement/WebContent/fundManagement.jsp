<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import = "com.FundRequesting" %>    
    
    <%
	session.setAttribute("statusMsg", "");
	System.out.println("Trying to process");
//Save---------------------------------
	if (request.getParameter("rID") != null)
	{
		FundRequesting itemObj = new FundRequesting();
		String stsMsg = "";
		
		//Insert--------------------------
		if (request.getParameter("hidItemIDSave") == "")
		{
					stsMsg = itemObj.insertDetails(request.getParameter("rID"),
					request.getParameter("rName"),
					request.getParameter("rAge"),
					request.getParameter("rAddress"),
					request.getParameter("rEmail"),
					request.getParameter("rPhoneNum"),
					request.getParameter("projName"),
					request.getParameter("projDesc"),
					request.getParameter("fund"));
		
		
		}
		else 
		{
					stsMsg = itemObj.updateResearcher(request.getParameter("hidItemIDSave"),
					request.getParameter("rName"),
					request.getParameter("rAge"),
					request.getParameter("rAddress"),
					request.getParameter("rEmail"),
					request.getParameter("rPhoneNum"),
					request.getParameter("projName"),
					request.getParameter("projDesc"),
					request.getParameter("fund"));
		
		
		}
		session.setAttribute("statusMsg", stsMsg);
		
	}

	//Delete-----------------------------
	if (request.getParameter("hidItemIDDelete") != null)
	{
    FundRequesting itemObj = new FundRequesting();
	String stsMsg =
	itemObj.deleteResearcher(request.getParameter("hidItemIDDelete"));
	session.setAttribute("statusMsg", stsMsg);
	}
%>   
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fund Requesting</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="text/javascript" src="Components/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function()
		{
			if ($("#alertSuccess").text().trim() == "")
			{
				$("#alertSuccess").hide();
			}
			$("#alertError").hide();
		});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	$("#btnSave").submit();
});

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#rID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#rName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#rAge").val($(this).closest("tr").find('td:eq(2)').text());
	$("#rAddress").val($(this).closest("tr").find('td:eq(3)').text());
	$("#rEmail").val($(this).closest("tr").find('td:eq(4)').text());
	$("#rPhoneNum").val($(this).closest("tr").find('td:eq(5)').text());
	$("#projName").val($(this).closest("tr").find('td:eq(6)').text());
	$("#projDesc").val($(this).closest("tr").find('td:eq(7)').text());
	$("#fund").val($(this).closest("tr").find('td:eq(8)').text());
	
});


// CLIENT-MODEL================================================================
function validateForm()
{
	// CODE
	if ($("#rID").val().trim() == "")
	{
		return "Insert  ID.";
	}
	
	// NAME
	if ($("#rName").val().trim() == "")
	{
		return "Insert  Name.";
	}
	
	if ($("#rAge").val().trim() == "")
	{
		return "Insert Age.";
	}
	
	if ($("#rAddress").val().trim() == "")
	{
		return "Insert Address.";
	}
	
	if ($("#rEmail").val().trim() == "")
	{
		return "Insert Email.";
	}
	
	// NAME
	if ($("#rPhoneNum").val().trim() == "")
	{
		return "Insert  Phone Number.";
	}
	
	if ($("#projName").val().trim() == "")
	{
		return "Insert Project Name.";
	}
	
	if ($("#projDesc").val().trim() == "")
	{
		return "Insert Project Description.";
	}
	
	if ($("#fund").val().trim() == "")
	{
		return "Insert Fund amount.";
	}
	
	
	return true;
}

</script>



</head>

<body>

   <div class="container">
   <div class="row">
   <div class="col-8">
 
     <center><h1 class="m-3">Fund Requesting</h1></center>
     <br>
     <form id="formResearcher" name="formResearcher method="post" action="fundManagement.jsp">
          Researcher's ID :  
         <input id="rID" name="rID" type="text" class="form-control form-control-sm">
         <br>
          Reseacher's Name: 
         <input id="rName" name="rName" type="text" class="form-control form-control-sm">
         <br> 
          Reseacher's Age: 
         <input id="rAge" name="rAge" type="text" class="form-control form-control-sm">
         <br> 
          Reseacher's Address:
         <input id="rAddress" name="rAddress" type="text" class="form-control form-control-sm">
         <br>
          Reseacher's Email:
         <input id="rEmail" name="rEmail" type="text" class="form-control form-control-sm">
         <br>
          Reseacher's PhoneNum:
         <input id="rPhoneNum" name="rPhoneNum" type="text" class="form-control form-control-sm">
         <br>
          Project Name:
         <input id="projName" name="projName" type="text" class="form-control form-control-sm">
         <br>
          Project description:
         <input id="projDesc" name="projDesc" type="text" class="form-control form-control-sm">
         <br>
          Fund:
         <input id="fund" name="fund" type="text" class="form-control form-control-sm">
         <br>
         <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
         <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
     </form>
     
  
   <div id="alertSuccess" class=alert alert-success>
     <%

             out.print(session.getAttribute("statusMsg"));
   
     %>
   
   </div>
   <div id="alertError" class=alert alert-danger>
   <br>
      <%

             out.print(session.getAttribute("statusMsg"));
   
     %>
   <%--jede --%>
   
   </div>
   
  </div>
  </div>
  </div>

</body>
</html>