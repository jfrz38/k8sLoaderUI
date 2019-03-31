<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="tfg.k8s.gui.*"%>
<%@ page import="java.util.concurrent.TimeUnit"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>

<%
	Peticion p = (Peticion) request.getAttribute("peticion");
	Results r = new Results(p);
	//session.setAttribute("PeticionSesion", p);
	
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
html, body { height: 100%; /*padding: 0; margin: 0;*/ }
div_left { width: 50%; height: 50%; float: left; margin}
div_right { width: 50%; height: 100%; float: right; overflow:auto;}
#div_left_up { background: #DFFFFD; overflow:auto; white-space:nowrap }
#div_left_down { background: #DFFFFD; overflow:auto; white-space:nowrap;}
#div_right { background: #DFFFFD; white-space:nowrap}
#div_left_down:hover,#div_left_up:hover,#div_right:hover{background-color:#B6EEEA;}
.centered {
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}
textarea {
  width: 100%;
  height: 70%;
  padding: 12px 20px;
  box-sizing: border-box;
  border: 2px solid #ccc;
  border-radius: 4px;
  background-color: #f8f8f8;
  resize: none;
  white-space:nowrap;
  overflow:auto;
}
table, th, td {
  border: 1px solid black;
  
}
td{
background-color: white;
}

.loadGIF{
	position: absolute;
	width:99%;
	height:99%;
	margin-left: 0.5%;
	margin-right: 0.5%;
}
.imageGIF{
	display: block;
    margin-left: auto;
    margin-right: auto;
    margin-top: 20%;
}
</style>

