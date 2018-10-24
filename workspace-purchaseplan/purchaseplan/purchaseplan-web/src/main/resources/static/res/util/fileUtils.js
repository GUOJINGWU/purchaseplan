function fileLoad(ele, divId, attRelaId) {
	// 创建一个formData对象
	var formData = new FormData();
	// 获取传入元素的val
	var name = $(ele).val();
	// 获取files
	var files = $(ele)[0].files[0];
	// 将name 和 files 添加到formData中，键值对形式
	formData.append("file", files);
	formData.append("attrelaid", attRelaId);
	$(".loading-shade").show();
	$.ajax({
		url : "/web/file/upload",
		type : 'POST',
		data : formData,
		processData : false,// 告诉jQuery不要去处理发送的数据
		contentType : false,// 告诉jQuery不要去设置Content-Type请求头
		beforeSend : function() {
			// 发送之前的动作
		},
		success : function(data) {
			fileHtml(data, divId);
		},
		error : function(data) {
			// 出错后的动作
			console.log(data);
		}
	});
}

function removeFile(id, divId, attRelaId) {
	var txt = '<h3 class="alert-title" style="text-align: center;font-size:18px;margin-top:75px">确认删除？</h3>';
	confirmDialog("确认删除附件", txt, function() {
		var datas = {
			key : id,
			value : attRelaId
		};
		$(".loading-shade").show();
		$.ajax({
			url : '/web/file/remove',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			datType : "JSON",
			data : JSON.stringify(datas),
			async : false,
			success : function(data) {
				fileHtml(data, divId);
			},
			error : function(data) {
				console.log(data);
			}
		});
	});
}

function fileHtml(data, divId) {
	console.log(JSON.stringify(data));
	var json = data.dataList;
	var htmlValue = '<ul>';
	for ( var index in json) {
		console.log(json[index].fileName);
		htmlValue += '<li>';
		var filename = json[index].fileName.substring(json[index].fileName.indexOf('_') + 1, json[index].fileName.length);
		htmlValue += '<a href=' + json[index].downLoadUrl + '><span>' + filename + '</span></a>';
		htmlValue += '<a href="javascript:;" onClick="removeFile(&#39;' + json[index].id + '&#39;,&#39;' + divId + '&#39;,&#39;' + json[index].attRelaId
				+ '&#39;);"><img src="../../res/images/deleteIconBlue.png" alt=""></img><span>删除</span></a>';
		htmlValue += '</li>';
	}
	htmlValue += '</ul>';
	$("#" + divId).html(htmlValue);
	$(".loading-shade").hide();
}