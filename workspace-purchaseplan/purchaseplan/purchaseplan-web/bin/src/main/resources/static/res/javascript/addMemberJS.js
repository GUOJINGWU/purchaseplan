$(function() {
	$(".back-btn").click(function() {
		top.CloseDialog("add-people");
	});

	$("#success").click(function() {
		var recipientId = $('#recipientId').val();
		var recipientName = $('#recipientName').val();
		var companyId = $('#companyId').val();
		var companyName = $('#companyName').val();
		parent.modifyCompanyName(recipientId, recipientName, companyId, companyName);
		top.CloseDialog("add-people");
	});
});

function removeMember(recipientId, recipientName, companyId, companyName) {
//	reBack();
	var htmlValue = '<a onclick="addMember(&#39;' + recipientId + '&#39;,&#39;' + recipientName + '&#39;,&#39;' + companyId + '&#39;,&#39;' + companyName
			+ '&#39;)" class="plan-a" >选择 </a>';
	console.log(recipientId);
	console.log(htmlValue);
	$('#recipientId').val('');
	$('#recipientName').val('');
	$('#companyId').val('');
	$('#companyName').val('');
	$('#selectedPerson').html('');
	$('#' + recipientId).html(htmlValue, function() {
		addMember(recipientId, recipientName, companyId, companyName);
	});
}

function addMember(recipientId, recipientName, companyId, companyName) {
	reBack();
	var htmlValue = '<a onclick="removeMember(&#39;' + recipientId + '&#39;,&#39;' + recipientName + '&#39;,&#39;' + companyId + '&#39;,&#39;' + companyName
			+ '&#39;)" class="plan-a" >取消 </a>';
	console.log(recipientId);
	console.log(htmlValue);
	$('#recipientId').val(recipientId);
	$('#recipientName').val(recipientName);
	$('#companyId').val(companyId);
	$('#companyName').val(companyName);
	$('#selectedPerson').html(recipientName);
	$('#' + recipientId).html(htmlValue, function() {
		removeMember(recipientId, recipientName, companyId, companyName);
	});
}

function reBack() {
	var recipientIdBack = $('#recipientId').val();
	var recipientNameBack = $('#recipientName').val();
	var companyIdBack = $('#companyId').val();
	var companyNameBack = $('#companyName').val();
	var htmlValueBack = '<a onclick="addMember(&#39;' + recipientIdBack + '&#39;,&#39;' + recipientNameBack + '&#39;,&#39;' + companyIdBack + '&#39;,&#39;' + companyNameBack
			+ '&#39;)" class="plan-a" >选择 </a>';
	$('#' + recipientIdBack).html(htmlValueBack, function() {
		addMember(recipientIdBack, recipientNameBack, companyIdBack, companyNameBack);
	});
}
