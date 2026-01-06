📊 Sistema de Cadastro de Pessoas com Cálculo de Peso Ideal
Um sistema completo CRUD desenvolvido com Java Spring Boot no backend e Angular no frontend, permitindo o gerenciamento de pessoas com cálculo automático do peso ideal.

🚀 Como Executar o Projeto
Pré-requisitos
Java 21 ou superior

Node.js 18+ e npm 9+

MySQL 8.0+

Maven 3.8+

Angular CLI 16+

1. Configuração do Banco de Dados
sql
-- Crie o banco de dados
CREATE DATABASE gth CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Ou use o MySQL Workbench/phpMyAdmin para criar o banco 'gth'
2. Backend (Spring Boot)
bash
# Clone o repositório
git clone [url-do-repositorio]
cd [nome-do-projeto]

# Navegue até a pasta do backend
cd backend

# Configure o application.properties se necessário
# Edite o arquivo src/main/resources/application.properties
# Ajuste usuário e senha do MySQL

# Compile e execute
mvn clean install
mvn spring-boot:run

# Ou execute via IDE
# O backend estará disponível em: http://localhost:8080
3. Frontend (Angular)
bash
# Navegue até a pasta do frontend
cd frontend

# Instale as dependências
npm install

# Execute o servidor de desenvolvimento
ng serve

# Ou com um port específico (opcional)
ng serve --port 4200

# O frontend estará disponível em: http://localhost:4200

🔧 Configurações Importantes
Backend (application.properties)
properties
spring.datasource.url=jdbc:mysql://localhost:3306/gth
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
Frontend (api.service.ts)
typescript
private baseUrl = 'http://localhost:8080/api/pessoas';
📋 Funcionalidades Implementadas
✅ CRUD Completo
Criar nova pessoa com validações

Ler lista de pessoas com paginação

Atualizar dados existentes

Excluir registros com confirmação

✅ Sistema de Pesquisa
Busca por nome (parcial)

Busca por CPF (com ou sem formatação)

Filtragem em tempo real

Estatísticas de resultados

✅ Cálculo de Peso Ideal
Fórmula específica por gênero:

Homens: (72.7 × altura) - 58

Mulheres: (62.1 × altura) - 44.7

Modal com resultados detalhados

Interpretação automática (acima/abaixo/ideal)

✅ Validações
CPF único de 11 dígitos

Sexo apenas 'M' ou 'F'

Datas válidas

Campos obrigatórios

Altura/peso positivo

✅ Interface Moderna
Design responsivo (mobile/desktop)

Feedback visual imediato

Animações suaves

Modais customizados

Indicadores de carregamento

🧪 Testes
Backend
bash
# Executar todos os testes
mvn test

# Executar testes específicos
mvn test -Dtest=PessoaServiceTest
mvn test -Dtest=PessoaControllerIntegrationTest

# Relatório de cobertura (se configurado)
mvn jacoco:report
Frontend
bash

🛠️ Tecnologias Utilizadas
Backend
Java 21

Spring Boot 4.0.1

Spring Data JPA

MySQL Connector

Maven

JUnit 5 / Mockito

H2 Database (testes)

Frontend
Angular 16+

TypeScript

Bootstrap 5

RxJS

HTML5 / CSS3

Ferramentas
Visual Studio Code / IntelliJ IDEA

Postman / Insomnia

Git

DBeaver
