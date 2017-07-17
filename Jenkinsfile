
def TAG = "${env.JOB_NAME}.${env.BUILD_NUMBER}".replace('-', '_').replace('/', '_').replace('.', '')
def TAG_IMAGE="";

 echo("$TAG")
//sh "docker info"
//sh "kubectl version"


podTemplate(label: 'maven', containers: [
  containerTemplate(name: 'maven', image: 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: 'cat'),
  containerTemplate(name: 'azure', image: 'azuresdk/azure-cli-python:2.0.10', ttyEnabled: true, command: 'cat'),
  containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true)
  
  ],
  volumes: [
      hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock'),
      hostPathVolume(hostPath: '/root/.kube', mountPath: '/root/.kube')
      
     ]
  
  ){

     
    

  node('maven') {
      
    stage('Build a Maven project') {


        stage('Get Source') {
            sh 'git config --global user.email "mzegarra@gmail.com"'
            sh 'git config --global user.name "Manuel"'
            git 'https://github.com/mzegarras/MicroserviceK8S.git'
            echo("$TAG")
        }

        stage('Add git tag') {
            sh "git tag -a ${TAG} -m 'Jenkins'"
        }
      
     
      container('maven') {
          
          stage('Build') {

            sh """
                    
                    mvn -f ./msservice/source/net.msonic.parametros.rest -B clean package
                    mv ./msservice/source/net.msonic.parametros.rest/net.msonic.pedidos.rest.web/target/net.msonic.pedidos.rest.web-1.0-SNAPSHOT.jar ./msservice/net.msonic.pedidos.rest.web-1.0-SNAPSHOT.jar
                """
          }
      }
      
      stage('Git push') {
        sh "git add ./msservice/net.msonic.pedidos.rest.web-1.0-SNAPSHOT.jar"
        sh "git commit -m ${TAG} -m 'Jenkins'"
      }
      
         withCredentials([usernamePassword(credentialsId: 'githubId',
                     usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {

        /*withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'githubId',
                    usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {*/
                        
            sh('git push https://${USERNAME}:${PASSWORD}@github.com/mzegarras/MicroserviceK8S.git --tags')
            sh('git push https://${USERNAME}:${PASSWORD}@github.com/mzegarras/MicroserviceK8S.git')
            
        }
  
  
      container('docker') {
          
        stage('Build Container image') {

            withCredentials([usernamePassword(credentialsId: 'DOCKER_HUB',
                     usernameVariable: 'DOCKER_HUB_USER', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
         
                TAG_IMAGE = "${DOCKER_HUB_USER}/msparametros:${TAG}"
                
                stage('Build image') {
                    sh """
                        cd ./msservice
                        docker build -t ${DOCKER_HUB_USER}/msparametros:${TAG} .
                    """
                }

                stage('Push image') {
                    sh """
                        docker login -u ${DOCKER_HUB_USER} -p ${DOCKER_HUB_PASSWORD}
                        docker images
                        docker push $TAG_IMAGE
                    """
                }
            }
        }
      }
      
     
      container('azure') {
          
          stage('Azure') {

            withCredentials([usernamePassword(credentialsId: 'azure-service-principal',
                     usernameVariable: 'AZURE_PRINCIPAL_ID', passwordVariable: 'AZURE_PRINCIPAL_PASSWORD')]) {

                withCredentials([[$class: 'FileBinding', variable: 'PVT_KEY_FILE', credentialsId: 'kubernetes']]) {
                        
                        stage('Azure Login') {

                            sh """
                                mkdir ~/.ssh 
                                cd ~/.ssh
                                cat $PVT_KEY_FILE > ~/.ssh/id_rsa
                                chmod 0500 ~/.ssh/id_rsa
                                
                                az login --service-principal -u $AZURE_PRINCIPAL_ID -p $AZURE_PRINCIPAL_PASSWORD -t 'eaca764b-9802-4b9d-80ff-4c090fb2163c'
                
                                az acs kubernetes get-credentials --resource-group=rgCI2 --name=jenkinsapp
                                
                                az acs kubernetes install-cli
                                
                            """

                        }
                }

                stage('Kubernetes update') {

                        sh """
                            kubectl set image deployment/msparametros msparametros=$TAG_IMAGE --namespace=production
                        """

                    }
               
                
            }
          }
      }
      
      
      
    }
  }
}
