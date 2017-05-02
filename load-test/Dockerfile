FROM java:8-jdk-alpine
MAINTAINER Manuel Zegarra "mzegarra@gmail.com"
ENV REFRESHED_AT 2017-04-19 22:46
WORKDIR /opt
ENV GATLING_VERSION 2.2.5
RUN mkdir -p gatling

# install gatling
RUN apk add --update wget && \
  mkdir -p /tmp/downloads && \
  wget -q -O /tmp/downloads/gatling-$GATLING_VERSION.zip \
  https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/$GATLING_VERSION/gatling-charts-highcharts-bundle-$GATLING_VERSION-bundle.zip && \
  mkdir -p /tmp/archive && cd /tmp/archive && \
  unzip /tmp/downloads/gatling-$GATLING_VERSION.zip && \
  mv /tmp/archive/gatling-charts-highcharts-bundle-$GATLING_VERSION/* /opt/gatling/ && \
  rm -rf /tmp/*


  # change context to gatling directory
WORKDIR  /opt/gatling

COPY ./basic/RestSimulation.scala /opt/gatling/user-files/simulations/

# set directories below to be mountable from host
VOLUME ["/opt/gatling/conf", "/opt/gatling/results", "/opt/gatling/user-files"]

# set environment variables
ENV PATH /opt/gatling/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
ENV GATLING_HOME /opt/gatling

ENTRYPOINT ["gatling.sh"]

