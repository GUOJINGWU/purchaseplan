$(function() {
	layui.use([ 'form' ], function() {
		var form = layui.form();
		form.on('radio(plan-type)', function(data) {
			if (data.value == 2) {
				console.log(data.value);
				$('.emergency-item').show();
				form.render();
			} else if (data.value == 1) {
				console.log(data.value);
				$('.emergency-item').hide();
				form.render();
			}
		});
	});

	$("#uploadFiles").change(function() {
		console.log($(this).val());
		fileLoad(this, 'uploadFilesView', $("#attachmentId").val());
	});
	
	typeEmergency();
});

// 添加人员弹窗
function addMember() {
	var informationId = $("#informationId").val();
	var purchasetype = $("#purchaseType").val();
	var url = "/purchaseplan/addmember?informationid=" + informationId + "&purchasetype=" + purchasetype;
	console.log(url);
	OpenSimpleUrl("add-people", "添加人员", url, '640px', '416px');

}

// 添加物资弹窗
function addMaterial() {
	var informationId = $("#informationId").val();
	var purchasetype = $("#purchaseType").val();
	var url = "/purchaseplan/addmaterial?informationid=" + informationId + "&purchasetype=" + purchasetype;
	console.log(url);
	OpenSimpleUrl("add-material", "添加物资", url, '940px', '600px');
}
// 批量导入弹窗
function batchImport() {
	var informationId = $("#informationId").val();
	var purchasetype = $("#purchaseType").val();
	OpenSimpleUrl("batchImportSheet", "批量导入", "/purchaseplan/batchimportsheet?informationid=" + informationId + "&purchasetype=" + purchasetype, '540px', '416px');
}
// 查看详情
function watchDetail() {
	var currentProjectURL = $("#currentProjectURL").val();
	OpenSimpleUrl("watch-detail", "查看详情", currentProjectURL + "/pageJump2", '640px', '500px');
}
// 选择供应商
function supplierSelection() {
	var txt = '<div style="padding: 10px;">' + '<div style="width:465px;height:32px;margin:20px auto">' + '<label style="margin-right:20px">供应商名称</label>'
			+ '<input type="text" class="search-input" style="width:270px;height:32px;margin:0 auto;" />' + '<a class="plan-radius-btn" style="margin-left:20px">搜索</a>' + '</div>'
			+ '<div style="height:262px;overflow-y:auto;">' + '<table class="layui-table">' + '<colgroup>' + '<col width="15%" />' + '<col width="35%" />' + '<col width="20%" />' +

			'</colgroup>' + '<thead>' + '<tr>' + '<th class="yzc-aCenter">序号</th>' + '<th class="yzc-aCenter">供应商名称</th>' + '<th class="yzc-aCenter">操作</th>' +

			'</tr>' + '</thead>' + '<tbody>' + '<tr>' + '<td class="yzc-aCenter">1</td>' + '<td class="yzc-aCenter supplier-name">供应商1</td>'
			+ '<td class="yzc-aCenter plan-a select-supplier" id="supplier1" onclick="toChoose1()">选择</td>' +

			'</tr>' + '<tr>' + '<td class="yzc-aCenter">1</td>' + '<td class="yzc-aCenter supplier-name">供应商2</td>'
			+ '<td class="yzc-aCenter plan-a select-supplier" id="supplier2"  onclick="toChoose2()">选择</td>' +

			'</tr>' + '</tbody>' + '</table>' + '</div>' + '</div>';
	confirmDialog("选择供应商", txt, function() {

	});
}

// 选择供应商
function toChoose1() {
	var supplierName = $("#supplier1").siblings(".supplier-name").html();
	$(".search-input").val(supplierName);
}

function toChoose2() {
	var supplierName = $("#supplier2").siblings(".supplier-name").html();
	$(".search-input").val(supplierName);
}