</head>
<script>
       
		/**LOAD TABLE HPA**/
       function loadTableHPA() {
			var urlHPA = "HpaTable.jsp";
			if (window.XMLHttpRequest) {
				request = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			try {
				request.onreadystatechange = getInfoHPA;
				request.open("GET", urlHPA, true);
				request.send();	
			} catch (e) {
				alert("Unable to connect to server");
			}

		}
		
		function getInfoHPA() {	
			if (request.readyState == 4) {
				var ret = request.responseText;
				document.getElementById('div_left_up_refresh').innerHTML = ret;
			}
		}
		
		window.setInterval("loadTableHPAInfinite()", 4000);
		
		function loadTableHPAInfinite() {
			var urlHPA = "HpaTable.jsp";
			if (window.XMLHttpRequest) {
				request = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			try {
				request.onreadystatechange = getInfoHPA;
				request.open("GET", urlHPA, true);
				request.send();	
			} catch (e) {
				alert("Unable to connect to server");
			}

		}
		
		
		/**LOAD TABLE DEPLOYMENT**/
		
		 /*function loadTableDeployment() {
			var urlDeployment = "DeploymentTable.jsp";
			if (window.XMLHttpRequest) {
				requestDeployment = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				requestDeployment = new ActiveXObject("Microsoft.XMLHTTP");
			}
			try {
				requestDeployment.onreadystatechange = getInfoDeployment;
				requestDeployment.open("GET", urlDeployment, true);
				requestDeployment.send();	
			} catch (e) {
				alert("Unable to connect to server");
			}

		}
		
		function getInfoDeployment() {	
			if (requestDeployment.readyState == 4) {
				var ret = requestDeployment.responseText;
				document.getElementById('div_left_down_refresh').innerHTML = ret;
			}
		}
		
		window.setInterval("loadDeploymentInfinite()", 4000);
		
		function loadDeploymentInfinite() {
			var urlDeployment = "DeploymentTable.jsp";
			if (window.XMLHttpRequest) {
				requestDeployment = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				requestDeployment = new ActiveXObject("Microsoft.XMLHTTP");
			}
			try {
				requestDeployment.onreadystatechange = getInfoDeployment;
				requestDeployment.open("GET", urlDeployment, true);
				requestDeployment.send();	
			} catch (e) {
				alert("Unable to connect to server");
			}

		}*/
		
		
		/**CLEAR HPA**/
		
		function func_clearHPA() {
			var url = "DoActionButton.jsp?accion=clearHPA";
			if (window.XMLHttpRequest) {
				requestClearHPA = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				requestClearHPA = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			try {
				requestClearHPA.onreadystatechange = getClearHPA;
				requestClearHPA.open("GET", url, true);
				requestClearHPA.send();	
			} catch (e) {
				alert("Unable to connect to server");
			}
			
			function getClearHPA() {	
				if (requestClearHPA.readyState == 4) {
					var tableHPA = '<table style="width:99%; margin-left: 0.5%; margin-right: 0.5%;"><tr><th>Nombre</th><th>Reference</th><th>Targets</th><th>minPods</th><th>maxPods</th><th>replicas</th><th>age</th></table>'
					document.getElementById('div_left_up_refresh').innerHTML = tableHPA;
				}
			}

		}
		
		/**STOP HPA**/
		
		function func_stopHPA() {
			var elem = document.getElementById('stop_hpa_button');
			/*var txt = elem.textContent || elem.innerText;
			
			if(txt=="Stop"){
				var urlStopHPA = "DoActionButton.jsp?accion=stopHPA";
			}else{
				var urlStopHPA = "DoActionButton.jsp?accion=startHPA";
			}*/
			
			var urlStopHPA = "DoActionButton.jsp?accion=stopHPA";
			
			if (window.XMLHttpRequest) {
				requestStopHPA = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				requestStopHPA = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			try {
				requestStopHPA.onreadystatechange = getStopHPA;
				requestStopHPA.open("GET", urlStopHPA, true);
				requestStopHPA.send();	
			} catch (e) {
				alert("Unable to connect to server");
			}
			
			function getStopHPA() {	
				if (requestStopHPA.readyState == 4) {
					//document.getElementById("stop_hpa_button").disabled = true;
					
					/*if(txt=="Stop"){
						document.getElementById("stop_hpa_button").innerHTML = "Start";
					}else{
						document.getElementById("stop_hpa_button").innerHTML = "Stop";
					}*/
				}
			}

		}
		
		
		/**CLEAR DEPLOYMENT**/
		
		function func_clearDeployment() {
			var urlClearDeployment = "DoActionButton.jsp?accion=clearDeployment";
			if (window.XMLHttpRequest) {
				requestClearDeployment = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				requestClearDeployment = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			try {
				requestClearDeployment.onreadystatechange = getClearDeployment;
				requestClearDeployment.open("GET", urlClearDeployment, true);
				requestClearDeployment.send();	
			} catch (e) {
				alert("Unable to connect to server");
			}
			
			function getClearDeployment() {	
				if (requestClearDeployment.readyState == 4) {
					var tableDeployment = '<table style="width:99%; margin-left: 0.5%; margin-right: 0.5%;"><tr><th id="probando">Nombre</th><th>Desired</th><th>Current</th><th>up-to-date</th><th>available</th><th>Age</th></table>'
					document.getElementById('div_left_down_refresh').innerHTML = tableDeployment;
					//drawChart("[0, 0, 0, 0, 0]");
				}
			}

		}
		
		/**STOP DEPLOYMENT**/
		
		function func_stopDeployment() {
			var elem = document.getElementById('stop_deployment_button');
			/*var txt = elem.textContent || elem.innerText;
			if(txt=="Stop"){
				var urlStopDeployment = "DoActionButton.jsp?accion=stopDeployment";
			}else{
				var urlStopDeployment = "DoActionButton.jsp?accion=startDeployment";
			}*/
			
			var urlStopDeployment = "DoActionButton.jsp?accion=stopDeployment";
			
			if (window.XMLHttpRequest) {
				requestStopDeployment = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				requestStopDeployment = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			try {
				requestStopDeployment.onreadystatechange = getStopDeployment;
				requestStopDeployment.open("GET", urlStopDeployment, true);
				requestStopDeployment.send();	
			} catch (e) {
				alert("Unable to connect to server");
			}
			
			function getStopDeployment() {	
				if (requestStopDeployment.readyState == 4) {
					/*if(txt=="Stop"){
						document.getElementById("stop_deployment_button").innerHTML = "Start";
					}else{
						document.getElementById("stop_deployment_button").innerHTML = "Stop";
					}*/
				}
			}

		}
		
		
		function back_button(){
			func_stopHPA();
			func_stopDeployment();
			func_clearHPA();
			func_clearDeployment();
			window.location.href='MainInterface.jsp';
		}
		
		/*************************/
		function start() {
			loadTableHPA();
			//loadTableDeployment();
		}
		window.onload = start;
		
	</script>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	/****************************************************************/

	/**LOAD GRAPHIC DEPLOYMENT**/

	function loadGraphDeployment() {
		var urlGraph = "DrawGraph.jsp";
		if (window.XMLHttpRequest) {
			requestDeploymentGraph = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestDeploymentGraph = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestDeploymentGraph.onreadystatechange = getInfoDeploymentGraph;
			requestDeploymentGraph.open("GET", urlGraph, true);
			requestDeploymentGraph.send();
		} catch (e) {
			alert("Unable to connect to server");
		}

	}

	function getInfoDeploymentGraph() {
		if (requestDeploymentGraph.readyState == 4) {
			var response = requestDeploymentGraph.responseText;
			drawChart(response)

		}
	}

	/**********************************************************/

	function drawChart(entryData) {

		var cabecera = [ [ 'Time', 'Desired', 'Current', 'Up-to-date',
				'Available' ] ];

		var newDataArray = cabecera.concat(eval("[" + entryData + "]"));
		var data = google.visualization.arrayToDataTable(newDataArray);

		var maxValueV;
		//alert("entyData = "+entryData);
		//alert("entyData[0] = "+eval(entryData)[1]);
		if(eval(entryData)[1] !== undefined){
			maxValueV = eval(entryData)[1] + 2;
		}else{
			maxValueV = 2;
		}
		//alert("maxValueV = "+maxValueV);
		
		var options = {
			//title: 'Deployment',
			hAxis : {
				//ticks: [{v:0, f:"0:00.0"}, {v:30, f:"0:30.0"}, {v:60, f:"1:00:0"}],
				//format:'##:##s',
				format: 'HH:mm',
				title : 'Time (s)',
				titleTextStyle : {
					color : '#333'
				},
				minValue : 0,
				/*scales:{
		            xAxes: [{
		                display: false //this will remove all the x-axis grid lines
		            }]
		        }*/
				//minValue: eval("[" + entryData + "]")[0]
			},
			vAxis : {
				title : 'Replicas',
				/*range: {
                    max: maxValueV,
                    min: 0
                },*/
				minValue : 0,
				viewWindow: {
			        max: maxValueV
				},
				//maxValue: maxValueV
				//minValue: eval("[" + entryData + "]")[0]
			},
			backgroundColor : 'transparent'
		};

		
		/*
		var formatNumber = new google.visualization.NumberFormat(
		        {prefix: '', negativeColor: 'red', negativeParens: true});

		     var formatDate = new google.visualization.DateFormat(
		        { prefix: 'Time: ', pattern: "dd MMM HH:mm", });

		     formatDate.format(data, 0);
		     formatNumber.format(data, 1);
		     
		     
		     */
		//var chart = new google.visualization.AreaChart(document
				//.getElementById('chart_div'));
		//chart.draw(data, options);
		var chart = new google.charts.Bar(document.getElementById('chart_div'));
		chart.draw(data, google.charts.Bar.convertOptions(options));
	}

	function repeat() {
		window.setInterval("loadGraphDeployment()", 4000);
	}

	function startChart() {
		repeat();
	}
	window.onload = startChart;
</script>

<body>

<div_left id="div_left_up">
	<p></p>
	
	<label style="margin-left: 1%;">HPA</label> 
	<!--
	<button id="stop_hpa_button" value="Stop" onclick="func_stopHPA()" style="float: right; margin-right: 1%;">Stop</button>
	-->
	<button name="clear_hpa" value="Clear" onclick="func_clearHPA()" style="float: right; margin-right: 5%;">Clear</button>

	<p></p>
	<div id="div_left_up_refresh"></div>
</div_left>

<div_right id="div_right">
	<p> Resultados</p>
	<h2>bb</h2>
    <p>bb</p>
    <div class="parent" style="width:99%; height:70%; margin-left: 0.5%; margin-right: 0.5%; position:relative">
  	<div class="loadGIF">
  		<img class="imageGIF" src="${pageContext.request.contextPath}/images/loading.gif" id="loadGIF">
  	</div>
  	<textarea readonly style="width:99%; height:99%; margin-left: 0.5%; margin-right: 0.5%;">
  	
	
	<%
  	//out.print("<loading.gif' />");
  	//out.flush();
  	//mock processing
  	while(Results.heyActivo){
		//out.print("<br/>Processing!");
        //out.flush();
        Thread.sleep(10);  
  	}
  	out.print(Results.salidaHey);
  	out.flush();
	%>
	</textarea>
	</div>
	
	<br>
	<input style="float: right; margin-right: 5%;" type="submit" name="backBtn" value="Back" onclick="back_button()">
	<input style="float: right; margin-right: 5%;" type="submit" name="backBtn" value="Save" onclick="save_txt_button()">
    
</div_right>
<div_left id="div_left_down">
	<p></p>
	
	<label>Deployment</label>
	<!--
	<button id="stop_deployment_button" value="Stop" onclick="func_stopDeployment()" style="float: right; margin-right: 1%;">Stop</button>
	-->
	<button name="clear_hpa" value="Clear" onclick="func_clearDeployment()" style="float: right; margin-right: 5%;">Clear</button>

	<p></p>
	<div id="chart_div" style="width: 100%; height: 75%"></div>
</div_left>
     
</body>
</html>