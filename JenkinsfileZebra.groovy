pipeline {
    agent any
    tools {
        jdk 'jdk8'
        maven 'maven3'
    }
    
    stages {
        stage('download_stash_code') {
            steps {
                checkout scm
            }
        }

 /*       stage('clean') {
            steps {
				sh """(
				cd src\gateway\HelloWorld
				ls
                mvn clean
				
				)"""
				
            }
        }

*/
        stage('Deploy to Test') {
            steps { 
     

                                    sh """(
									 cd src/gateway/HelloWorld
                                     mvn install -X -Ptest -Dusername=riddhi_thacker@yahoo.com -Dpassword=Ridz94_@
                                    )"""
                                
                                

            }
        }
 stage('Mock url') {
            steps { 
     

                                    sh """(
									
									 cd src/gateway/HelloWorld
									 sleep 20
									 APP_STATUSCODE=\$(curl -X OPTIONS --silent --output /dev/stderr --write-out "%{http_code}" http://riddhithacker-eval-test.apigee.net/weatherapi/)
									 if [ "\$APP_STATUSCODE" -eq 200 ]
									 then
									  echo "API is up and running"
									 else
									 echo "API validation failed"
									 exit 1
									 fi
                                    )"""
                                
                                

            }
        } 
  stage('Deploy to Mock') {
            steps { 
     

                                    sh """(
									
									 cd src/gateway/HelloWorld
									 
									 mvn install -X -Pprod -Dusername=riddhi_thacker@yahoo.com -Dpassword=Ridz94_@
									 
                                    )"""
                                
                                

            }
        }
	stage('Deploy to sandbox') {
            steps { 
     

                                    sh """(
									
									 cd src/gateway/HelloWorld
									 
									 mvn install -X -Pprod -Dusername=riddhi_thacker@yahoo.com -Dpassword=Ridz94_@
									 
                                    )"""
                                
                                

            }
        }
        
    }
    
}

