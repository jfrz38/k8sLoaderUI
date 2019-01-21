# TFGDocker

## Primeros pasos

Instalar Docker
```bash
sudo apt  install docker.io
```

Probar que funciona
```bash
sudo docker run hello-world
```

<details><summary>La salida por consola será:</summary>
<p>

```bash
Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
```

</p>
</details>



Instalar kubectl (vía https://kubernetes.io/docs/tasks/tools/install-kubectl/)
```bash
sudo apt-get update && sudo apt-get install -y apt-transport-https
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee -a /etc/apt/sources.list.d/kubernetes.list
sudo apt-get update
sudo apt-get install -y kubectl
```

Comprobar que funciona
```bash
kubectl cluster-info
```
Al ejecutar localmente sin un cluster creado (como Minikube, kubeadm...) la respuesta será:
```bash
The connection to the server localhost:8080 was refused - did you specify the right host or port?
```
Instalar minikube
Descargar
```bash
curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 \ && chmod +x minikube
```

Añadir al path
```bash
sudo cp minikube /usr/local/bin && rm minikube
```

Comprobar que está instalado correctamente
```bash
minikube status
```

Instalar go
```bash
apt  install golang-go
```

Configurar path
Depende de la ubicación donde se encuentre el paquete go/bin, en este caso
```bash
export PATH=$PATH:/home/ubuntu/go/bin
```
De forma genérica sería
```bash
export PATH=$PATH:/RUTA_GO/go/bin
```

Instalar hey
```bash
go get -u github.com/rakyll/hey
```

Instalar jq
```bash
apt install jq
```

Iniciar minikube
```bash
minikube start --memory 4096 --cpus 2 --vm-driver=none start
```

```bash

```
