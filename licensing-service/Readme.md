start docker postgress
docker run -d -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=testDB -p 5432:5432 postgres