function searchPlan() {
	var informationId = $("#informationId").val();
	var url = "/purchaseplan/demandplansheettable?informationid=" + informationId;
	console.log(url);
	$('#demandPlanSheetTable').load(url, function() {
		editNumber();
	});
}

function removePlan(goodId) {
	var txt = '<h3 class="alert-title" style="text-align: center;font-size:18px;margin-top:75px">确定取消？</h3>';
	confirmDialog("确认取消", txt, function() {
		var datas = {
			id : goodId
		};
		$(".loading-shade").show();
		$.ajax({
			url : '/purchaseplanrest/removepurchaseplanlist',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			datType : "JSON",
			data : JSON.stringify(datas),
			async : false,
			success : function(data) {
				searchPlan();
				$(".loading-shade").hide();
			},
			error : function(data) {
				console.log(data);
			}
		});
	});
}

function modifyCompanyName(recipientId, recipientName, companyId, companyName) {
	$("#recipient_id").val(recipientId);
	$("#recipient_name").val(recipientName);
	$("#company_id").val(companyId);
	$("#company_name").val(companyName);
}

function savePlan(node_status) {
	var data = getHtmlValue(node_status);
	console.log(data);
	$(".loading-shade").show();
	$.ajax({
		url : '/purchaseplanrest/saveplans',
		type : 'POST',
		contentType : 'application/json; charset=UTF-8',
		datType : "JSON",
		data : JSON.stringify(data),
		async : false,
		success : function(rst) {
			$(".loading-shade").hide();
			if (rst.status == 2) {
			} else {
				AlertWarnMessage(rst.desc);
			}
		},
		error : function(data) {
			console.log(data);
		}
	});
}

function submitPlan(node_status) {
	var txt = '<h3 class="alert-title" style="text-align: center;font-size:18px;margin-top:75px">确定提交物资信息？</h3>';
	confirmDialog("确认提交", txt, function() {
		var data = getHtmlValue(node_status);
		console.log(data);
		$(".loading-shade").show();
		$.ajax({
			url : '/purchaseplanrest/submitplan',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			datType : "JSON",
			data : JSON.stringify(data),
			async : false,
			success : function(rst) {
				$(".loading-shade").hide();
				if (rst.status == 2) {
					window.location.href = "/purchaseplan/index";
				} else {
					AlertWarnMessage(rst.desc);
				}
			},
			error : function(data) {
				console.log(data);
			}
		});
	});
}

function getHtmlValue(node_status) {
	var data = {};
	data.id = $("#informationId").val();// 主键
	data.demand_number = $("#demand_number").val();// 需求编号
	data.plan_type_status = $('input[name="plan_type_status"]:checked').val();// 计划类型状态
	data.emergency_cause = $("#emergency_cause").val();
	data.instruction_manual = $("#instruction_manual").val();// 备注说明
	data.attachment_id = $("#attachmentId").val();// 需求附件
	data.purchase_type = $("#purchaseType").val();// 计划类型
	data.node_status = node_status;
	var tableOut = [];

	$('#demandPlanSheetTable tr').each(function(i) {
		if (i > 0) {
			var table = {};
			table.plan_coding = $(this).find('td:eq(1)').text();
			table.plan_quantity = $(this).find('td:eq(8) input').val();
			tableOut.push(table);
		}
	});
	data.planList = tableOut;

	var recipientList = [];
	var recipient = {};
	recipient.information_id = $("#informationId").val();
	recipient.recipient_id = $("#recipient_id").val();
	recipient.recipient_name = $("#recipient_name").val();
	recipient.company_id = $("#company_id").val();
	recipient.company_name = $("#company_name").val();
	recipient.recipient_status = node_status;
	recipient.is_create = 0;
	recipientList.push(recipient);
	data.purchaseRecipientList = recipientList;
	return data;
}

function typeEmergency() {
	var type = $('input[name="plan_type_status"]:checked').val();
	if (type == 2) {
		$('.emergency-item').show();
	} else if (type == 1) {
		$('.emergency-item').hide();
	}
}