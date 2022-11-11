/**
 * 
 */
/* 원그래프 */
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Men',     11],
          ['Women',      13]
        ]);

        var options = {
          title: '성별 분포도'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      };

/* 막대그래프 */
google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

      var data = google.visualization.arrayToDataTable([
        ['Age', '회원수',],
        ['10대', 27000],
        ['20대', 60000],
        ['30대', 35000],
        ['40대', 19000],
        ['50대 이상', 9000]
      ]);

      var options = {
        title: '연령별 분포도',
     
        hAxis: {
        
          minValue: 0
        },
        vAxis: {
          
        }
      };

      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

      chart.draw(data, options);
    }