# Java PostgreSQL CRUD Application

Um aplicativo de console simples em Java que implementa operações CRUD (Create, Read, Update, Delete) com banco de dados PostgreSQL.

## 📋 Descrição

Esta aplicação fornece uma interface de linha de comando para gerenciar usuários em um banco de dados PostgreSQL. É uma implementação completa de um sistema CRUD com uma arquitetura DAO (Data Access Object).

## 🚀 Funcionalidades

- ✅ **Criar** novos usuários
- 📖 **Listar** todos os usuários
- 🔍 **Buscar** usuário por ID
- 🔄 **Atualizar** informações de usuários existentes
- ❌ **Excluir** usuários do banco de dados

## 🛠️ Tecnologias Utilizadas

- Java 11+
- PostgreSQL
- JDBC (Java Database Connectivity)
- Maven ou Gradle (suporte para ambos)

## 📦 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── crud/
│   │               ├── model/
│   │               │   └── User.java
│   │               ├── dao/
│   │               │   └── UserDAO.java
│   │               ├── util/
│   │               │   └── DatabaseConnection.java
│   │               └── App.java
│   └── resources/
│       └── database.properties
```

## 🔧 Pré-requisitos

- Java JDK 11 ou superior
- PostgreSQL instalado e rodando
- Maven ou Gradle (dependendo da versão que você escolher usar)

## ⚙️ Configuração

1. Clone este repositório
2. Configure o PostgreSQL:
   - Crie um usuário ou use um existente
   - Atualize o arquivo `database.properties` com suas credenciais:
     ```
     driver=org.postgresql.Driver
     url=jdbc:postgresql://localhost:5432/seu_banco
     user=seu_usuario
     password=sua_senha
     ```

## 🚀 Execução

### Com Maven

```bash
mvn clean package
java -jar target/java-postgres-crud-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### Com Gradle

```bash
./gradlew build
./gradlew run

# Ou para criar um JAR executável
./gradlew fatJar
java -jar build/libs/java-postgres-crud-with-dependencies-1.0-SNAPSHOT.jar
```

## 💻 Uso

Ao iniciar o aplicativo, você verá um menu com as seguintes opções:

```
====== USER MANAGEMENT SYSTEM ======
1. Create a new user
2. List all users
3. Find user by ID
4. Update a user
5. Delete a user
0. Exit
Choose an option:
```

Siga as instruções na tela para interagir com o aplicativo.

## 📝 Notas

- A tabela de usuários será criada automaticamente se não existir
- Os usuários são identificados por um ID gerado automaticamente (SERIAL no PostgreSQL)
- Todos os erros são tratados e exibidos no console

## 📄 Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE.md](LICENSE.md) para detalhes.

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

## 📞 Contato

- Coloque aqui suas informações de contato ou links para redes sociais.