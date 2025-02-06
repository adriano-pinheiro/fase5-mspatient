### Atualizar o paciente pelo ID

Atualiza um paciente de acordo com o ID informado

- **Método:** `UPDATE`
- **Ponto de acesso:** `api/v1/pacient`
- **Código HTTP:** `200 OK`

#### Parâmetro

```shell
curl --location --request PUT 'http://localhost:8094/api/v1/patient/67a4bcfc4f9c890349a1bd35' \
--header 'Content-Type: application/json' \
--data-raw '{  
  "name": "Edgard Alencar",
  "cpf": "123.456.789-99",
  "rne": null,
  "birthDate": "1989-01-01",
  "email": "edgard.alecard@email.com",
  "phone": "(11) 99999-9999",
  "addresses": [
    {
      "street": "111",
      "number": "222",
      "complement": "333",
      "neighborhood": "444",
      "city": "555",
      "state": "666",
      "zipCode": "777"
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
    PUT  api/v1/patient/{Id}

[< Voltar para o índice](../README.md)
