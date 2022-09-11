#! /bin/bash
docker run -p 5432:5432 --name postgres-onevision -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=onevision postgres
