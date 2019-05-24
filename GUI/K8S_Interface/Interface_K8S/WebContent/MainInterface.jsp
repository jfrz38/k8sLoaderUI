<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="tfg.k8s.gui.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	height: 100%;
	display: grid;
  	font-family: Arial;
  	color: black;
  	-webkit-text-size-adjust: 100%;
  	-webkit-font-smoothing: antialiased;
  
}
* {
  box-sizing: border-box;
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

/*.centered {
  position: absolute;
  margin-top:5%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}*/

.center_div{

	margin-top:5%;
	text-align: center;
	
}*/
/*input{
	margin-left:15%;
	display:block;
}*/

input,select{
	margin-top:25px;
}

/*.center_left_div{
	margin-left:15%;
	display:block;
}*/

.button_dcha{
	float:right;
	margin-right:40px;
}

hr{
	margin-top:25px;
	width: 75%;
	
}
.form_button{
	display:inline-block;
}
/*.select{

}*/



.inp {
  position: relative;
  margin: auto;
  width: 100%;
  max-width: 280px;
}
.inp .label {
  position: absolute;
  top: 14px;
  left: 0;
  /*font-size: 16px;*/
  /*color: #333;*/
  /*font-weight: 500;*/
  transform-origin: 0 0;
  transition: all 0.2s ease;
  
  color: #333; 
	font-family: 'Raleway',sans-serif; 
	font-size: 18px; 
	font-weight: 500; 
	/*line-height: 32px;*/
}
/*.inp .border {
  position: absolute;
  bottom: 0;
  left: 0;
  height: 2px;
  width: 100%;
  background: #07f;
  transform: scaleX(0);
  transform-origin: 0 0;
  transition: all 0.15s ease;
}*/

.inp input {
  -webkit-appearance: none;
  width: 50%;
  border: 0;
  font-family: inherit;
  padding: 12px 0;
  height: 48px;
  font-size: 16px;
  font-weight: 500;
  border-bottom: 2px solid #c8ccd4;
  background: none;
  border-radius: 0;
  color: #223254;
  transition: all 0.15s ease;
}
.inp input:hover {
  background: rgba(34,50,84,0.03);
}
.inp input:not(:placeholder-shown) + span {
  color: #5a667f;
  transform: translateY(-35px) scale(0.75);
}
.inp input:focus {
  background: none;
  outline: none;
}
.inp input:focus + span {
  color: #07f;
  transform: translateY(-35px) scale(0.75);
}
.inp input:focus + span + .border {
  transform: scaleX(1);
}
​

/***/

/***/

/**/


.select-container {
    position: relative;
    
}
   
select {
    border: 2px solid #eee;
    border-radius: 10px;
    -moz-appearance: none;
    -webkit-appearance: none;
    appearance:none;
    width: 50%;
    height: 35px;
    line-height: 35px;
    background: #FFF;
    font-size: 14px;
    font-weight: 500;
    color: #333;
    outline:none;
    padding-left: 15px;
    box-shadow: 3px 3px 30px #eee;
    transition: 0.2s;
    
    
    background: url(http://cdn1.iconfinder.com/data/icons/cc_mono_icon_set/blacks/16x16/br_down.png) no-repeat right #FFF;
    -webkit-appearance: none;
    background-position-x: 310px;
}
 
select:focus, select:hover {
  box-shadow: 3px 3px 10px #eee
}
   
select:-moz-focusring {
    color: transparent;
    text-shadow: 0 0 0 #000;
}
   
select::-ms-expand {
    display: none;
}
   


/***/
.lbl_title{
	/*color: #0098FF;*/
	color: black;
	font-family: 'Raleway',sans-serif;
	font-size: 25px;
	font-weight: 800;
	
	
	text-align: center;
	text-transform: uppercase;
}
/****/


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
  padding: 20px;
  width: 200px;
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


/******/
/**/
/**/

.custom_label { 
	color: #333; 
	font-family: 'Raleway',sans-serif; 
	font-size: 18px; 
	font-weight: 500; 
	line-height: 32px;
	
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
    <label class="lbl_title">Datos Peticiones</label>
    <hr>
    <form action="Results" id="results_form">
    
   	<!--  https://codepen.io/andreasstorm/pen/gKGbxo -->
	
	
	<label class="inp">
  	<input type="number" min="1" name="peticionesTotales" value="" placeholder="&nbsp;">
  	<span class="label">Peticiones totales</span>
  	<span class="border"></span>
	</label>
	
	<hr>
	
	<label class="inp">
  	<input type="number" min="1" name="peticionesConcurrentes" value="" placeholder="&nbsp;">
  	<span class="label">Peticiones concurrentes</span>
  	<span class="border"></span>
	</label>
	
	<hr>
	
	<label class="inp">
  	<input type="number" min="1" name="peticionesPorSegundo" value="" placeholder="&nbsp;">
  	<span class="label">Peticiones por segundo</span>
  	<span class="border"></span>
	</label>

	<hr>
		
	<div class="custom_label">URL (servicio)</div>
    <select name="servicio" id="comboBoxService"></select>
    
    <!--
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
	
	-->
  </div>
  
</div>


<div class="split right">
	<div class="center_div">
    <label class="lbl_title">Datos K8S</label>
    <hr>

    <div class="custom_label">Namespace</div>
     <!--  onchange="location.reload()"-->
     <div class="select-container">
    
    <select name="namespace" id="comboBoxNamespace" onChange="refreshComboBox()">
    	<% for( NamespaceK8S n : MainInterface.namespacesArray ){ %>
    	<option value="<%= n.getName() %>"><%= n.getName() %></option>
		<%}%>
	</select>
	</div>
	
    <hr>
    <div class="custom_label">Despliegue</div>
    <select name="deployment" id="comboBoxDeployment">
    	
	</select>
    <hr>
    <div class="custom_label">HPA</div>
    <select name="hpa" id="comboBoxHPA">
    	
	</select>
	</form>
    <hr>
    <div>
    <input class="button" type="submit" name="sendBtn" value="Enviar" form="results_form">
     
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