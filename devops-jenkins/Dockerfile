FROM jenkinsci/blueocean:latest
 
LABEL maintainer="Tomaž Kramberger <info@tomazkramberger.me>"

ENV JENKINS_OPTS "--prefix=/jenkins"
ENV JAVA_OPTS "-Djenkins.install.runSetupWizard=false -Dpermissive-script-security.enabled=true"
ENV FILEBEAT_VERSION 5.3.0

ENV MAVEN_VERSION 3.5.2
ENV MAVEN_HOME /usr/share/maven
 
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
 
USER root
 
RUN mkdir /etc/filebeat && \
  apk add --no-cache wget && \
  wget -q https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-${FILEBEAT_VERSION}-linux-x86_64.tar.gz --no-check-certificate -O filebeat.tar.gz && \
  tar xzvf filebeat.tar.gz && \
  cp filebeat-*/filebeat /usr/local/bin && \
  rm -rf filebeat*

RUN apk -v --no-cache add \
        python \
        py-pip \
        groff \
        less \
        mailcap \
        gcc \
        postgresql-dev \
        python-dev \
        musl-dev \
        docker \
        libffi-dev \
        make \
        openssh-keygen \
        && \
    rm /var/cache/apk/*

RUN pip install --upgrade \
    awscli==1.14.5 \
    s3cmd==2.0.1 \
    python-magic \
    boto3 \
    psycopg2 \
    docker-compose

RUN cd /usr/share \
 && wget -q http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz -O - | tar xzf - \
 && mv /usr/share/apache-maven-$MAVEN_VERSION $MAVEN_HOME \
 && ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

COPY filebeat.yml /etc/filebeat/
COPY ./groovy /var/jenkins_home/init.groovy.d/

RUN mkdir -p /home/root/.ssh && \
    chmod 0700 /home/root/.ssh && \ 
    ssh-keygen -q -t rsa -N '' -f /home/root/.ssh/id_rsa

COPY ["entrypoint.sh", "/"]

RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/bin/bash","-c","./entrypoint.sh"]