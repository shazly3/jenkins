#build_artifact
def buildJar() {
    echo "building the artifact..."
    sh 'mvn package'
} 

#build_dockerimage
def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh 'docker build -t shazly3/jenkins:APP-2.0 .'
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh 'docker push shazly3/jenkins:latest'
    }
} 

def deployApp() {
    echo 'deploying the app...'
} 

return this