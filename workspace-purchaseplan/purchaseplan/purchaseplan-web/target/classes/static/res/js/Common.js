/**
 * 常用方法模块
 * 需要在页面自动加载的请在init里面调用
 * 其他方法可在其他页面用Common.xxx()调用
 */

var Common = {
    init: function () {
        // 自动加载
        this.leftMenu();
        this.editInput(); //   /* 表格文字与输入框切换 */
        this.maxWords();/* textarea最大字数 */
        this.inputMaxWords(); /* input最大字数 */
        this.switchBtn();
        
    },

    tab: function () {
        $(".yui-tab-title").on('click', 'li', function () {
            $(this).addClass('yui-this').siblings('li').removeClass('yui-this');
            var dom = $(this).parent().find('li');
            var index;
            for (var i = 0; i < dom.length; i++) {
                if (dom[i].className) {
                    index = i;
                }
            }
            var item = $(this).parents('.yui-tab').find('.yui-tab-item');
            if (item.length > 0) {
                item.eq(index).addClass('yui-show').siblings('div.yui-tab-item').removeClass('yui-show');
            } else {
                $(this).parents('.yui-tab').find('.yui-tab-content').show();
            }
        });
    },

    page: function () {
        // 分页
        $(".page").createPage({
            pageIndex: 22,
            totalPage: 200,
            count: 220,
            type: 1,
            countSpan: false,
            backFn: function (pageIndex) {
                //console.log(pageIndex);
            }
        });
    },

    tipsClosed: function () {
        // 关闭提示框
        $('.yui-alert .close').on('click', function (e) {
            e.preventDefault();
            /* Act on the event */
            $(this).parent().hide();
        });
    },

    editRequirement: function () {
        /* 编辑货物-添加其它要求 */
    },

    leftMenu: function () {
        // 左侧菜单
        $('.firstMenu').on('click', function (e) {
            e.preventDefault();
            $(this).find('.menuExpand').toggleClass('menuExpanded');
            $(this).next().toggleClass('menuSecondShow');
        });
    },

    
    selectOrDie: function () {
        /* select下拉菜单 */
        $('select').selectOrDie();
    },

    /**
     * layer自带btn按钮方法
     * 弹窗iframe fucntion
     * url，内容模板地：默认为：'/layer_content/dialog-default.html'
     * title, 弹框左上角标题
     * width，弹窗宽度，默认为：'540px'，
     * height,弹窗高度，默认为：'300px'，配合isScroll = yes 可以创建滚动条弹窗
     * isScroll ,弹窗是否有滚动条 只有 'yes'和'no' 2个值，默认为'no'
     *
     */
    layerDialog: function (url, title, width, height, isScroll, skin) {
        if (url === undefined) {
            url = '/layer_content/dialog-default.html'
        }
        ;
        if (title === undefined) {
            title = '信息'
        }
        ;
        if (width === undefined) {
            width = '540px'
        }
        ;
        if (skin === undefined) {
            skin = null
        }
        ;
        if (isScroll === undefined) {
            // 无滚动条，自适应iframe高度
            isScroll = 'no';
            height = '';
        } else if (isScroll == 'yes') {
            isScroll = 'yes';
            if (height === undefined) {
                height = '300px'
            }
            ;
        }
        ;
        // if (hasBtn == 1){
        //     btnContent =  btnContent || ['取消','确定']
        // }else {
        //     btnContent = null;
        // }
        layer.open({
            type: 2,
            title: title,
            skin: skin,
            shadeClose: false,
            content: [url, isScroll],
            area: [width, height],
            // btn: btnContent,
            resize: false,
            scrollbar: false,
            offset: '100px',
            success: function (layero, index) {
                if (isScroll == 'no') {
                    layer.iframeAuto(index);
                }
            },
            end: function (layero, index) {
                layer.close(index);
            }
        });
    },

    /* 表格文字与输入框切换 */
    editInput: function () {
        $(".writeNumber").on('click', function () {
            $(this).hide();
            $(this).next('div').show().find('input').focus();
        });

        $(".editeTd .yui-input").on('blur', function () {
            var text = $.trim($(this).val()),
                parentTd = $(this).parents('.editeTd'),
                max = parseInt(parentTd.siblings('.number').text());
            var reg = /\d+/;
            if (reg.test(text)) {
                if (text > max) {
                    parentTd.find('.yui-input-help').show();
                    return;
                }
                $(this).parents('.editeTd').find(".writeNumber").text(text).addClass('black').show();
                $(this).parents('.yui-input-item').hide();
                parentTd.find('.yui-input-help').hide();
            } else {
                parentTd.find('.yui-input-help').show();
            }


        });
    },

    /* textarea最大字数 */
    maxWords: function () {
        function maxWord(ev, maxWords) {
            var val = ev.val(),
                numberText = ev.next('.remainder').find(".remainder-number");
            if (val.length >= maxWords) {
                numberText.text(maxWords);
                ev.val(ev.val().substr(0, maxWords));
            } else {
                numberText.text(val.length);
            }
        }

        $(".textarea-control").on('input propertychange', function () {
            var maxWords = parseInt($(this).parents('.form-block').find('.maxNumber').text());
            maxWords ? maxWord($(this), maxWords) : ''
        });
    },
    /* input最大字数 */
    inputMaxWords: function () {
        function maxWord(ev, maxWords) {
            var val = ev.val(),
                numberText = ev.next('.inputCounter').find(".remainder-number");
            if (val.length >= maxWords) {
                numberText.text(maxWords);
                ev.val(ev.val().substr(0, maxWords));
            } else {
                numberText.text(val.length);
            }
        }

        $(".insideInputWrapper-input").on('input propertychange', function () {
            var maxWords = parseInt($(this).parent().find('.maxNumber').text());
            maxWords ? maxWord($(this), maxWords) : ''
        });
    },
    /* 开关按钮 */
    switchBtn: function () {
        //开关控制富文本编辑框和表单显示和隐藏

        function checkeds(ev, class1, class2) {
            if (ev.type == 'ifChecked') {
                $('.' + class2).show();
                $('.' + class1).hide();
            } else {
                $('.' + class2).hide();
                $('.' + class1).show();
            }
        }

        function radioFn(radioBoxName, class1, class2) {
            if (radioBoxName == 'describe1') {
                $('.' + class1).show();
                $('.' + class2).hide();
            } else {
                $('.' + class1).hide();
                $('.' + class2).show();
            }
        }
        
// 10 确定供应商
        $("input.contrastBox").on('ifChecked ifUnchecked', function (event) {
            checkeds(event, 'vsTableAll', 'vsTableUnit');
        });
        $("input.resultBox").on('ifChecked ifUnchecked', function (event) {
            checkeds(event, 'result-open', 'result-open-book');
        });
        /*19 项目变更*/
        $(".radioBox").on('ifChecked', function () {
            var radioBoxName = $(this).attr('data-label');
            radioFn(radioBoxName, 'describe1', 'describe2');
        });
    },

   
    /*  成员标签删除 */
    key: function (dom) {
        $("." + dom).on('keydown', function (event) {
            var e = event || window.event
            var k = e.keyCode || e.which;
            if (k == 8 && $(this).val() == '') {
                $(this).prev('span').remove()
            }
        })
    },

    /*修改公司名称按钮*/
    modify: function (ev) {
        var text = ev.prev('span.input-value').text();
        var dom = '<div class="yui-input-wrapper yui-fl"><input class="yui-input w360 company-name-input" type="text" placeholder="'+text+'"></div>';
        var isHidden = ev.prev('span.input-value').is(":hidden");
        var isHasInput = ev.prev('.yui-input-wrapper').length;
        if(!isHidden && !isHasInput) {
            ev.prev('span.input-value').hide();
            ev.before(dom);
        }
    },

    /*修改常用地址，支付方式，要求*/
    amendCommon: function (ev) {
        var commonValue = ev.parent().prev('td').find('span.commonValue');
        var text = commonValue.text().trim();
        var len = text.length;
        var val = commonValue.next('div').find('.yui-input').val().trim();
        if(commonValue.is(":hidden") && val !== '') {
            commonValue.text(val).show();
            commonValue.next('div').removeClass('yui-input-fail').hide().find('.yui-input-help').hide();
            return
        }
        if(!commonValue.is(":hidden")) {
            commonValue.hide();
            commonValue.next('div').show().find('.yui-input').val(text);
            commonValue.next('div').find('.remainder-number').text(len);
            return
        }
        if(commonValue.is(":hidden") && val == '') {
            commonValue.next('div').addClass('yui-input-fail').find('.yui-input-help').show();
            return
        }

    }
};
Common.init();