# LIME Web

Projeto para a matéria de Programação Orientada a Objetos, curso ADS da Fatec Rubens Lara.

## Objetivo

Criar uma aplicação web utilizando Spring + Thymeleaf + Postgresql.

## Sobre

[LIME](https://lime-web.onrender.com/) é um sistema de avaliações e listagem de livros e filmes em que os usuários podem incluir, editar ou remover livros e filmes e também incluir ou deletar comentários.

### Tecnologias

- Spring Web
- Thymeleaf
- Jdbc
- Postgresql
- HTMX

## Setup

Para rodar a aplicação localmente primeiro é necessário ter o postgresql rodando e com o banco de dados criado. Feito isso, configure o arquivo `.env` com as configurações do BD.

```bash
# Gerar arquivo .env a partir do template
cp .env.example .env

# Em seguida, o arquivo .env deverá ser editado com as configurações do banco de dados
# Exemplo:
# DB_NAME=lime
# DB_HOST=localhost
# DB_USER=postgres
# DB_PASSWD=root
```

Inicializar o servidor, usando docker:

```bash
# faz o build da imagem docker e inicia o servidor
docker compose up --build -d

# ou caso a imagem já exista
docker compose up -d
```

