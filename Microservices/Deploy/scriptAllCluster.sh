#!/bin/bash

#Cambiar entorno de Minikube al de Docker
eval $(minikube docker-env)

#Crear imagen app1
docker build -t app1 Deploy/app1

#Crear imagen app2
#cd /home/jf/JoseF/TFGDocker/Microservices/Deploy/app2
docker build -t app2 Deploy/app2

#Crear imagen app3
#cd /home/jf/JoseF/TFGDocker/Microservices/Deploy/app3
docker build -t app3 Deploy/app3

#Desplegar en Kubernetes
kubectl apply -f Deploy/k8s/app-deployment.yaml

# Crear servicios
kubectl apply -f Deploy/k8s/services-app.yaml

#Crear namespace monitoring
kubectl create namespace monitoring

#Prometheus HELM
# helm install --name prometheus-operator stable/prometheus --namespace monitoring
# Ir a la ruta de desplegar prometheus

# kubectl apply -f Deploy/Prometheus/prometheus-operator/monitoring-namespace.yaml
 
kubectl apply -f Deploy/Prometheus/prometheus-operator

# HELM

kubectl -n kube-system create serviceaccount tiller

kubectl create clusterrolebinding tiller --clusterrole cluster-admin --serviceaccount=kube-system:tiller

helm init --service-account tiller

kubectl create clusterrolebinding anonymous-role-binding --clusterrole=cluster-admin --user=system:anonymous

# helm delete prometheus-adapter
# helm del --purge prometheus-adapter

#Cluster IP
$(kubectl get svc prometheus -n monitoring -o=jsonpath='{.items[*]}{.spec.clusterIP}')

helm install --name prometheus-adapter stable/prometheus-adapter --set prometheus.url="http://$(kubectl get svc prometheus -n monitoring -o=jsonpath='{.items[*]}{.spec.clusterIP}')",prometheus.port="9090" --namespace kube-system


