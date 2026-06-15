# Movvia - Sistema de Gerenciamento de Veículos

O **Movvia** é uma aplicação web completa desenvolvida como projeto acadêmico para a disciplina de Programação. O objetivo do sistema é fornecer um painel administrativo ágil, moderno e responsivo para o controle, cadastro e manutenção de frotas de veículos corporativos, simulando cenários reais de ambientes de produção.

---

## Tecnologias Utilizadas

A solução foi estruturada utilizando uma arquitetura robusta e moderna dividida em camadas:

* **Backend:** Java 17 com **Spring Boot** (Spring Data JPA, Web, Thymeleaf).
* **Frontend:** HTML5, CSS3, **Bootstrap 5** (Layout responsivo e validações customizadas) e Bootstrap Icons.
* **Banco de Dados:** **PostgreSQL** rodando de forma isolada.
* **Infraestrutura:** **Docker** e **Docker Compose** para orquestração e conteinerização de todo o ecossistema.

---

## Funcionalidades do Ecossistema (CRUD Completo)

A aplicação cumpre com todos os requisitos operacionais de persistência de dados de forma segura:
1. **Cadastrar Veículos:** Inclusão de novos registros no banco através de um formulário inteligente com validação nativa client-side (regras estritas para caracteres em marcas/cores e numéricos para anos).
2. **Consultar Frota:** Listagem dinâmica em tempo real dos veículos salvos no PostgreSQL utilizando tabelas estilizadas com efeito hover.
3. **Atualizar Registros:** Mecanismo de edição rápida que reaproveita o formulário de cadastro enviando IDs ocultos via Thymeleaf para mesclagem de dados (`merge`).
4. **Excluir Registros:** Remoção física e instantânea de registros diretamente pelos gatilhos de ação da tabela.

---

## Infraestrutura Docker & Redes

A arquitetura do projeto foi totalmente "dockerizada" para garantir que a aplicação rode em qualquer máquina sem a necessidade de instalar o Java ou o PostgreSQL localmente.

* **Isolamento de Containers:** O backend Java e o banco PostgreSQL rodam em containers separados utilizando imagens otimizadas.
* **Docker Network:** Foi criada uma rede virtual explícita do tipo `bridge` chamada `frota-net`. Os containers comunicam-se de forma segura através de resolução de nomes de serviços internos, eliminando a dependência de IPs dinâmicos.

---

## Como Executar a Aplicação

### Pré-requisitos
* Possuir o **Docker** e o **Docker Compose** instalados na máquina.
* *Opcional:* Maven instalado (caso deseje compilar o projeto manualmente fora do Docker).

### 1. Gerando o .jar do projeto (Opcional)
Se precisar compilar o projeto manualmente antes de criar a imagem, execute na raiz do projeto:
```bash
mvn clean package -DskipTests

### 2. Comandos Docker (Pelo Terminal)
Para subir a infraestrutura completa (Banco de Dados + Aplicação Spring Boot), abra o terminal na pasta raiz e execute:
Bash
docker compose up --build -d

Para derrubar os containers e parar a aplicação:
Bash
docker compose down

### 3. Como reiniciar após alterações no código (Atalho Windows)
Para facilitar o desenvolvimento, reconstrução e execução limpa do ambiente sem cache físico do Docker, desenvolvi um script automatizado na raiz. Basta dar dois cliques ou rodar no terminal:
Bash
.\recriar.bat

Acesse no Navegador
Com os containers rodando, a aplicação estará disponível na sua máquina local. Acesse o link abaixo no seu navegador:

👉 http://localhost:8080/web/produtos

Estrutura do Projeto e Containers
O ambiente é orquestrado pelo docker-compose.yml contendo:

Container db: Roda a imagem oficial do postgres:15, mapeado na porta 5432 com volumes persistentes para não perder os dados dos veículos.

Container app: Roda o backend Spring Boot, construído através do meu Dockerfile, exposto na porta 8080 e dependente do container de banco de dados para iniciar.

Ambas as aplicações se comunicam através da rede interna estática frota-net.

👨‍💻 Desenvolvedor
Projeto desenvolvido individualmente para a disciplina de Programação — UVA Tijuca (Engenharia de Software):

Pablo Tiago Machado Vaz Dias (Matrícula: 1250119843)

© 2026 Movvia - Painel Administrativo de Frotas