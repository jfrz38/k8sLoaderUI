#Ir al POM
# cd $(dirname "$(realpath $0)")/GUI/K8S_Interface/Interface_K8S

# Maven
mvn clean install -f $(dirname "$(realpath $0)")/GUI/K8S_Interface/Interface_K8S

# Mover el .war a la carpeta de tomcat
mv $(dirname "$(realpath $0)")/GUI/K8S_Interface/Interface_K8S/target/Interface_K8S-0.0.1.war /opt/tomcat/apache-tomcat-9.0.16/webapps

