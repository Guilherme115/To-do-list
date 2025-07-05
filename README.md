
---

```
# 📝 To-Do List em Java Puro

Um projeto simples de To-Do List feito em **Java puro**, usando `HttpServer` nativo da JDK e integração com **MySQL** para persistência de dados. Nada de frameworks pesados como Spring — aqui é código raiz, na marra.

---

## 🚀 Funcionalidades

- ✅ Criar tarefas  
- 📋 Listar todas as tarefas  
- ✔️ Marcar tarefas como concluídas  
- ❌ Deletar tarefas  
- 💾 Persistência com banco de dados MySQL  

---

## 🧰 Tecnologias utilizadas

- **Java 17+**
- **Java HTTP Server** (`com.sun.net.httpserver.HttpServer`)
- **MySQL**
- **JDBC** (Java Database Connectivity)
- Nenhum framework (sem Spring Boot!)

---

## 📂 Estrutura básica do projeto

```

src/
├── Main.java
├── controllers/
│   └── TaskController.java
├── models/
│   └── Task.java
├── services/
│   └── TaskService.java
├── database/
│   └── DatabaseConnection.java

````

---

## 🧪 Como rodar

### 1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/todo-java-http-mysql.git
````

### 2. Configure o banco de dados MySQL:

Crie o banco:

```sql
CREATE DATABASE todo_db;
```

Crie a tabela:

```sql
CREATE TABLE tasks (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  completed BOOLEAN DEFAULT FALSE
);
```

### 3. Configure o acesso ao banco

Altere o arquivo `DatabaseConnection.java` com seu usuário e senha do MySQL.

### 4. Compile o projeto:

```bash
javac -d out -cp "lib/mysql-connector-java-X.X.X.jar" src/**/*.java
```

### 5. Rode o servidor:

```bash
java -cp "out:lib/mysql-connector-java-X.X.X.jar" Main
```

A API estará rodando em:
[http://localhost:8080](http://localhost:8080)

---

## 📬 Endpoints disponíveis

| Método | Endpoint    | Descrição                   |
| ------ | ----------- | --------------------------- |
| GET    | /tasks      | Lista todas as tarefas      |
| POST   | /tasks      | Cria uma nova tarefa        |
| PUT    | /tasks/{id} | Marca tarefa como concluída |
| DELETE | /tasks/{id} | Deleta uma tarefa           |

---

## 🧾 Exemplo de JSON

### Criar tarefa

```json
{
  "title": "Estudar Java",
  "description": "Ver aula sobre JDBC e MySQL"
}
```

### Resposta esperada

```json
{
  "id": 1,
  "title": "Estudar Java",
  "description": "Ver aula sobre JDBC e MySQL",
  "completed": false
}
```

---

## 📌 Observações

* feito pra estudar HTTP, rotas e lógica de negócio no modo raiz.

---

## 🤘 Autor

Feit por **Guilherme**
[GitHub](https://github.com/Guilherme115)



```

