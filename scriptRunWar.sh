#!/bin/bash

#Correr con maven para generar el .war
mydir "$0"
dirname "$(realpath $0)"
# echo dirname

cd $(dirname "$(realpath $0)")/GUI/K8S_Interface/Interface_K8S
mvn clean install

#Mover el .war a la carpeta de tomcat 

mv dirname "$(realpath $0)"/GUI/K8S_Interface/Interface_K8S/target/Interface_K8S-0.0.1.war /opt/tomcat/apache-tomcat-9.0.16/webapps
