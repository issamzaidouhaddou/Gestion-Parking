pipeline
{
agent any
      tools{
      maven 'apache-maven-3.6.3-bin'
      jdk 'jdk-11.0.10'
      }
      stages{
              stage('build')
              {
                  steps{
                  bat 'mvn compiler:compile'
                  }
                  post
                  {
                      sucesses{
                      echo 'projet compilé avec succès'
                              }
                              
                      failure{
                      echo 'Erreur de compilation'
                      }
                  }
             }
      }
}      
