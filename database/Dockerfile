FROM postgres:9.6.2
MAINTAINER Manuel Zegarra "mzegarra@gmail.com"
ENV REFRESHED_AT 2017-04-24 22:50
RUN localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.utf8
ENV POSTGRES_USER docker
ENV POSTGRES_PASSWORD docker
ENV POSTGRES_DB docker
ADD 10_create_tables.sql /docker-entrypoint-initdb.d/
ADD 20_load_data.sql /docker-entrypoint-initdb.d/
EXPOSE 5432