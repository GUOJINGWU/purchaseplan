$(function() {
	layui.use('laydate', function() {
		var laydate = layui.laydate;

		var start = {
			min : laydate.now(),
			max : '2099-06-16 23:59:59',
			istoday : false,
			choose : function(datas) {
				end.min = datas; // 开始日选好后，重置结束日的最小日期
				end.start = datas // 将结束日的初始值设定为开始日
			}
		};

		var end = {
			min : laydate.now(),
			max : '2099-06-16 23:59:59',
			istoday : false,
			choose : function(datas) {
				start.max = datas; // 结束日选好后，重置开始日的最大日期
			}
		};
		document.getElementById('LAY_demorange_s').onclick = function() {
			start.elem = this;
			laydate(start);
		}
		document.getElementById('LAY_demorange_e').onclick = function() {
			end.elem = this
			laydate(end);
		}
	});
});

// 点击清空
function onClear() {
	$(".input-clear").val('');
}

function createNewDemand(purchaseType) {
	var datas = {};
	$.ajax({
		url : '/purchaseplanrest/createnewdemand',
		type : 'POST',
		contentType : 'application/json; charset=UTF-8',
		datType : "JSON",
		data : JSON.stringify(datas),
		async : false,
		success : function(res) {
			window.location.href = "/purchaseplan/demandplansheet?informationid=" + res.data + "&purchasetype=" + purchaseType;
		},
		error : function(data) {
			console.log(data);
		}
	});
}

function removeDemand(ids) {
	var txt = '<h3 class="alert-title" style="text-align: center;font-size:18px;margin-top:75px">确认删除？</h3>';
	confirmDialog("确认删除", txt, function() {
		var datas = {
			id : ids
		};
		$.ajax({
			url : '/purchaseplanrest/removedemand',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			datType : "JSON",
			data : JSON.stringify(datas),
			async : false,
			success : function(res) {
				if (res.status == 2) {
					AlertSucMessage(res.desc, function() {
						var form = $("#demand-plan-form").toJSON();
						searchDemand(form);
					});
				}else{
					AlertWarnMessage('删除异常！！');
				}
			},
			error : function(data) {
				console.log(data);
			}
		});
	});
}

function viewDemand(ids) {
	window.location.href = "/purchaseplan/viewdemand?informationid" + ids;
}

function submitDemand(ids) {
	var txt = '<h3 class="alert-title" style="text-align: center;font-size:18px;margin-top:75px">确认提交？</h3>';
	confirmDialog("确认提交", txt, function() {
		var datas = {
			id : ids,
			node_status : 5
		};
		$.ajax({
			url : '/purchaseplanrest/submitdemand',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			datType : "JSON",
			data : JSON.stringify(datas),
			async : false,
			success : function(res) {
				console.log(JSON.stringify(res));
				if (res.status == 2) {
					AlertSucMessage(res.desc, function() {
						var form = $("#demand-plan-form").toJSON();
						searchDemand(form);
					});
				}else{
					AlertWarnMessage('删除异常！！');
				}
			},
			error : function(data) {
				console.log(data);
			}
		});
	});
}

function gobackDemand(ids) {
	var txt = '<h3 class="alert-title" style="text-align: center;font-size:18px;margin-top:75px">确认撤回？</h3>';
	confirmDialog("确认撤回", txt, function() {
		var datas = {
			id : ids,
			node_status : 4
		};
		$.ajax({
			url : '/purchaseplanrest/submitdemand',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			datType : "JSON",
			data : JSON.stringify(datas),
			async : false,
			success : function(res) {
				if (res.status == 2) {
					AlertSucMessage(res.desc, function() {
						var form = $("#demand-plan-form").toJSON();
						searchDemand(form);
					});
				}else{
					AlertWarnMessage('删除异常！！');
				}
			},
			error : function(data) {
				console.log(data);
			}
		});
	});
}
