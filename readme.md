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

- [Criar um Paciente](docs/create.md)
- [Pesquisar Lista de pacientes](docs/list.md)
- [Pesquisar paciente pelo ID ](docs/serchById.md)
- [Pesquisar paciente pelo CPF](docs/serchByCpf.md)
- [Pesquisar paciente pelo RNE](docs/serchByRne.md)
- [Deletar um paciente pelo ID](docs/delete.md)
- [Atualizar um paciente pelo ID](docs/update.md)

### Swagger
http://localhost:8094/swagger-ui/index.html