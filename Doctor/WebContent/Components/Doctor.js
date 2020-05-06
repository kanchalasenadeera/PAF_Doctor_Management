$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateDoctorForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	
	var method = ($("#didDoctorIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "DoctorAPI",
		type : method,
		data : $("#formDoctor").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onDoctorSaveComplete(response.responseText, status);
		}
	});
	
	//$("#formDoctor").submit();
});
// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
			$("#didDoctorIDSave").val($(this).closest("tr").find('#didDoctorIDUpdate').val());
			$("#docName").val($(this).closest("tr").find('td:eq(0)').text());
			$("#docAge").val($(this).closest("tr").find('td:eq(1)').text());
			$("#docGender").val($(this).closest("tr").find('td:eq(2)').text());
			$("#docSpecialization").val($(this).closest("tr").find('td:eq(3)').text());
			$("#hosID").val($(this).closest("tr").find('td:eq(4)').text());
			
		});

//---------------------------------------------------------------------------------------------------lol

function onDoctorSaveComplete(response, status)
{
    if (status == "success")
    {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
        } 
        else if (resultSet.status.trim() == "error")
        {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } 
    else if (status == "error")
    {
        $("#alertError").text("Error while saving.");
        $("#alertError").show();
    } 
    else
    {
        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();
    }
    
    $("#didDoctorIDSave").val("");
    $("#formDoctor")[0].reset();
}

$(document).on("click", ".btnRemove", function(event)
{
    $.ajax(
    {
        url : "DoctorAPI",
        type : "DELETE",
        data : "docID=" + $(this).data("doctorid"),
        dataType : "text",
        complete : function(response, status)
        {
            onDoctorDeleteComplete(response.responseText, status);
        }
    });
});

function onDoctorDeleteComplete(response, status)
{
    if (status == "success")
    {
        var resultSet = JSON.parse(response);
        
        if (resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
        } 
        else if (resultSet.status.trim() == "error")
        {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } 
    else if (status == "error")
    {
        $("#alertError").text("Error while deleting.");
        $("#alertError").show();
    } 
    else
    {
        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();
    }
}

//----------------------------------------------------------------------------------------------lol
// CLIENTMODEL=========================================================================
function validateDoctorForm() {
	// CODE
	if ($("#docName").val().trim() == "") {
		return "Insert Doctor name.";
	}
	// NAME
	if ($("#docAge").val().trim() == "") {
		return "Insert Doctor Age.";
	}
	// PRICE-------------------------------

	if ($("#docGender").val().trim() == "") {
		return "Insert Doctor Gender.";
	}
	
	if ($("#docSpecialization").val().trim() == "") {
		return "Insert Doctor Specialization.";
	}
	
	if ($("#hosID").val().trim() == "") {
		return "Insert Hospital ID.";
	}

	return true;
}

