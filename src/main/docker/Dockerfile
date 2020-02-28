FROM 1000kit/liquibase:3.8.7 as liquibase

FROM registry.access.redhat.com/ubi8/ubi-minimal
WORKDIR /work/

# copy liquibase binary
COPY --from=liquibase liquibase /work/liquibase

# copy liquibase changelog
COPY target/classes/db /work/db

# copy application
COPY target/*-runner /work/application

RUN chmod 775 /work
EXPOSE 8080

# execute the liquibase changes and start the application
CMD ./liquibase --changeLogFile=db/changeLog.xml \
     --url=${QUARKUS_DATASOURCE_URL} \
     --username=${QUARKUS_DATASOURCE_USERNAME} \
     --password=${QUARKUS_DATASOURCE_PASSWORD} \
     update && \
    ./application -Dquarkus.http.host=0.0.0.0
