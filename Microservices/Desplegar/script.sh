#Iniciar minikube
#minikube start --memory 4096 --cpus 3

#Apuntar al entorno de Minikube
eval $(minikube docker-env)

#Construir imágenes de las APPs
docker build -t app1 ./Docker/app1/
docker build -t app2 ./Docker/app2/
docker build -t app3 ./Docker/app3/

#Prometheus
kubectl apply -f Prometheus/Operator/

#Certificados
make certs

#Custom metrics API
kubectl apply -f Prometheus/Adapter/

#Aplicación
kubectl apply -f APP
