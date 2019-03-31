<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="tfg.k8s.gui.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: Arial;
  color: black;
}

.split {
  height: 100%;
  width: 50%;
  position: fixed;
  z-index: 1;
  top: 0;
  overflow-x: hidden;
  padding-top: 20px;
}

.left {
  left: 0;
  background-color: #DFFFFD;
}

.right {
  right: 0;
  background-color: #DFFFFD;
}
.right:hover,.left:hover{background-color:#B6EEEA;}

.centered {
  position: absolute;
  /*top: 50%;*/
  margin-top:5%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

.center_div{
	margin-top:5%;
	text-align: center;
	
}
/*input{
	margin-left:15%;
	display:block;
}*/
input,select{
	margin-top:10px;
}
.center_left_div{
	margin-left:15%;
	display:block;
}
.button_dcha{
	float:right;
	margin-right:40px;
}

hr{
	width: 75%;"
}
.form_button{
	display:inline-block;
}
</style>
</head>

<script>
		
		
		
	</script>
<body>

<%
	//Inicializar
  	MainInterface mi = new MainInterface();
%>
<div class="split left">
  <div class="center_div">
    <h2>Datos Peticiones</h2>
    <hr>
    <form action="Results" id="results_form">
    <div>Peticiones totales</div>
    <input type="number" min="1" name="peticionesTotales" value="">
    <hr>
    <div>Peticiones concurrentes</div>
    <input type="number" min="1" name="peticionesConcurrentes" value="">
    <hr>
    <div>Peticiones por segundo máximo (0 = sin límite)</div>
    <input type="number" min="1" name="peticionesPorSegundo" value="">
    <hr>
    <div>URL (servicio)</div>
    <select name="servicio" id="comboBoxService">
    
	</select>
	
  </div>
</div>

<div class="split right">
	<div class="center_div">
    <h2>Datos K8S</h2>
    <hr>
    <div>Namespace</div>
     <!--  onchange="location.reload()"-->
    <select name="namespace" id="comboBoxNamespace" onChange="refreshComboBox()">
    	<% for( NamespaceK8S n : MainInterface.namespacesArray ){ %>
    	<option value="<%= n.getName() %>"><%= n.getName() %></option>
		<%}%>
	</select>
    <hr>
    <div>Despliegue</div>
    <select name="deployment" id="comboBoxDeployment">
    	
	</select>
    <hr>
    <div>HPA</div>
    <select name="hpa" id="comboBoxHPA">
    	
	</select>
	</form>
    <hr>
    <div>
    <input type="submit" name="sendBtn" value="Send" form="results_form">
     
    </div>
	</div>
</div>
     <script>
     	/*window.onload = function() {
			var e = document.getElementById("comboBoxNamespace");
			var text = e.options[e.selectedIndex].text;
		}*/
     
  			/**REFRESH COMBO BOX DEPLOYMENT**/
  			function refreshComboBoxDeployment(){
  				var e = document.getElementById("comboBoxNamespace");
  		  		//var value = e.options[e.selectedIndex].value;
  				var text;
  				if(e.options[e.selectedIndex].text !== undefined){
  					text = e.options[e.selectedIndex].text;
  				}else{
  					text = "default";
  				}
  				var urlRefreshComboBox = "RefreshComboBoxDeployment.jsp?namespace="+text;
  				if (window.XMLHttpRequest) {
  					requestRefreshComboBox = new XMLHttpRequest();
  				} else if (window.ActiveXObject) {
  					requestRefreshComboBox = new ActiveXObject("Microsoft.XMLHTTP");
  				}
  				try {
  					requestRefreshComboBox.onreadystatechange = getInfoRefreshComboBox;
  					requestRefreshComboBox.open("GET", urlRefreshComboBox, true);
  					requestRefreshComboBox.send();	
  				} catch (e) {
  					alert("Unable to connect to server");
  				}
  			}		
  			
  			function getInfoRefreshComboBox() {	
  				if (requestRefreshComboBox.readyState == 4) {
  					var ret = requestRefreshComboBox.responseText;
  					//document.getElementById('comboBoxDeployment').innerHTML = eval(ret)[0];
  					document.getElementById('comboBoxDeployment').innerHTML = ret;
  					
  				}
  			}
  			

  			/**REFRESH COMBO BOX HPA**/
  			function refreshComboBoxHPA(){
  				var e = document.getElementById("comboBoxNamespace");
  				var text;
  				if(e.options[e.selectedIndex].text !== undefined){
  					text = e.options[e.selectedIndex].text;
  				}else{
  					text = "default";
  				}
  				var urlRefreshComboBoxHPA = "RefreshComboBoxHPA.jsp?namespace="+text;
  				if (window.XMLHttpRequest) {
  					requestRefreshComboBoxHPA = new XMLHttpRequest();
  				} else if (window.ActiveXObject) {
  					requestRefreshComboBoxHPA = new ActiveXObject("Microsoft.XMLHTTP");
  				}
  				try {
  					requestRefreshComboBoxHPA.onreadystatechange = getInfoRefreshComboBoxHPA;
  					requestRefreshComboBoxHPA.open("GET", urlRefreshComboBoxHPA, true);
  					requestRefreshComboBoxHPA.send();	
  				} catch (e) {
  					alert("Unable to connect to server");
  				}
  			}
  			
  			function getInfoRefreshComboBoxHPA() {	
  				if (requestRefreshComboBoxHPA.readyState == 4) {
  					var ret = requestRefreshComboBoxHPA.responseText;
  					//document.getElementById('comboBoxDeployment').innerHTML = eval(ret)[0];
  					document.getElementById('comboBoxHPA').innerHTML = ret;
  					
  				}
  			}
  			
  			/**REFRESH COMBO BOX SERVICE**/
  			function refreshComboBoxService(){
  				var e = document.getElementById("comboBoxNamespace");
  		  		//var value = e.options[e.selectedIndex].value;
  				var text;
  				if(e.options[e.selectedIndex].text !== undefined){
  					text = e.options[e.selectedIndex].text;
  				}else{
  					text = "default";
  				}
  				var urlRefreshComboBoxService = "RefreshComboBoxService.jsp?namespace="+text;
  				if (window.XMLHttpRequest) {
  					requestRefreshComboBoxService = new XMLHttpRequest();
  				} else if (window.ActiveXObject) {
  					requestRefreshComboBoxService = new ActiveXObject("Microsoft.XMLHTTP");
  				}
  				try {
  					requestRefreshComboBoxService.onreadystatechange = getInfoRefreshComboBoxService;
  					requestRefreshComboBoxService.open("GET", urlRefreshComboBoxService, true);
  					requestRefreshComboBoxService.send();	
  				} catch (e) {
  					alert("Unable to connect to server");
  				}
  			}		
  			
  			function getInfoRefreshComboBoxService() {	
  				if (requestRefreshComboBox.readyState == 4) {
  					var ret = requestRefreshComboBox.responseText;
  					//document.getElementById('comboBoxDeployment').innerHTML = eval(ret)[0];
  					document.getElementById('comboBoxService').innerHTML = ret;
  					
  				}
  			}
  			
			function refreshComboBox() {
  				
				refreshComboBoxDeployment()
				refreshComboBoxHPA()
				refreshComboBoxService()
				
  			}
			
  			window.onload = refreshComboBox;
	</script>

</body>
</html>