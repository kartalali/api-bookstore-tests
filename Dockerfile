FROM jenkins/jenkins:lts

USER root
RUN apt-get update && apt-get install -y maven git
RUN jenkins-plugin-cli --plugins \
  configuration-as-code \
  workflow-aggregator \
  job-dsl \
  junit

USER jenkins
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"