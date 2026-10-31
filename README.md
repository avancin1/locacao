# Sistema de Locação de Veículos

## Sobre o projeto

Este projeto foi desenvolvido para a disciplina de Banco de Dados da FAESA. O objetivo é criar um sistema para auxiliar no gerenciamento de uma locadora de veículos.

Por meio do sistema, é possível cadastrar clientes, veículos e locações, consultar informações, atualizar registros, remover dados e gerar relatórios.

O programa foi desenvolvido em Java e utiliza o PostgreSQL para armazenar as informações.

## Tecnologias utilizadas

- Java 17
- PostgreSQL
- JDBC
- Maven
- Eclipse IDE
- SQL Power Architect

## Banco de dados

O banco de dados utilizado no projeto possui três tabelas principais:

- **Clientes:** armazena informações como nome, CPF, telefone e CNH.
- **Veículos:** armazena informações como placa, modelo, marca, ano e valor da diária.
- **Locações:** registra as locações realizadas, relacionando os clientes aos veículos e armazenando as datas e os valores.

O diagrama relacional está disponível na pasta `diagrama`.

## Funcionalidades

O sistema possui um menu principal com as seguintes opções:

- **Relatórios:** apresenta informações sobre as locações.
- **Inserir registros:** permite cadastrar clientes, veículos e locações.
- **Remover registros:** permite excluir registros existentes, respeitando os relacionamentos do banco de dados.
- **Atualizar registros:** permite alterar informações cadastradas.
- **Sair:** encerra o programa.

Ao iniciar, o sistema também apresenta a quantidade de registros existentes em cada tabela.

## Pré-requisitos

Para executar o projeto, é necessário ter instalado:

- Java JDK 17 ou superior compatível.
- PostgreSQL.
- Maven.
- Git.

## Configuração do banco de dados

1. Instale as dependências e inicie o PostgreSQL (Ubuntu/Debian):

```bash
sudo apt update
sudo apt install -y git openjdk-17-jdk maven postgresql
sudo systemctl start postgresql
```

2. Defina uma senha para o usuário `postgres` e crie o banco `locadora`:

```bash
sudo -u postgres psql -c "ALTER USER postgres PASSWORD 'sua_senha';"
sudo -u postgres psql -c "CREATE DATABASE locadora;"
```

3. Baixe o projeto (veja "Como executar no Linux", passo 1) e, dentro da pasta do projeto, crie as tabelas:

```bash
sudo -u postgres psql -d locadora < sql/criacao_tabelas.sql
```

   Opcionalmente, insira os dados de exemplo:

```bash
sudo -u postgres psql -d locadora < sql/dados_exemplo.sql
```

   > Atenção: `criacao_tabelas.sql` apaga e recria as tabelas. Executar de novo zera os dados.

4. Configure os dados de acesso ao banco no arquivo `.env`, como mostrado a seguir.

### Configuração do arquivo .env

Na pasta principal do projeto existe um arquivo chamado `.env.example`, que serve como modelo.

Faça uma cópia desse arquivo e renomeie a cópia para `.env`.

Abra o arquivo e informe os dados da sua conexão:

```env
DB_URL=jdbc:postgresql://localhost:5432/locadora
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
```

Substitua os valores de usuário e senha pelos dados configurados no seu PostgreSQL (se seguiu os comandos acima, o usuário é `postgres` e a senha é a que você definiu no `ALTER USER`).

**Importante:** o arquivo `.env` contém dados de acesso e não deve ser enviado ao GitHub. Cada pessoa que utilizar o projeto deverá configurar seu próprio arquivo.

## Como executar no Linux

### 1. Baixar o projeto

Abra o terminal e execute:

```bash
git clone URL_DO_REPOSITORIO
```

Entre na pasta do projeto:

```bash
cd NOME_DO_REPOSITORIO
```

### 2. Configurar o arquivo .env

Copie o arquivo de exemplo:

```bash
cp .env.example .env
```

Abra o arquivo:

```bash
nano .env
```

Preencha os dados de conexão com o PostgreSQL e salve as alterações.

### 3. Compilar o projeto

Na pasta principal do projeto, execute:

```bash
mvn clean package
```

### 4. Executar o sistema

Após a compilação, execute o sistema com o comando (sempre a partir da pasta principal do projeto, pois o `.env` e os relatórios em `sql/relatorios/` são lidos a partir dela):

```bash
export LC_ALL=C.UTF-8
mvn exec:java
```

Se aparecer "Não foi possível conectar ao banco de dados", confira se o PostgreSQL está ligado e se o `.env` está na pasta de onde você executou o comando.

O programa será iniciado pela classe principal `Principal`, e o menu inicial será exibido no terminal.

## Organização do projeto

O código foi dividido em pacotes para facilitar a organização e a manutenção:

- **conexion:** contém a classe responsável pela conexão com o banco de dados.
- **controller:** reúne as classes responsáveis pelas operações das entidades.
- **model:** contém as classes que representam clientes, veículos e locações.
- **reports:** contém as consultas e os métodos dos relatórios.
- **utils:** reúne classes auxiliares utilizadas no sistema.
- **sql:** armazena os scripts SQL.
- **diagrama:** contém o diagrama relacional do banco de dados.
- **interfaces:** contém as classes responsáveis pelas operações de inserção, remoção e atualização de registros.

## Vídeo de apresentação

O vídeo apresenta o funcionamento do sistema, demonstrando suas principais funcionalidades e operações.

**Link do vídeo:** https://youtu.be/43tJ_GKzdDI?feature=shared

## Integrantes

- Ana Julia Alvarenga Avancini
- Dayane Bernardo Rinaldi

## Utilização de Inteligência Artificial

Durante o desenvolvimento, foram utilizadas ferramentas de Inteligência Artificial como apoio para esclarecer dúvidas, revisar trechos de código e auxiliar na organização do projeto.

O registro das atividades em que houve utilização dessas ferramentas está disponível no arquivo `USO_IA.md`.

## Informações acadêmicas

**Instituição:** FAESA  
**Disciplina:** Banco de Dados  
**Professor:** Howard Roatti  
**Projeto:** Sistema de Locação de Veículos
