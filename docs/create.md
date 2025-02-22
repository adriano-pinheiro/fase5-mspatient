### Criar um novo paciente

Recebe os dados para criação de um novo paciente

- **Método:** `POST`
- **Ponto de acesso:** `api/v1/patient`
- **Código HTTP:** `201 CREATED`

#### Parâmetro
| **Campo**  | **Tipo**       | **Descrição**              | **Requerido** | **Enum** |
|------------|----------------|----------------------------|---------------|----------|
| id         | String         | Identificador único        | Sim           |          |
| name       | String         | Nome do paciente           | Sim           |          |
| cpf        | String         | CPF do paciente            | Sim           |          |
| rne        | String         | RNE do paciente            | Não           |          |
| birthDate  | LocalDate      | Data de nascimento         | Não           |          |
| email      | String         | E-mail do paciente         | Não           |          |
| phone      | String         | Telefone do paciente       | Não           |          |
| addresses  | List Object    | Lista de endereços         | Não           |          |



#### Parâmetro Address
| **Campo**      | **Tipo**  | **Descrição**      | **Requerido** | **Enum** |
|----------------|-----------|--------------------|---------------|----------|
| street         | String    | Nome da rua        | Não           |          |
| number         | String    | Número do endereço | Não           |          |
| complement     | String    | Complemento        | Não           |          |
| neighborhood   | String    | Bairro             | Não           |          |
| city           | String    | Cidade             | Não           |          |
| state          | String    | Estado             | Não           |          |
| zipCode        | String    | CEP                | Não           |          |


```shell
curl --location 'http://localhost:8094/api/v1/patient' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Edgard Alencar",
    "cpf": "04782771053",
    "rne": null,
    "birthDate": "1989-10-01",
    "email": "teste@teste.com.br",
    "phone": "(11)97979-7979",
    "addresses": [
        {
            "street": "Rua Lazaro Silva",
            "number": "233",
            "complement": "10A",
            "neighborhood": "City Bussocaba",
            "city": "Osasco",
            "state": "SP",
            "zipCode": "06641470"
        }
    ]
}'
```
    POST  api/v1/patient

#### Exemplo de resposta de sucesso

```json
{
  "id": "67b9247fb9f06e17605df7a9",
  "name": "Edgard Alencar",
  "cpf": "04782771053",
  "rne": null,
  "birthDate": "1989-10-01",
  "email": "teste@teste.com.br",
  "phone": "(11)97979-7979",
  "addresses": [
    {
      "street": "Rua Lazaro Silva",
      "number": "233",
      "complement": "10A",
      "neighborhood": "City Bussocaba",
      "city": "Osasco",
      "state": "SP",
      "zipCode": "06641470"
    }
  ]
}
```

[< Voltar para o índice](../README.md)
