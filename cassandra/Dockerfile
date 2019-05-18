FROM cassandra:latest
EXPOSE 9042
COPY ./docker-entrypoint.sh ./docker-entrypoint.sh
RUN chmod a+x docker-entrypoint.sh
COPY initial-seed.cql docker-entrypoint-initdb.d/initial-seed.cql
