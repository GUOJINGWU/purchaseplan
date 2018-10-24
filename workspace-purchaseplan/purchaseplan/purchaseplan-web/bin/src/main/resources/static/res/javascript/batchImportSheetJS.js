$(function() {
	$(".back-btn").click(function() {
		parent.searchPlan();
		top.CloseDialog("batchImportSheet");
	});

	$(".sure-btn").click(function() {
		fileUploead();
	});
});

function changeName() {
	var fileName = getFileNameIndex($("#fileExcel").val());
	$('#fileText').val(fileName);
}

function getFileNameIndex(o) {
	var start = o.lastIndexOf("\\") + 1;
	var end = o.lastIndexOf(".");
	return o.substring(start, end);
}

function getFileName(o) {
	var pos = o.lastIndexOf(".");
	return o.substring(pos + 1);
}

function fileUploead() {
	var formData = new FormData();
	formData.append("fileExcel", $("#fileExcel")[0].files[0]);
	formData.append("informationid", $("#informationId").val());
	formData.append("purchasetype", $("#purchaseType").val());
	$.ajax({
		url : "/purchaseplanrest/batchimportexcel",
		type : 'POST',
		data : formData,
		processData : false,// 告诉jQuery不要去处理发送的数据
		contentType : false,// 告诉jQuery不要去设置Content-Type请求头
		beforeSend : function() {
		},
		success : function(res) {
			console.log(res);
			if(res.status == 2){
				parent.searchPlan();
				top.CloseDialog("batchImportSheet");
				return;
			}else{
				AlertWarnMessage(res.desc);
			}
		},
		error : function(data) {
			// 出错后的动作
			console.log(data);
		}
	});
}