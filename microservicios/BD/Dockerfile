FROM postgres:alpine
ENV POSTGRES_DB userdb
COPY userdb.sql /docker-entrypoint-initdb.d/
EXPOSE 5432:5432
