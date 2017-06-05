FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Manuel Zegarra "mzegarra@gmail.com"
ENV REFRESHED_AT 2017-05-24 22:01
ADD ./net.msonic.pedidos.rest.web-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -Xmx2048m -Xss256k $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
