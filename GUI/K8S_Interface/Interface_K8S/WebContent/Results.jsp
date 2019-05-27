<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="tfg.k8s.gui.*"%>
<%@ page import="java.util.concurrent.TimeUnit"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>

<%
	Peticion p = (Peticion) request.getAttribute("peticion");
	Results r = new Results();;
	
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
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
  /*border: 1px solid black;*/
  
}
tr:nth-child(even) {
  background-color: #eee;
}
tr:nth-child(odd) {
 	background-color: #fff;
}
th{
	background-color: #39CCC2;
}
tr:hover{
	
	background-color: #5FEBEB;
}
/*td{
background-color: white;
}*/
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
label{
	margin-left:1%;
	font-weight: bold;
	font-size:24px;
}
hr{
	margin-top:10px;
	width: 75%;
}
label{
	/*color: #0098FF;*/
	color: black;
	font-family: 'Raleway',sans-serif;
	font-size: 25px;
	font-weight: 800;
	
	
	text-align: center;
	text-transform: uppercase;
}
/********/
/***/
.button {
  text-align: center;
  text-transform: uppercase;
  cursor: pointer;
  font-size: 11px;
  letter-spacing: 4px;
  position: relative;
  /*background-color: #16a085;*/
  background-color: #0098FF;
  border: none;
  color: #fff;
  padding: 10px;
  width: 100px;
  text-align: center;
  transition-duration: 0.4s;
  overflow: hidden;
  box-shadow: 0 5px 15px #193047;
  border-radius: 4px;
}
.button:hover {
  background: #fff;
  box-shadow: 0px 2px 10px 5px #1abc9c;
  color: #000;
}
.button:after {
  content: "";
  background: #1abc9c;
  display: block;
  position: absolute;
  padding-top: 300%;
  padding-left: 350%;
  margin-left: -20px !important;
  margin-top: -120%;
  opacity: 0;
  transition: all 0.8s
}
.button:active:after {
  padding: 0;
  margin: 0;
  opacity: 1;
  transition: 0s
}
.button:focus { outline:0; }
</style>

</head>


<body>

<div_left id="div_left_up">
	<p></p>
	
	<div>
		<label>HPA</label>
		<button class="button" name="clear_hpa" value="Clear" onclick="func_clearHPA()" style="float: right; margin-right: 1%;">Borrar</button>	
	</div>

	<p></p>
	<div id="div_left_up_refresh"></div>
</div_left>

<div_right id="div_right">
	<p> </p>
	<div style="text-align: center"><label> Resultados</label></div>
	
	<hr>
    <p style="margin-right:2%; text-align: right;">
    Obtenidos gracias a hey 
    <a href="https://github.com/rakyll/hey">
    <img src="${pageContext.request.contextPath}/images/GitHubIcon.png" style="width:32px;height:32px;">
    </a>
    </p>
    <div class="parent" style="width:99%; height:70%; margin-left: 0.5%; margin-right: 0.5%; position:relative">
  	<div class="loadGIF">
  		<img class="imageGIF" src="${pageContext.request.contextPath}/images/loading.gif" id="loadGIF">
  	</div>
  	<textarea readonly style="width:99%; height:99%; margin-left: 0.5%; margin-right: 0.5%;" id="textAreaHey">
  	
	</textarea>
	</div>
	
	<br>
	<input class="button" style="float: right; margin-right: 1%;" type="submit" name="backBtn" value="Atr&aacute;s" onclick="back_button()">

</div_right>
<div_left id="div_left_down">
	<p></p>
	
	<label>Despliegue</label>

	<button class="button" name="clear_hpa" value="Clear" onclick="func_clearDeployment()" style="float: right; margin-right: 1%;">Borrar</button>

	<p></p>
	<div id="chart_div" style="width: 100%; height: 75%"></div>
</div_left>
     
</body>
<%
	
	r.lanzarHilos(p);
	
%>

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
		
		window.setInterval("loadTableHPAInfinite()", 15000);
		
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
					var tableHPA = '<table style="width:99%; margin-left: 0.5%; margin-right: 0.5%;"><tr><th>Nombre</th><th>Referencia</th><th>Objetivos</th><th>minPods</th><th>maxPods</th><th>Réplicas</th><th>Tiempo</th></table>'
					document.getElementById('div_left_up_refresh').innerHTML = tableHPA;
				}
			}

		}
		
		/**STOP HPA**/
		
		function func_stopHPA() {
			var elem = document.getElementById('stop_hpa_button');
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
					var tableDeployment = '<table style="width:99%; margin-left: 0.5%; margin-right: 0.5%;"><tr><th id="deployment_table">Nombre</th><th>Deseado</th><th>Actual</th><th>Por actualizar</th><th>Disponible</th><th>Tiempo</th></table>'
					document.getElementById('div_left_down_refresh').innerHTML = tableDeployment;
					
				}
			}

		}
		
		/**STOP DEPLOYMENT**/
		
		function func_stopDeployment() {
			var elem = document.getElementById('stop_deployment_button');
			
			
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
		
		/*
		GET OUTPUT HEY
		*/
		var timer;
		function getHeyOutput() {
			var urlHey = "RefreshOutputHey.jsp";
			if (window.XMLHttpRequest) {
				requestHey = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				requestHey = new ActiveXObject("Microsoft.XMLHTTP");
			}
			try {
				requestHey.onreadystatechange = getOutputHey;
				requestHey.open("GET", urlHey, true);
				requestHey.send();	
			} catch (e) {
				alert("Unable to connect to server");
			}

		}
		
		function getOutputHey() {
			if (requestHey.readyState == 4) {
				var ret = requestHey.responseText;
				if(ret.includes("error = -1 ; not loaded yet")){
					delayExecution();
				}else{
					window.alert("return salida");
					window.alert("salida = "+ret);
					document.getElementById('textAreaHey').innerHTML = ret;
					document.getElementById('loadGIF').style.visibility = "hidden";
				}
				
			}
			
		}
		
		function sleep(ms) {
			  return new Promise(resolve => setTimeout(resolve, ms));
		}
		async function delayExecution() {
			  await sleep(1000);
			  getHeyOutput();
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
			getHeyOutput();
		}
		window.onload = start();
		
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

		var cabecera = [ [ 'Tiempo', 'Deseado', 'Actual', 'Por actualizar',
				'Disponible' ] ];

		var newDataArray = cabecera.concat(eval("[" + entryData + "]"));
		var data = google.visualization.arrayToDataTable(newDataArray);
		var dateFormatter = new google.visualization.DateFormat({pattern: 'HH:mm:ss'});
		dateFormatter.format(data, 0);
		
		var maxValueV;
		if(eval(entryData)[1] !== undefined){
			maxValueV = eval(entryData)[1] + 2;
		}else{
			maxValueV = 2;
		}
		
		
		var options = {
				
        hAxis: {title: 'Time', format:'##s', minValue : 0},
        vAxis : {
			title : 'Replicas',
			minValue : 0,
			viewWindow: {
		        max: maxValueV
			},
		},
		backgroundColor : 'transparent'
    	};
		
		var chart = new google.charts.Bar(document.getElementById('chart_div'));
		chart.draw(data, google.charts.Bar.convertOptions(options));
	}

	function repeat() {
		window.setInterval("loadGraphDeployment()", 15000);
	}

	function startChart() {
		repeat();
	}
	window.onload = startChart;
</script>
</html>