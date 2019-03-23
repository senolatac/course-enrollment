FROM cassandra:3.11
EXPOSE 9042
COPY docker/cassandra/docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod a+x docker-entrypoint.sh
COPY initial-seed.cql docker-entrypoint-initdb.d/initial-seed.cql