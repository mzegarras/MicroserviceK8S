



RESOURCE_GROUP=rgACS
LOCATION=eastus
az group create --name=$RESOURCE_GROUP --location=$LOCATION

DNS_PREFIX=dnsACS
CLUSTER_NAME=clusterACS

az acs create --orchestrator-type=kubernetes --resource-group $RESOURCE_GROUP --name=$CLUSTER_NAME --dns-prefix=$DNS_PREFIX --generate-ssh-keys --agent-count=1 --master-count=1 --agent-vm-size=Standard_DS1_v2


az acs kubernetes get-credentials --resource-group=rgACS --name=clusterACS

pbcopy < ~/.ssh/id_rsa.pub



=================

RESOURCE_GROUP=rgACS2
LOCATION=eastus2
az group create --name=$RESOURCE_GROUP --location=$LOCATION

DNS_PREFIX=dnsACS2
CLUSTER_NAME=clusterACS2

az acs create --orchestrator-type=kubernetes --resource-group $RESOURCE_GROUP --name=$CLUSTER_NAME --dns-prefix=$DNS_PREFIX --generate-ssh-keys --agent-count=1 --master-count=1 --agent-vm-size=Standard_DS1_v2

az acs kubernetes get-credentials --resource-group=rgACS2 --name=clusterACS2


ibkperu01@hotmail.com ;; interbank.123



docker build -t msparametrosdb:3.0 .

docker tag msparametrosdb:2.0 rcapi.azurecr.io/poc/msparametrosdb:2.0


docker tag msparametros:3.0 rcapi.azurecr.io/poc/msparametros:3.0


https://stefanprodan.com/2016/a-monitoring-solution-for-docker-hosts-containers-and-containerized-services/


docker run -d --name=grafana -p 3000:3000 grafana/grafana


kubectl run -i -t --tty load-generator4 --image=grafana/grafana


docker run -i -p 3000:3000 grafana/grafana
kubectl run grafana --image=grafana/grafana --replicas=1

====
machine_cpu_cores

https://raw.githubusercontent.com/coreos/blog-examples/master/monitoring-kubernetes-with-prometheus/prometheus.yml
sum (rate (container_cpu_usage_seconds_total{id="/"}[1m])) / sum (machine_cpu_cores) * 100
===

https://github.com/camilb/prometheus-kubernetes/tree/master/grafana/grafana-dashboards

=====
https://kubernetes.io/docs/user-guide/kubectl-cheatsheet/
https://github.com/kubernetes/heapster/blob/master/docs/influxdb.md
https://stefanprodan.com/2016/a-monitoring-solution-for-docker-hosts-containers-and-containerized-services/

kubectl top node
kubectl describe nodes
kubectl describe service/msparametros
kubectl get pods -o wide



kubectl scale deployment msparametros --replicas=10
kubectl autoscale deployment msparametros --min=1 --max=10 --cpu-percent=5


====
kubectl get pods --selector=name=nginx,type=frontend
kubectl get events
kubectl get nodes --show-labels

====

https://coreos.com/blog/monitoring-kubernetes-with-prometheus.html

kubectl expose deployment/prometheus --type=LoadBalancer --name=prometheus



=========
az storage account create -n storageacs -g rgACS -l westus --sku Premium_LRS --kind Storage

az storage account list --resource-group rgACS

az storage account delete --name storageacs --resource-group rgACS --yes

DefaultEndpointsProtocol=https;AccountName=storageacs;AccountKey=j2AOZpLjBr66njjSselmBZj9qkFgOMCNIMVy7Yc7k91fcAa2yD5XJyYKQniJC8l7KEQ2DAIxAQpEBSlj2Hk2uQ==;EndpointSuffix=core.windows.net

scp azureuser@<master-dns-name>:.kube/config $HOME/.kube/config

//storageacs.file.core.windows.net/k8stest
> sudo mount -t cifs //storageacs.file.core.windows.net/k8stest [punto de montaje] -o vers=3.0,username=storageacs,password=j2AOZpLjBr66njjSselmBZj9qkFgOMCNIMVy7Yc7k91fcAa2yD5XJyYKQniJC8l7KEQ2DAIxAQpEBSlj2Hk2uQ==,dir_mode=0777,file_mode=0777

=====
kubectl create -f ./azure-secret.yaml 



====
sudo apt-get install cifs-utils
sudo apt-get install -y nfs-common

https://github.com/Azure/acs-engine/issues/184

ssh -i {PATH-TO-YOUR-PRIVATE-KEY} {USER}@{AGENT-NODE-NAME}
ssh -i ~/.ssh/id_rsa azureuser@dnsacs.eastus.cloudapp.azure.com
ssh -i ~/.ssh/id_rsa azureuser@104.41.147.96
ssh -i ~/.ssh/id_rsa azureuser@10.240.0.4
scp ~/.ssh/id_rsa azureuser@104.41.147.96:/home/azureuser

kubectl exec -it jenkins-4214599064-sdllt -- /bin/bash

===
https://github.com/GoogleCloudPlatform/continuous-deployment-on-kubernetes/blob/master/jenkins/k8s/jenkins.yaml



apiVersion: v1
kind: Pod
metadata:
 name: jenkins
spec:
 containers:
  - image: jenkins
    name: azure
    volumeMounts:
      - name: azure
        mountPath: /var/jenkins_home
 volumes:
      - name: azure
        azureFile:
          secretName: azure-secret
          shareName: k8stest
          readOnly: false

===

docker tag jenkinsms rcapi.azurecr.io/poc/jenkinsms:1.0
kubectl exec -it jenkins-1470477643-qqk8x -- /bin/bash

