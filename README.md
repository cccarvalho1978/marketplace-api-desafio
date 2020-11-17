# marketplace-api-desafio
Desafio Hotmart - Marketplace

# Sobre o desafio implementação

- Para os testes foi utilizado o Postman, foi gerado uma coleção com as chamadas da api: **MarketPlace-Desafio.postman_collection**
- Banco de dados utilizado para o desafio foi o **H2**
- Ferramenta utilizada para versionamento de scripts foi a **Flyway**
- Biblioteca utilizada para cache foi a **ehcache**


## Segurança da api

**Configuração da segurança da api**
- client-id: marketplace-hotmart 
- client-secret: 123456

**Usuário cadastrado na api**
- usuário: cristiano
- senha: 123


### 1 - Geração do token
- 1.1 - Acessar a url via POST: http://localhost:8080/oauth/token 
- 1.2 - Na Aba Authorization, preencher os campos Username com o client-id e a Password com a client-secret será criado o campo **Authorization** com o valor Basic <Username+Password> em base 64. (Basic bWFya2V0cGxhY2UtaG90bWFydDoxM
- 1.3 - Na Aba Body, criar três campos: grant_type (password), username (cristiano) e password (123)
- 1.4 - Ao realizar a chamada do botão SEND terá como resultado o json abaixo e o campo do token é o *access_token*:

	'{
	    "access_token": "2db9a55f-454e-4272-a8a1-397f391f7788",
	    "token_type": "bearer",
	    "refresh_token": "d11a4a9f-8ab8-46e4-8c8e-7897d357ce48",
	    "expires_in": 43199,
	    "scope": "password"
	}'

### 2 - Exemplo chamada utilizando o *access_token*
- 2.1 - Acessar a url via GET: http://localhost:8080/api/v1/produtos
- 2.2 - Na Aba Headers, incluir a chave Authorization com o valor Bearer mais o token gerado
 **Bearer	2db9a55f-454e-4272-a8a1-397f391f7788**
- 2.3 - Realizar a chamada no botão SEND.


