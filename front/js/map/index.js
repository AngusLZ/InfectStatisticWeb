$.ajax({
    type:'get',
    url:'http://localhost:8080/start',
    dataType:'json',
    async:false,
    success:function(result) {
        $('#loading').css("visibility", "hidden");
    }
});

$('.loading').css("visibility", "hidden");

var myChart = echarts.init(document.getElementById('china-map'));

var provinces = ['shanghai', 'hebei', 'shanxi', 'neimenggu', 'liaoning', 'jilin', 'heilongjiang', 'jiangsu', 'zhejiang', 'anhui', 'fujian', 'jiangxi', 'shandong', 'henan', 'hubei', 'hunan', 'guangdong', 'guangxi', 'hainan', 'sichuan', 'guizhou', 'yunnan', 'xizang', 'shanxi1', 'gansu', 'qinghai', 'ningxia', 'xinjiang', 'beijing', 'tianjin', 'chongqing', 'xianggang', 'aomen'];

var provincesText = ['上海', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新疆', '北京', '天津', '重庆', '香港', '澳门'];

var seriesData = {'china': [{
        name: '北京',
        value: 212
    }, {
        name: '天津',
        value: 60
    }, {
        name: '上海',
        value: 208
    }, {
        name: '重庆',
        value: 337
    }, {
        name: '河北',
        value: 126
    }, {
        name: '河南',
        value: 675
    }, {
        name: '云南',
        value: 117
    }, {
        name: '辽宁',
        value: 74
    }, {
        name: '黑龙江',
        value: 155
    }, {
        name: '湖南',
        value: 593
    }, {
        name: '安徽',
        value: 480
    }, {
        name: '山东',
        value: 270
    }, {
        name: '新疆',
        value: 29
    }, {
        name: '江苏',
        value: 308
    }, {
        name: '浙江',
        value: 829
    }, {
        name: '江西',
        value: 476
    }, {
        name: '湖北',
        value: 13522
    }, {
        name: '广西',
        value: 139
    }, {
        name: '甘肃',
        value: 55
    }, {
        name: '山西',
        value: 74
    }, {
        name: '内蒙古',
        value: 34
    }, {
        name: '陕西',
        value: 142
    }, {
        name: '吉林',
        value: 42
    }, {
        name: '福建',
        value: 179
    }, {
        name: '贵州',
        value: 56
    }, {
        name: '广东',
        value: 797
    }, {
        name: '青海',
        value: 15
    }, {
        name: '西藏',
        value: 1
    }, {
        name: '四川',
        value: 282
    }, {
        name: '宁夏',
        value: 34
    }, {
        name: '海南',
        value: 79
    }, {
        name: '台湾',
        value: 10
    }, {
        name: '香港',
        value: 15
    }, {
        name: '澳门',
        value: 9
    }],
    'zhejiang': [
        {
            name: '温州',
            value: 364
        },
        {
            name: '杭州',
            value: 141
        },
        {
            name: '台州',
            value: 122
        },
        {
            name: '宁波',
            value: 120
        },
        {
            name: '金华',
            value: 44
        },
        {
            name: '绍兴',
            value: 30
        },
        {
            name: '嘉兴',
            value: 27
        },
        {
            name: '丽水',
            value: 16
        },
        {
            name: '衢州',
            value: 15
        },{
            name: '湖州',
            value: 9
        },{
            name: '舟山',
            value: 7
        }

    ]};

var chinaPieces = [
    {min: 1000, max: 1000000, label: '大于等于1000人', color: '#372a28'},
    {min: 500, max: 999, label: '确诊500-999人', color: '#4e160f'},
    {min: 100, max: 499, label: '确诊100-499人', color: '#974236'},
    {min: 10, max: 99, label: '确诊10-99人', color: '#ee7263'},
    {min: 1, max: 9, label: '确诊1-9人', color: '#f5bba7'},
];

var proPieces = [
    {min: 1000, max: 1000000, label: '大于等于1000人', color: '#372a28'},
    {min: 500, max: 999, label: '确诊500-999人', color: '#4e160f'},
    {min: 100, max: 499, label: '确诊100-499人', color: '#974236'},
    {min: 10, max: 99, label: '确诊10-99人', color: '#ee7263'},
    {min: 1, max: 9, label: '确诊1-9人', color: '#f5bba7'},
];


/*
* 当前界面处于哪一种状态
* true：全国
* false：省份具体信息
* */
var flag=true;

var province="全国";

/*
* 返回全国按钮注册点击事件
* */
document.getElementById("whole").onclick = function () {
    initEcharts("china", "中国");
};

/*
* 初始化地图
* */
function initEcharts(pName, Chinese_,type=1) {

    var tmpSeriesData = [];
    if (pName === 'china') {
        flag=true;
        //隐藏省份疫情数据
        var dom = document.getElementById("province-message");
        dom.style.display="none";

        if(type===1){
            $.ajax({
                type:'get',
                url:'http://localhost:8080/provices',
                async:false,
                data:{
                    date:$('#datetimepicker1').find("input").val()
                },
                dataType:'json',
                success:function(result){
                    result.forEach((item)=>{
                        var ser = {
                            name: item.name,
                            value: item.currentConfirmedCount
                        };
                        tmpSeriesData.push(ser);
                    });
                }
            });
        }else{
            $.ajax({
                type:'get',
                url:'http://localhost:8080/provincesConfirmedCount',
                async:false,
                data:{
                    date:$('#datetimepicker1').find("input").val()
                },
                dataType:'json',
                success:function(result){
                    result.forEach((item)=>{
                        var ser = {
                            name: item.name,
                            value: item.currentConfirmedCount
                        };
                        tmpSeriesData.push(ser);
                    });
                }
            });
        }
    }else {
        flag=false;
        var jsonData = {"name": province, "date": $('#datetimepicker1').find("input").val()};
        $.ajax({
            type: "POST",
            contentType: "application/json",
            async:false,
            url: "http://localhost:8080/getTown",
            data: JSON.stringify(jsonData),
            dataType: "json",
            success: function(result) {
                if(type===1){
                    result.forEach((item)=>{
                        var ser = {
                            name: item.name,
                            value: item.currentConfirmedCount
                        };
                        tmpSeriesData.push(ser);
                    });
                }else{
                    result.forEach((item)=>{
                        var ser = {
                            name: item.name,
                            value: item.confirmedCount
                        };
                        tmpSeriesData.push(ser);
                    })
                }
            }
        });
    }
    var pieces = pName === "china" ? chinaPieces : proPieces;
    var name=pName === "china" ? "中国" : pName;
    var option = {
        title: {
            text: name + '疫情图',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            transitionDuration: 1,
            formatter: function (params) {
                var value = (params.value + '').split('.');
                value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,');
                var nameTemp=params.seriesName==="china"?"中国":params.seriesName;
                return nameTemp + '<br/>' + params.name + ': ' + value;
            }
        },
        visualMap: {
            type: 'piecewise',
            pieces: pieces,
            textStyle: {
                color: '#000000'
            },
            inRange: {
                color: ['lightskyblue', 'yellow', 'orangered']
            },
            top: '50%'
        },
        series: [
            {
                name: pName,
                type: 'map',
                mapType: pName,
                roam: false,//是否开启鼠标缩放和平移漫游
                itemStyle: {//地图区域的多边形 图形样式
                    normal: {//是图形在默认状态下的样式
                        label: {
                            show: true,//是否显示标签
                            textStyle: {
                                color: "rgba(255,255,255,0)"
                            }
                        }
                    },
                    emphasis: {//是图形在高亮状态下的样式,比如在鼠标悬浮或者图例联动高亮时
                        label: { show: true }
                    }
                },
                data: tmpSeriesData,
                top: "3%"//组件距离容器的距离
            }
        ]
    };
    myChart.setOption(option);
    myChart.off("click");
    if (pName === "china") { // 全国时，添加click 进入省级
        myChart.on('click', function (param) {
            //在本页面跳转
            window.location.hash='#centre';

            //遍历取到provincesText 中的下标  去拿到对应的省js
            for (var i = 0; i < provincesText.length; i++) {
                if (param.name === provincesText[i]) {
                    //显示对应省份的方法
                    showProvince(provincesText[i], provinces[i] );
                    break;
                }
            }
        });
    } else { // 省份，添加双击 回退到全国
        myChart.on("dblclick", function () {
            initEcharts("china", "中国");
        });
    }
}

/*
* 展示对应的省
* */
function showProvince(pName, Chinese_) {
    //显示被隐藏的疫情数据
    var dom = document.getElementById("province-message");
    dom.style.display="block";
    province=pName;
    $('#province-name').html(province+"疫情信息");
    initProvinceDigital();
    //这写省份的js都是通过在线构建工具生成的，保存在本地，需要时加载使用即可，最好不要一开始全部直接引入。
    loadBdScript('$' + Chinese_ + 'JS', './js/map/province/' + Chinese_ + '.js', function () {
        initEcharts(pName, Chinese_);
    });
}

/*
* 加载对应的JS
* */
function loadBdScript(scriptId, url, callback) {
    initNewDefiniteLine();
    var script = document.createElement("script");
    script.type = "text/javascript";
    if (script.readyState) {  //IE
        script.onreadystatechange = function () {
            if (script.readyState === "loaded" || script.readyState === "complete") {
                script.onreadystatechange = null;
                callback();
            }
        };
    } else {
        script.onload = function () {
            callback();
        };
    }
    script.src = url;
    script.id = scriptId;
    document.getElementsByTagName("head")[0].appendChild(script);
};


//---------------------------------------------------------------------------------//

function delay() {
    return new Date(new Date().getTime()-24*60*60*19*1000);
}

/*
* 设置全国疫情日期
* */
$('#datetimepicker1').datetimepicker({
    language:  'zh-CN',
    todayBtn: 1,
    autoclose: 1,
    forceParse: 0,
    minView: "month",
    todayHighlight: 1,
    format: 'yyyy-mm-dd',
    startDate: delay(),
    endDate:new Date()
}).on('changeDate',function(ev){
    var  time=$('#datetimepicker1').find("input").val();
    if(time!=''){
        initNationalDigital();
        initEcharts("china", "中国");
    }
});

/*
* 设置省份疫情日期
* */
$('#datetimepicker2').datetimepicker({
    language:  'zh-CN',
    todayBtn: 1,
    autoclose: 1,
    forceParse: 0,
    minView: "month",
    todayHighlight: 1,
    format: 'yyyy-mm-dd',
    startDate: delay(),
    endDate:new Date()
}).on('changeDate',function(ev){
    var  time=$('#datetimepicker2').find("input").val();
    if(time!=''){
        initProvinceDigital();
    }
});

Date.prototype.format =function(format) {
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "h+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
        (this.getFullYear()+"").substr(4- RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length==1? o[k] :
                ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}

$("#datetimepicker1").datetimepicker("setDate", new Date());
$("#datetimepicker2").datetimepicker("setDate", new Date());
$("#date-input1").attr("value",new Date().format('yyyy-MM-dd'));
$("#date-input2").attr("value",new Date().format('yyyy-MM-dd'));

//------------------------------------------------------------------------------//

/*
* 设置全国数字疫情
*
* $('#datetimepicker1').find("input").val()
* */
initNationalDigital();
function initNationalDigital(){
    $.ajax({
        type:'get',
        url:'http://localhost:8080/current',
        data:{
            date:$('#datetimepicker1').find("input").val()
        },
        dataType:'json',
        success:function(result){
            document.getElementById("definite-count-content").innerHTML=result["currentConfirmedCount"];
            document.getElementById("suspected-count-content").innerHTML=result["suspectedCount"];
            document.getElementById("severe-count-content").innerHTML=result["seriousCount"];
            document.getElementById("sum-definite-count-content").innerHTML=result["confirmedCount"];
            document.getElementById("sum-cure-count-content").innerHTML=result["curedCount"];
            document.getElementById("sum-dead-count-content").innerHTML=result["deadCount"];

            document.getElementById("definite-count-change").innerHTML=result["currentConfirmedIncr"]>0?"+"+result["currentConfirmedIncr"]:result["currentConfirmedIncr"];
            document.getElementById("suspected-count-change").innerHTML=result["suspectedIncr"]>0?"+"+result["suspectedIncr"]:result["suspectedIncr"];
            document.getElementById("severe-count-change").innerHTML=result["seriousIncr"]>0?"+"+result["seriousIncr"]:result["seriousIncr"];
            document.getElementById("sum-definite-count-change").innerHTML=result["confirmedIncr"]>0?"+"+result["confirmedIncr"]:result["confirmedIncr"];
            document.getElementById("sum-cure-count-change").innerHTML=result["curedIncr"]>0?"+"+result["curedIncr"]:result["curedIncr"];
            document.getElementById("sum-dead-count-change").innerHTML=result["deadIncr"]>0?"+"+result["deadIncr"]:result["deadIncr"];
        }
    });
}

initEcharts("china", "中国",1);

//-----------------------------------------------------------------------------------------//

/*
* 地图现有确诊按钮
* */
document.getElementById("definite-bt").onclick=function () {
    if(flag){
        initEcharts("china","中国",1);
    }else{
        initEcharts(province,"中国",1);
    }
};

/*
* 地图累计确诊按钮
* */
document.getElementById("sum-definite-bt").onclick=function () {
    if(flag){
        initEcharts("china","中国",2);
    }else{
        initEcharts(province,"中国",2);
    }
};

//---------------------------------------------------------------------------------------------//

/*
* 设置省份数字疫情
*
* info: {name:province,date:$('#datetimepicker2').find("input").val()}
* */
function initProvinceDigital(){
    var jsonData = {"name": province, "date": $('#datetimepicker2').find("input").val()};
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/specailProvince",
        data: JSON.stringify(jsonData),
        dataType: "json",
        success: function(result) {
            document.getElementById("province-definite-count-content").innerHTML=result["currentConfirmedCount"];
            document.getElementById("province-suspected-count-content").innerHTML=result["suspectedCount"];
            document.getElementById("province-cure-count-content").innerHTML=result["curedCount"];
            document.getElementById("province-dead-count-content").innerHTML=result["deadCount"];

            document.getElementById("province-definite-count-change").innerHTML=result["currentConfirmedIncr"]>0?"+"+result["currentConfirmedIncr"]:result["currentConfirmedIncr"];
            document.getElementById("province-suspected-count-change").innerHTML=result["suspectedIncr"]>0?"+"+result["suspectedIncr"]:result["suspectedIncr"];
            document.getElementById("province-care-count-change").innerHTML=result["curedIncr"]>0?"+"+result["curedIncr"]:result["curedIncr"];
            document.getElementById("province-dead-count-change").innerHTML=result["deadIncr"]>0?"+"+result["deadIncr"]:result["deadIncr"];
        }
    });
}


