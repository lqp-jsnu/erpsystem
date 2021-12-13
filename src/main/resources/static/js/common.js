/**
 * 渲染表格要用到的常用代码封装
 */

// 初始化表格
function initTable(node, url, pageList, queryParams, columns, toolbar = '#toolbar') {
    node.bootstrapTable("destroy");         // 先销毁用来的表格再构造新的表格
    node.bootstrapTable({
        toolbar: toolbar,                   // 工具按钮用哪个容器
        method: 'get',                      // 请求方式
        url: url,                           // 请求路径
        dataType: "json",                   // 服务器返回的数据类型
        contentType: "application/json",    // 发送到服务器的数据编码类型
        striped: true,                     // 是否显示行间隔色
        cache: false,                      // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                  // 是否显示分页（*）
        sortable: false,                   // 是否启用排序
        sortOrder: "asc",                   // 排序方式
        sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
        queryParamsType: "limit",           // 设置为 ‘limit’ 则会发送符合 RESTFul 格式的参数
        pageNumber: 1,                      // 初始化加载第一页，默认第一页
        pageSize: 10,                       // 每页的记录行数（*）
        pageList: pageList,                 // 可供选择的每页的行数（*）
        showColumns: true,                 // 是否显示所有的列
        showRefresh: true,                 // 是否显示刷新按钮
        minimumCountColumns: 2,             // 最少允许的列数
        clickToSelect: true,               // 是否启用点击选中行
        showToggle:true,                   // 是否显示详细视图和列表视图的切换按钮
        cardView: false,                   // 是否显示详细视图
        detailView: false,                 // 是否显示父子表
        uniqueId: "id",                     // 每一行的唯一标识，一般为主键列
        dataField: "rows",                  // 这是返回的json数组的key.默认好像是"rows".这里只要前后端约定好就行
        queryParams: queryParams,
        responseHandler: result => {
            if (result.success === false) {
                switch (result.code) {
                    case 9001: swal("错误", "数据库错误", "error");   break;
                    case 9002: swal("错误", "参数错误", "error");     break;
                    case 9999: swal("错误", "系统错误", "error");     break;
                }
                return null;
            } else {
                return { total: result.module.total, rows: result.module.rows, };
            }
        },
        columns: columns,
        rowStyle: function (row, index) {
            let classesArr = ['info', '#ffffff'];
            let strClass = "";
            if (index % 2 === 0) {  // 偶数行
                strClass = classesArr[0];
            } else {    // 奇数行
                strClass = classesArr[1];
            }
            return { classes: strClass };
        }// 隔行变色
    });
}

// 初始化下拉框
function initSelect(node, placeholder, templateResult, url, delay, data, showData, pageSize) {
    node.select2({
        language: "zh-CN",  			// 设置 提示语言
        width: "100%", 					// 设置下拉框的宽度
        placeholder: placeholder,       // 指定控件的占位符
        minimumInputLength: 1,          // 最小需要输入多少个字符才进行查询
        escapeMarkup: markup => markup,	// 处理自定义模板呈现的内容的自动转义
        templateResult: templateResult, // 自定义搜索结果的呈现方式
        ajax: {
            url: url,
            dataType: 'json',
            delay: delay,                // 让Select2等待1s，直到用户完成搜索字词的输入，然后才触发AJAX请求。
            data: data,
            processResults: function (result, params) {
                if (result.success === false) {
                    switch (result.code) {
                        case 9001: swal("错误", "数据库错误", "error");   break;
                        case 9002: swal("错误", "参数错误", "error");     break;
                        case 9999: swal("错误", "系统错误", "error");     break;
                    }
                    return null;
                } else {
                    return {
                        results: showData(result),
                        pagination: {
                            more: ((params.page || 1) * pageSize) < result.module.total   // 是否显示分页
                        }
                    };
                }
            },
            cache: false
        },
    });
}

// 弹出框
function dialog(message, callback) {
    swal(message, {
        buttons: {
            true: "确定",
            cancel: "取消"
        },
    }).then((value) => {
        switch (value) {
            case "true":
                callback();
                break;
            default:
                break;
        }
    });
}

