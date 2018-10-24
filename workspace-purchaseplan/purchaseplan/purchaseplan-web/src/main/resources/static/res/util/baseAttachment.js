/**
 * 文件下载
 */
function  Download(url) {
    if (url) {
        $("#iframe_download").remove();
        $("#downloadFile").remove();
        $("<iframe width='0' height='0' id='iframe_download' style='display:none;'></iframe>").prependTo('body');
        $("<form id='downloadFile' method='post'></form>").prependTo('body');

        var f = $("#downloadFile")[0];
        if (f) {
            f.action = url;
            f.method = "POST";
            f.target = "iframe_download";
            f.submit();
        }
    }
}

	/**
	 * 上传文件
	 * 
	 * @param params
	 * @returns {Boolean}
	 */
	function uploadFile(params) {
		var attRelaId = params.attRelaId; // 【必填】附件关联id
		var divId = params.divId; // 【必填】加载附件列表的div控件
		var savePath = params.savePath; // 相对路径，格式如：“项目id/自定义路径”
		var suffix = params.suffix; // 允许上传的附件格式(默认为 * 全部格式)
									// ：格式如：.doc,.docx,.xlt
		var callBack = params.callBack; // 回调函数
		var isSingle = params.isSingle; // 是否单个(默认为 0 ：多个)
		var isView = params.isView; // 是否查看页面(默认为1：查看页面)
		var width = params.width; // 弹窗宽度(有默认)
		var height = params.height; // 弹窗高度(有默认)

		// 附件关联id，必须有
		if (!attRelaId) {
			return false;
		}

		if (!suffix) {
			suffix = "*";
		}

		if (!savePath) {
			savePath = "";
		}

		if (isSingle != '1') {
			isSingle = 0;
		}

		if (isView != "1") {
			isView = 0;
		}

		if (!width) {
			width = 650;
		}

		if (!height) {
			height = 280;
		}

		var title = "<div class='title'>上传附件</div>";
		var url = "../baseattachment/fileupload?savePath=" + savePath + "&attRelaId=" + attRelaId + "&isSingle=" + isSingle + "&suffix=" + suffix;
		console.log(url);
		var D = dialog({
			title : title,
			url : url,
			width : width,
			height : height,
			okValue : "完成",
			ok : function() {
				// 加载附件列表
				loadAttList(attRelaId, divId, isView);

				// 回调
				if (callBack) {
					callBack();
				}
				return true;
			}
		});
		D.showModal();
	}

	/**
	 * 加载文件列表
	 */
	function loadAttList(attRelaId, divId, isView) {
		if(!divId){
			return false;
		}
		
		if (!attRelaId) {
			$("#" + divId).html("无");
			return false;
		}

		// ajax获取附件列表
		$.post(ctxforjs + "/baseAttachment/getFileListByRelaId.do", {
			attRelaId : attRelaId
		}, function(data) {
			if (!!data && data.status == 'success') {
				var result = data.dataList;
				if (!!result && result != 'none') {
					$("#" + divId).html(generateHtml(result, isView));
				}
			} else {
				$("#" + divId).html("加载失败：" + !!data.desc ? data.desc : "未知");
			}
		});
	}

	/**
	 * 加载文件列表
	 * @param result
	 * @param isView
	 */
	function generateHtml(result, isView) {
		if (!result || result == 'none' || result.length<=0) {
			
			if(isView == 1){ //查看页面显示“无”
				return "无";
			}
			
			return "";
		}

		var html = "<ul class=\"detachmentList\">";

		//查看页面
		if(isView == 1){
//			html = "<ul class=\"detachmentListView\">";
		}
		
		for (var i = 0; i < result.length; i++) {
			html += "<li>";
			html += "<a href=\"javascript:;\" style=\"cursor: pointer;\"  title=\"点击下载\" onclick=\"Download('" + result[i].downLoadUrl + "')\" >";
			html += "<span>" + result[i].fileName + "</span>";
			html += "</a>";

			// 是否查看页面
			if (isView == 0) {
				html += "<a  href=\"javascript:;\"  style=\"cursor: pointer;\" onclick=\"deleteAttFile('" + result[i].id + "',this);\">";
				html += "<img src=\"" + ctxforjs + "/resources/images/baseAttachment/deleteIconBlue.png\" alt=\"点击删除\" />";
				html += " <span>删除</span>";
				html += "</a>";
			}
			if(result[i].isPreview == 1){
		         html += "<a  href=\""+result[i].previewAttPath+"\" target=\"view_window\" style=\"cursor: pointer;\">";
		            html += "<img src=\"" + ctxforjs + "/resources/images/baseAttachment/previewIconBlue.png\" alt=\"点击预览\" />";
		            html += " <span>预览</span>";
		            html += "</a>";
			}
			html += "</li>";
		}
		html += "</ul>";
		return html;
	}

	// 删除附件
	function deleteAttFile(id, obj) {

		confirmDialog('确认', '是否确认想要删除？', function() {
			$.ajax({
				type : "Post",
				url : "../baseAttachment/deleteFileById.do",
				data : {
					id : id
				},
				success : function(data) {
					if (!!data && data.status == 'success') {
						$(obj).parent().remove();
					} else {
						AlertWarnMessage("删除失败：" + !!data.desc ? data.desc : "未知", function() {
						});
						return false;
					}
				}
			});
		});
	}
	
	//预览文件格式
	function previewAttFile(id) {
		AlertLoading();
		$.ajax({
			type : "Post",
			url : "../baseAttachment/generatePDF.do",
			data : {
				id : id
			},
			success : function(data) {
				closeLoading();
				if (!!data && data.status == 'success') {
					window.open("../baseAttachment/previewPDF.do?id="+id)
				} else {
					AlertWarnMessage(data.desc);
				}
			}
		});
	}
