//获取给折线图准备的容器id
var dom = document.getElementById("chart-line");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '广告统计';

var colors = ['#5793f3', '#d14a61', '#675bba'];

option = {
    xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [820, 932, 901, 934, 1290, 1330, 1320],
        type: 'line'
    }]
};
myChart.setOption(option);