# aep_2026.2

## Tecnologias utilizadas

- **Java 26**: linguagem utilizada no desenvolvimento da aplicação orientada a objetos.
- **Spring Boot 4.1.1**: estrutura principal para configuração e execução da aplicação.
- **Spring Data MongoDB**: integração entre a aplicação Java e o banco de dados NoSQL.
- **MongoDB 8.0**: armazenamento das solicitações em documentos na coleção `solicitacoes`.
- **Maven Wrapper**: gerenciamento das dependências e execução do projeto.
- **Docker e Docker Compose**: criação e inicialização padronizada do ambiente MongoDB.
- **Jakarta Bean Validation**: validação dos dados obrigatórios das solicitações.
- **Lombok**: suporte à geração de código durante a compilação.

## Banco de dados MongoDB

A aplicação utiliza o banco NoSQL MongoDB. Por padrão, a conexão é realizada em
`mongodb://localhost:27017/observacao`, e as solicitações são armazenadas na coleção
`solicitacoes`.

### Iniciar o banco de dados

Com o Docker Desktop em execução, abra um terminal na pasta do projeto e execute:

```powershell
docker compose up -d
```

Verifique se o contêiner está saudável:

```powershell
docker compose ps
docker exec observacao-mongodb mongosh --quiet --eval "db.adminCommand('ping')"
```

O comando de verificação deve retornar `{ ok: 1 }`.

### Configurar outra conexão

Para utilizar outro servidor MongoDB, defina a variável de ambiente `MONGODB_URI`
antes de iniciar a aplicação:

```powershell
$env:MONGODB_URI="mongodb://localhost:27017/observacao"
```

### Consultar as solicitações

Abra o terminal do MongoDB dentro do contêiner:

```powershell
docker exec -it observacao-mongodb mongosh
```

Em seguida, selecione o banco e consulte a coleção:

```javascript
use observacao
db.solicitacoes.find()
```

### Encerrar o banco de dados

```powershell
docker compose down
```

Os dados permanecem armazenados no volume `mongodb_data` e estarão disponíveis na
próxima inicialização.
