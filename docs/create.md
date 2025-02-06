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
    "cpf": "123.456.789-99",
    "rne": null,
    "birthDate": "1990-05-15",
    "email": "edgard.alencar@email.com",
    "phone": "(11) 91234-5678",
    "addresses": [
        {
            "street": "Rua das Flores",
            "number": "123",
            "complement": "Apto 101",
            "neighborhood": "Centro",
            "city": "São Paulo",
            "state": "SP",
            "zipCode": "01000-000"
        },
        {
            "street": "Avenida Paulista",
            "number": "456",
            "complement": "Sala 202",
            "neighborhood": "Bela Vista",
            "city": "São Paulo",
            "state": "SP",
            "zipCode": "01310-000"
        }
    ]
}'
```
    POST  api/v1/patient

#### Exemplo de resposta de sucesso

```json
{
  "id": "67a4d22fba5bdd0616c3f22e",
  "name": "Edgard Alencar",
  "cpf": "123.456.789-99",
  "rne": null,
  "birthDate": "1990-05-15",
  "email": "edgard.alencar@email.com",
  "phone": "(11) 91234-5678",
  "addresses": [
    {
      "street": "Rua das Flores",
      "number": "123",
      "complement": "Apto 101",
      "neighborhood": "Centro",
      "city": "São Paulo",
      "state": "SP",
      "zipCode": "01000-000"
    },
    {
      "street": "Avenida Paulista",
      "number": "456",
      "complement": "Sala 202",
      "neighborhood": "Bela Vista",
      "city": "São Paulo",
      "state": "SP",
      "zipCode": "01310-000"
    }
  ]
}
```

[< Voltar para o índice](../README.md)
