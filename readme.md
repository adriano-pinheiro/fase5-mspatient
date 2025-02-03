# MicroServiço - Gestão de Pacientes

Micro serviço responsável pela gestão de pacientes

- Controle de Pacientes

## Stack

- Java 17
- Spring Boot
- Docker
- Mongodb

![Stack](docs/img/stack.png)

## Iniciar ambiente dev/local

Inicia containers e configura aplicação. Url de acesso: http://localhost:8094/api/v1

```shell
make dev/local
```
ou
``` shell
docker-compose up -d 
```

## Documentação API

- [Criar um Envio de Pedido](docs/create.md)
- [Pesquisar Listar Envios](docs/shipping.md)
- [Processar Envio](docs/process.md)
- [Finalizar Envio](docs/finished.md)
- [Pesquisar Listar Rastreios](docs/tracking.md)
- [Listar Transportadoras](docs/carriers.md)

### Swagger
http://localhost:8094/swagger-ui/index.html