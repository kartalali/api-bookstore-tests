FROM jenkins/jenkins:lts

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

RUN jenkins-plugin-cli --plugins \
  configuration-as-code \
  git \
  workflow-aggregator