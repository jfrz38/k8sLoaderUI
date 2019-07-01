#!/bin/bash

#Directorio actual
CURRENTDIR=$(pwd)
#Correr con maven para generar el .war

cd $CURRENTDIR/GUI/K8S_Interface/Interface_K8S
mvn clean install

#Mover el .war a la carpeta de tomcat

mv $CURRENTDIR/GUI/K8S_Interface/Interface_K8S/target/Interface_K8S-0.0.1.war /opt/tomcat/apache-tomcat-9.0.16/webapps
