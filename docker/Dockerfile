FROM alpine:latest

MAINTAINER Mark V (mveeprojects)

WORKDIR /var/mylist

RUN apk add --update curl && \
    apk add --no-cache docker-cli

COPY initCouchbaseDockerCompose.sh initCouchbase.sh

ENTRYPOINT ./initCouchbase.sh
