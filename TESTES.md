# Registro de Testes – Sistema de Locação de Veículos

## 1\. Objetivo

Este documento apresenta os testes realizados durante o desenvolvimento do Sistema de Locação de Veículos, com o objetivo de verificar o funcionamento das operações, a integração com o banco de dados PostgreSQL e a consistência das informações.

## 2\. Ambiente de testes

* **Linguagem:** Java 17
* **Gerenciador de dependências:** Maven
* **Banco de dados:** PostgreSQL
* **IDE utilizada:** Eclipse
* **Projeto:** Sistema de Locação de Veículos

## 3\. Testes funcionais

### 3.1. Cadastro de registros

* Cadastro de clientes.
* Cadastro de veículos.
* Cadastro de locações.
* Verificação da gravação dos dados no banco de dados.
* Verificação dos campos obrigatórios.

**Resultado:** Aprovado. As operações de cadastro foram executadas corretamente.

### 3.2. Consulta de registros

* Listagem de clientes.
* Listagem de veículos.
* Listagem de locações.
* Consulta de registros por identificador.

**Resultado:** Aprovado. As consultas e listagens apresentaram os registros corretamente.

### 3.3. Atualização de registros

* Atualização dos dados de clientes.
* Atualização dos dados de veículos.
* Atualização dos dados de locações.
* Verificação das alterações no banco de dados.

**Resultado:** Aprovado. As atualizações foram realizadas corretamente e os dados foram persistidos no banco.

### 3.4. Remoção de registros

* Remoção de clientes.
* Remoção de veículos.
* Remoção de locações.
* Verificação do tratamento de registros inexistentes.
* Verificação das restrições de integridade referencial.

**Resultado:** Aprovado. As operações de remoção funcionaram conforme esperado.

### 3.5. Relatórios

* Relatório de locações por modelo, com total de locações e duração média.
* Relatório de locações detalhadas, apresentando informações dos clientes e veículos.
* Verificação dos dados retornados pelas consultas SQL.

**Resultado:** Aprovado. Os relatórios foram gerados corretamente e apresentaram as informações esperadas.

## 4\. Testes de integração

Foram verificadas as seguintes funcionalidades:

* Estabelecimento da conexão com o banco de dados PostgreSQL.
* Execução das operações SQL.
* Persistência das alterações realizadas pelo sistema.
* Relacionamento entre clientes, veículos e locações.
* Funcionamento dos menus e integração entre as funcionalidades.

**Resultado:** Aprovado. A integração entre o sistema Java e o banco de dados funcionou corretamente.

## 