/*
* 曲线图新增确诊按钮
* */
document.getElementById("echart-definite-bt").onclick=function () {
    initNewDefiniteLine();
};

/*
* 曲线图累计确诊按钮
* */
document.getElementById("echart-sum-definite-bt").onclick=function () {
    initSumDefiniteLine();
};

/*
* 曲线图治愈/死亡按钮
* */
document.getElementById("echart-cure-dead-bt").onclick=function () {
    initCureAndDeadLine();
};

//-----------------------------------------------------------------------------------------------//

/*
* 新增确诊曲线
* */
initNewDefiniteLine();
function initNewDefiniteLine() {
    //获取给折线图准备的容器id
    var dom = document.getElementById("chart-line");
    dom.setAttribute('class','active');
    var myChart = echarts.init(dom);
    myChart.clear();

    var jsonData = {"name": province};
    $.ajax({
        type: "POST",
        contentType: "application/json",
        async:false,
        url: "http://localhost:8080/imgInfo",
        data: JSON.stringify(jsonData),
        dataType: "json",
        success: function(result) {
            var arrayX=[];
            var arrayY=[];
            for(i=9;i>=0;i--){
                arrayX.push(result[i].date);
                arrayY.push(result[i].currentConfirmedCount);
            }
            var option = {
                title: {
                    text: '新增确诊',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    type: 'category',
                    axisLabel:{
                        interval:0,
                        rotate:40
                    },
                    data: arrayX,
                },
                yAxis: {
                    type: 'value',
                },
                series: [{
                    name:"新增确诊",
                    data: arrayY,
                    type: 'line',
                    smooth: true
                }]
            };
            myChart.setOption(option);
        }
    });
}

