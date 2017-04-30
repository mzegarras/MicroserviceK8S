# Microservice parametros#

Microservice parámetros

### Clonar el repositorio ###
git clone git@github.com:whatever .

### Generar imagen de base datos ###

1.- Ingresar a consola y ubicarse en la carpeta "database"

docker build -t msparametrosdb:1.0 .

### Probar imagen msparametrosdb:1.0 ###

1.- Eliminar container llamado msparametrosdb, el comando puede fallar
docker rm msparametrosdb -f

2.- Crear container msparametrosdb
docker run --name msparametrosdb -d msparametrosdb:1.0

3.- Conectarnos al container, prompt debe cambiar ("root@0767600f24c3:/#" ):

docker exec -i -t msparametrosdb /bin/bash
psql -h localhost -U postgres

4.- Listar valores de la tabla
docker=# select * from country;

5.- Ver objetos de base datos
docker=# \d

6.- Cerrar session postgress
docker=# \d

7.- Cerrar session container
exit


### Generar imagen de service ###

1.- Ingresar a consola y ubicarse en la carpeta "msservice"

docker build -t msparametros:1.0 .

### Probar imagen msparametros:1.0 ###

1.- Eliminar container llamado msparametros, el comando puede fallar
docker rm msparametros -f

2.- Crear container msparametros
docker run --name msparametros -p 8080:8080 -d --link=msparametrosdb msparametros:1.0

curl -H "Content-Type:application/json" http://localhost:8080/ms-parametros/api/country/v1/list

[ {
  "id" : 1,
  "description" : "Alemania"
}, {
  "id" : 2,
  "description" : "Argentina"
} ]

### Eliminar containers ###
docker rm msparametrosdb -f
docker rm msparametros -f



### Publicar images ###
docker login

docker tag msparametrosdb:1.0 mzegarra/msparametrosdb:1.0
docker tag msparametros:1.0 mzegarra/msparametros:1.0


docker push mzegarra/msparametrosdb:1.0
docker push mzegarra/msparametros:1.0

### Crear tu cuenta en aws, azure o google cloud ###


kubectl create -f db-deployment.yaml
service "msparametrosdb" created
deployment "msparametrosdb" created

kubectl create -f msservice-deployment.yaml
service "msparametros" created
deployment "msparametros" created


### Obtener ip pública generada
1.- Listar servicios de kuberntes 

kubectl get services

NAME             CLUSTER-IP    EXTERNAL-IP     PORT(S)        AGE
kubernetes       10.0.0.1      <none>          443/TCP        7d
msparametros     10.0.4.143    13.91.58.229    80:30191/TCP   4m
msparametrosdb   None          <none>          5432/TCP       19m

2.- Invocar al rest service

curl -H "Content-Type:application/json" http://13.91.58.229/ms-parametros/api/country/v1/list


### Obtener pods
1.- Listar servicios de kuberntes 

kubectl get pods

NAME                              READY     STATUS    RESTARTS   AGE
msparametros-1381946421-67vwg     1/1       Running   0          7m
msparametrosdb-4246394331-gq9sg   1/1       Running   0          22m

2.- Ver logs
kubectl logs msparametros-1381946421-67vwg