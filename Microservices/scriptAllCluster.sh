#!/bin/bash

#Cambiar entorno de Minikube al de Docker
eval $(minikube docker-env)

#Crear imagen app1
cd /home/jf/JoseF/TFGDocker/Microservices/Deploy/app1
docker build -t app1 .

#Crear imagen app2
cd /home/jf/JoseF/TFGDocker/Microservices/Deploy/app2
docker build -t app2 .

#Crear imagen app3
cd /home/jf/JoseF/TFGDocker/Microservices/Deploy/app3
docker build -t app3 .

#Desplegar en Kubernetes

cd /home/jf/JoseF/TFGDocker/Microservices/Deploy
kubectl apply -f app-deployment.yaml


# Crear servicio

kubectl apply -f services-app.yaml

# Ir a la ruta de desplegar prometheus

cd /home/jf/JoseF/DesplegarPrometheus/minikube-prometheus-demo

kubectl apply -f monitoring-namespace.yaml

kubectl apply -f prometheus-config.yaml

kubectl apply -f prometheus-deployment.yaml

kubectl apply -f prometheus-service.yaml

kubectl apply -f node-exporter-daemonset.yml

# HELM

kubectl -n kube-system create serviceaccount tiller

kubectl create clusterrolebinding tiller --clusterrole cluster-admin --serviceaccount=kube-system:tiller

helm init --service-account tiller

kubectl create clusterrolebinding anonymous-role-binding --clusterrole=cluster-admin --user=system:anonymous

helm delete prometheus-adapter
helm del --purge prometheus-adapter