// 无参数请求
function noParameterPostRequest(url, callback, method = "POST") {
    $.ajax({
        type: method,
        url: url,
        dataType: "json",
        contentType : "application/json",
        success: function (result) {
            if (result.success === false) {
                switch (result.code) {
                    case 9001: swal("错误", "数据库错误", "error");   break;
                    case 9002: swal("错误", "参数错误", "error");     break;
                    case 9004: callback(result);                    break;
                    case 9005: swal("错误", "文件删除错误", "error"); break;
                    case 9006: swal("错误", "文件创建错误", "error"); break;
                    case 9007: swal("错误", "文件不存在", "error");   break;
                    case 9008: callback(result);                    break;    // 环境变量错误提示
                    case 9009: callback(result);                    break;    // 唯一约束条件错误提示
                    case 9999: swal("错误", "系统错误", "error");     break;
                }
            } else {
                callback(result);
            }
        },
        error: function () {
            swal("错误", "404", "error");
        }
    });
}

// 有参数请求
function parameterPostRequest(url, data, callback, method = "POST") {
    $.ajax({
        type: method,
        url: url,
        dataType: "json",
        data: JSON.stringify(data),
        traditional: true,
        contentType : "application/json",
        success: function (result) {
            if (result.success === false) {
                switch (result.code) {
                    case 9001: swal("错误", "数据库错误", "error");   break;
                    case 9002: swal("错误", "参数错误", "error");     break;
                    case 9004: callback(result);                    break;
                    case 9005: swal("错误", "文件删除错误", "error"); break;
                    case 9006: swal("错误", "文件创建错误", "error"); break;
                    case 9007: swal("错误", "文件不存在", "error");   break;
                    case 9009: callback(result);                    break;    // 唯一约束条件错误提示
                    case 9999: swal("错误", "系统错误", "error");     break;
                }
            } else {
                callback(result);
            }
        },
        error: function () {
            swal("错误", "404", "error");
        }
    });
}

// 修改提示框样式
function changeToolTip() {
    $(function() {
        $( document ).tooltip({
            position: {
                my: "center bottom-20",
                at: "center top",
                using: function( position, feedback ) {
                    $( this ).css( position );
                    $( "<div>" )
                        .addClass( "arrow" )
                        .addClass( feedback.vertical )
                        .addClass( feedback.horizontal )
                        .appendTo( this );
                }
            },
            show: {
                effect: "slideDown",
                delay: 250
            },
            hide: {
                effect: "explode",
                delay: 250
            }
        });

        $("select").on("select2:close", () => $("[role=tooltip]").remove());
    });
}

// 设置input success样式
function setInputSuccess(el) {
    el.parent().parent().removeClass("has-error");
    el.parent().parent().addClass("has-success");
}

// 设置input error样式
function setInputError(el) {
    el.parent().parent().removeClass("has-success");
    el.parent().parent().addClass("has-error");
}

// UTC时间格式转换
function addZero(num) {
    return num < 10 ? '0' + num : num;
}
function formatDateTime(date) {
    let time = new Date(Date.parse(date));
    let Y = time.getFullYear() + '-';
    let M = this.addZero(time.getMonth() + 1) + '-';
    let D = this.addZero(time.getDate()) + ' ';
    let h = this.addZero(time.getHours()) + ':';
    let m = this.addZero(time.getMinutes()) + ':';
    let s = this.addZero(time.getSeconds());
    return Y + M + D + h + m + s;
}

// date表单处理
function dateInputFormat(date) {
    let time = new Date(Date.parse(date));
    let Y = time.getFullYear() + '-';
    let M = this.addZero(time.getMonth() + 1) + '-';
    let D = this.addZero(time.getDate());
    return Y + M + D;
}

// 去除对象内的空键值对
function removeEmptyField (data) {
    for (const key in data) {
        if ('' === data[key]) {
            delete data[key];
        }
    }
}

// 普通数据处理
function stringFormatter(value) {
    return null === value ? "" : '<span title="'+ value +'">' + value +'</span>';
}

// 时间数据处理
function dateFormatter(value) {
    return null === value ? "" : '<span title="'+ formatDateTime(value) +'">' + formatDateTime(value) +'</span>';
}

// 检测input的输入输出
function checkInputChange(id, content = "") {
    let $input = $("#" + id);
    content === $input.val() ? setInputError($input) : setInputSuccess($input);
}
function numberInputChange(id, message, content = "", min = 0) {
    let $input = $("#" + id);
    if ($input.val() < min) {
        swal("操作提示", message, "info");
        setInputError($input);
        return;
    }
    content === $input.val() ? setInputError($input) : setInputSuccess($input);
}

// 下载文件
function loadFile(filename) {
    // js模拟表单下载
    let form = $("<form></form>").attr("action", "/file/download").attr("method", "post");
    form.append($("<input />").attr("type", "hidden").attr("name", "filename").attr("value", filename));
    form.appendTo('body').submit().remove();
}