#!/bin/bash

#Ir al directorio donde está el proyecto

#Git pull

#Correr con maven para generar el .war

cd /home/ubuntu/TFGDocker/GUI/K8S_Interface/Interface_K8S
mvn clean install

#Mover el .war a la carpeta de tomcat 

mv /home/ubuntu/TFGDocker/GUI/K8S_Interface/Interface_K8S/target/Interface_K8S-0.0.1.war /opt/tomcat/apache-tomcat-9.0.16/webapps
