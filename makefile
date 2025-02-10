dev/local:
	docker-compose up -d
	./mvnw spring-boot:run

api/test:
	docker-compose up -d
	./mvnw clean
	./mvnw test

