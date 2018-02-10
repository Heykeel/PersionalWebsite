
/** about-me.jsp ***************************************************************/

/* 绘制球形图 */
var chartColors = window.chartColors;
var color = Chart.helpers.color;
var config = {
	data : {
		datasets : [ {
			data : [ 10, 9, 8.5, 9, 9],
			backgroundColor : [ color(chartColors.red).alpha(0.5).rgbString(),
					color(chartColors.orange).alpha(0.5).rgbString(),
					color(chartColors.yellow).alpha(0.5).rgbString(),
					color(chartColors.blue).alpha(0.5).rgbString(),
					color(chartColors.grey).alpha(0.5).rgbString(), ],
			label : 'My dataset' // for legend
		} ],
		labels : [ "Java", "Spring", "SpringMVC", "Mybatis", "MySQL" ]
	},
	options : {
		responsive : true,
		legend : {
			position : 'right',
		},
		title : {
			display : false,
			text : 'Java Back-end Skills'
		},
		scale : {
			ticks : {
				beginAtZero : true
			},
			reverse : false
		},
		animation : {
			animateRotate : false,
			animateScale : true
		}
	}
};

	window.onload = function() {
	/* 加载球状Chart */
	var ctx = document.getElementById("chart-area");
	window.myPolarArea = Chart.PolarArea(ctx, config);
};


/** blogTypeList.jsp ***************************************************************/

/* 添加博客类别 */
function addType() {
	var typeName = $("#typeName").val();
	var orderNum = $("#orderNum").val();

	if (typeName == null || typeName == "") {
		$("#error").html("类别名不能为空！");
		return false;
	}
	if (orderNum == null || orderNum == "") {
		$("#error").html("优先级不能为空！");
		return false;
	}
	return true;
}





