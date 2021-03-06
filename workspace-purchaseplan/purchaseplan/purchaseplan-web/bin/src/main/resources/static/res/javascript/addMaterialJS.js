$(function() {
	$(".back-btn").click(function() {
		parent.searchPlan();
		top.CloseDialog("add-material");
	});
});

layui.use([ 'form', 'layedit', 'laydate' ], function() {
	var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

	// 一级菜单选择
	form.on('select(first-type)', function(data) {
		var secondType = $("#second-type");
		secondType.empty();
		secondType.append('<option value="0">二级分类</option>');

		var thirdType = $("#third-type");
		thirdType.empty();
		thirdType.append('<option value="0">三级分类</option>');

		// 加载二级类型列表
		var firstRankId = data.value;
		var datas = {
			key : firstRankId
		};
		$.ajax({
			url : '/materialstore/getgoodstype',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			datType : "JSON",
			data : JSON.stringify(datas),
			async : false,
			success : function(data) {
				var secondType = $("#second-type");
				secondType.empty();
				secondType.append('<option value="">二级分类</option>');
				data.forEach(function(val, index) {
					secondType.append('<option value="' + val.id + '" code="' + val.code + '">' + val.name + '</option>');
				});
			},
			error : function(data) {
				console.log(data);
			}
		});

		$('#second-type').attr("disabled", false);
		form.render('select');
	});

	// 二级级菜单选择
	form.on('select(second-type)', function(data) {
		var thirdType = $("#third-type");
		thirdType.empty();
		thirdType.append('<option value="0">三级分类</option>');
		// 加载三级类型列表
		var secondRankId = data.value;
		var datas = {
			key : secondRankId
		};

		$.ajax({
			url : '/materialstore/getgoodstype',
			type : 'POST',
			contentType : 'application/json; charset=UTF-8',
			datType : "JSON",
			data : JSON.stringify(datas),
			async : false,
			success : function(data) {
				var secondType = $("#third-type");
				secondType.empty();
				secondType.append('<option value="">三级分类</option>');
				data.forEach(function(val, index) {
					secondType.append('<option value="' + val.id + '" code="' + val.code + '">' + val.name + '</option>');
				});
			},
			error : function(data) {
				console.log(data);
			}
		});

		$('#third-type').attr("disabled", false);
		form.render('select');

	});
});

/**
 * 搜索
 */
function search() {
	var nameorcode = $("#nameorcode").val();
	var firstType = $("#first-type").val();
	var secondType = $("#second-type").val();
	var thirdType = $("#third-type").val();
	var informationid = $("#informationid").val();
	var url = "/purchaseplan/getpagelistvotablesadd?nameorcode=" + nameorcode + "&firsttype=" + firstType + "&secondtype=" + secondType + "&thirdtype="
			+ thirdType + "&informationid=" + informationid;
	$('#pageListVOtables').load(url,function (){
		editNumber();
	});
	
}

function modityPurchasePlan(id, goodId, isLocal,firstRankIds) {
	if (isLocal == 2) {
		addPurchasePlan(id, goodId,firstRankIds);
		return;
	}
	if (isLocal == 1) {
		removePurchasePlan(id, goodId,firstRankIds);
		return;
	}
}

function addPurchasePlan(ids, goodId,firstRankIds) {

	if (goodId == null) {
		AlertWarnMessage('物资信息获取异常！！');
		return;
	}
	var quantity = $("#" + goodId).val();
	var informationid = $("#informationid").val();
	if (quantity == null || quantity == '' || quantity == 0) {
		AlertWarnMessage('请填写需求数量！！');
		return;
	}
	console.log("firstRankIds----->"+firstRankIds);
	var datas = {
		id : ids,
		goods_id : goodId,
		plan_quantity : quantity,
		information_id : informationid,
		firstRankId : firstRankIds
	};
	$.ajax({
		url : '/purchaseplanrest/savepurchaseplanlist',
		type : 'POST',
		contentType : 'application/json; charset=UTF-8',
		datType : "JSON",
		data : JSON.stringify(datas),
		async : false,
		success : function(rst) {
			if (rst.status == 2) {
				search();
			} else {
				AlertWarnMessage('加入失败！！');
			}
		},
		error : function(data) {
			console.log(data);
		}
	});
}

function removePurchasePlan(ids, goodId) {
	var datas = {
		id : ids,
		goods_id : goodId
	};
	console.log(JSON.stringify(datas));
	$.ajax({
		url : '/purchaseplanrest/removepurchaseplanlist',
		type : 'POST',
		contentType : 'application/json; charset=UTF-8',
		datType : "JSON",
		data : JSON.stringify(datas),
		async : false,
		success : function(data) {
			search();
		},
		error : function(data) {
			console.log(data);
		}
	});
}