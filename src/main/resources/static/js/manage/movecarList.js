/**
 * 用户管理
 */
var pageCurr;
var form;
$(function() {
    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;

        tableIns=table.render({
            elem: '#movecarList',
            url:'/manage/codeList',
            data: $("#codeSearch").serialize(),
            method: 'post', //默认：get请求
            cellMinWidth: 80,
            page: true,
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response:{
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'list' //数据列表的字段名称，默认：data
            },
            cols: [[
                {type:'numbers'}
                ,{field:'code', title:'挪车码编号',align:'center'}
                ,{field:'phone', title:'手机号',align:'center'}
                ,{field:'carNo', title:'车牌号',align:'center'}
                ,{field:'status', title: '挪车码状态',align:'center'}
                ,{field:'createTime', title: '申请时间',align:'center'}
                ,{field:'status',title:'操作',align:'center', toolbar:'#optBar'}
            ]],
            done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                console.log(curr);
                $("[data-field='status']").children().each(function(){
                    console.log($(this));
                    if($(this).text()=='1'){
                        $(this).text("使用中")
                    }else if($(this).text()=='0'){
                        $(this).text("未激活")
                    }else if($(this).text()=='2'){
                        $(this).text("已停用")
                    }
                });
                //得到数据总量
                //console.log(count);
                pageCurr=curr;
            }
        });

        //监听工具条
        table.on('tool(movecarTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'invalid'){
                //停用
                invalidCode(obj,data.code,data.status);
            }
        });

        //监听提交
        form.on('submit(searchSubmit)', function(data){
            load(data);
            return false;
        });
    });

});

function invalidCode(obj,code,status) {
    if(null!=code){
        layer.confirm('您确定要停用挪车码吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/manage/updateStatus",{"code":code,"status":status},function(data){
                if (data.code == "0") {
                    layer.alert(data.msg,function(){
                        layer.closeAll();
                        load(obj);
                    });
                } else {
                    layer.alert(data.msg);
                }
            });
        }, function(){
            layer.closeAll();
        });
    }
}

function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

function cleanUser(){
    $("#phone").val("");
    $("#carNo").val("");
    $("#code").val("");
}
