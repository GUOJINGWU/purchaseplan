/**
 * Created by guojingwu on 2018/9/26.
 */
/*
 * 解决不支持input的placeholder问题
 * 使用说明：
 * 1、直接加载，只适用于固定个数的含有placeholder属性的标签
 * 2、动态添加含有placeholder属性的标签时，必须手动出发失去焦点事件
 * 3、在表单提交前，先执行clearPlaceholder函数，以防把placeholder值提交上去
 * author 孟俊杰
 * since 20160311
 */
$(function () {
    if (!placeholderSupport()) {   // 判断浏览器是否支持 placeholder
        $('[placeholder]').live("focus", function () {
            var input = $(this);
            input.css("color", $("body").css("color"));
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).live("blur", function () {
            var input = $(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
                input.css("color", "#747474");
            }
        }).blur();
    };
})

//判断浏览器是否支持placeholder
function placeholderSupport() {
    return 'placeholder' in document.createElement('input');
}

//表单提交之前，调用此方法，清除错误提交模拟placeholder时的内容
function clearPlaceholder() {
    if (!placeholderSupport()) {
        $("[placeholder]").each(function(i,e){
            if ($(e).val() == $(e).attr("placeholder")) {
                $(e).val("");
            }
        });
    }
}