/*
* 新增疑似曲线
* */
function initSumDefiniteLine() {
    //获取给折线图准备的容器id
    var dom = document.getElementById("chart-line");
    dom.setAttribute('class','active');
    var myChart = echarts.init(dom);
    myChart.clear();

    var jsonData = {"name": province};
    $.ajax({
        type: "POST",
        contentType: "application/json",
        async:false,
        url: "http://localhost:8080/imgInfo",
        data: JSON.stringify(jsonData),
        dataType: "json",
        success: function(result) {
            var arrayX=[];
            var arrayY=[];
            for(i=9;i>=0;i--){
                arrayX.push(result[i].date);
                arrayY.push(result[i].suspectedCount);
            }
            var option = {
                title: {
                    text: '新增疑似',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    type: 'category',
                    axisLabel:{
                        interval:0,
                        rotate:40
                    },
                    data: arrayX,
                },
                yAxis: {
                    type: 'value',
                },
                series: [{
                    name:"新增疑似",
                    data: arrayY,
                    type: 'line',
                    smooth: true
                }]
            };
            myChart.setOption(option);
        }
    });
}

/*
* 累计治愈/死亡曲线
* */
function initCureAndDeadLine() {
    //获取给折线图准备的容器id
    var dom = document.getElementById("chart-line");
    var myChart = echarts.init(dom);
    myChart.clear();

    var jsonData = {"name": province};
    $.ajax({
        type: "POST",
        contentType: "application/json",
        async:false,
        url: "http://localhost:8080/imgInfo",
        data: JSON.stringify(jsonData),
        dataType: "json",
        success: function(result) {
            var arrayX=[];
            var arrayYCure=[];
            var arrayYDead=[];
            for(i=9;i>=0;i--){
                arrayX.push(result[i].date);
                arrayYCure.push(result[i].curedCount);
                arrayYDead.push(result[i].deadCount);
            }
            var option = {
                title: {
                    text: '累计治愈/死亡'
                },
                tooltip: {
                    trigger: 'axis'
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    axisLabel: {
                        interval:0,
                        rotate:40
                    },
                    boundaryGap: false,
                    data: arrayX
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '累计治愈',
                        type: 'line',
                        smooth: true,
                        data: arrayYCure
                    },
                    {
                        name: '累计死亡',
                        type: 'line',
                        smooth: true,
                        data: arrayYDead
                    }
                ]
            };
            myChart.setOption(option);
        }
    });


}

//-----------------------------------------------------------------------------------------------//


