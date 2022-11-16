/**
 * 
 */
let frmGraph = document.frm_graph;
let mSize = document.querySelector("#mSize");
let fSize = document.querySelector("#fSize");
let tenSize = document.querySelector("#tenSize");
let twentySize = document.querySelector("#twentySize");
let thirtySize = document.querySelector("#thirtySize");
let fourtySize = document.querySelector("#fourtySize");
let fiftySize = document.querySelector("#fiftySize");


if(frmGraph != null){
	frmGraph.btnSelect.addEventListener('click',function() {
		frmGraph.action = 'action.admin?job=graphSearch';
		frmGraph.SERIAL.value = frmGraph.findStr.value;
		frmGraph.nowPage.value=1;
		frmGraph.submit();
	})
}
/* 원그래프 */
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['남성', Number(mSize.value)],
          ['여성', Number(fSize.value)]
        ]);

        var options = {
          title: '성별별 통계'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      };

/* 막대그래프 */
google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

      var data = google.visualization.arrayToDataTable([
        ['Age', '회원수',{role:'style'}],
        ['10대', Number(tenSize.value), '#f02fa2'],
        ['20대', Number(twentySize.value),'#a0cfa6'],
        ['30대', Number(thirtySize.value),'#ba3f52'],
        ['40대', Number(fourtySize.value),'#126ab1'],
        ['50대 이상', Number(fiftySize.value),'#f22c32']
      ]);

      var options = {
        title: '연령별 통계',
     
        hAxis: {
        
          minValue: 0
        },
        vAxis: {
          
        }
      };

      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

      chart.draw(data, options);
    }