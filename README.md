IDEA QA Automation Project  
**Autor:** Renan Ribeiro  
**Empresa:** IDEA  
**Stack utilizada:** Selenium + Java + TestNG + RestAssured + K6 + Allure Report + CI/CD GitHub Actions  

---

## Visão Geral

Este projeto valida a qualidade do site **Automation Demo Site**  
➡ https://demo.automationtesting.in/

Foram realizados três tipos de testes:

| Tipo de teste | Tecnologia | Status |
Web UI (Selenium + TestNG) Fluxos reais do usuário Bugs identificados
API (RestAssured + JSON Schema) CRUD de produtos (DummyJson API)  Passou 
Performance (K6) Teste de carga e stress Passou 

---

## Estrutura do Projeto

qa-idea-maker/
src
test
java
web -> Automação UI Selenium
api -> Testes API RestAssured
performance -> K6 script
resources
schemas -> JSON Schema de validação
evidence
screenshots
videos
pdf
pom.xml
README.md

yaml
Copiar código


## Testes Web (Selenium)

### Cenários Explorados e Resultados

ID Cenário Web | Resultado
| TC001 | Acesso "Skip Sign In" Passou
| TC002 | Login com e-mail inválido  **BUG – alerta incorreto**
| TC003 | Login sem preencher e-mail  **BUG – não valida obrigatório** 
| TC004 | Register – Validação telefone  **BUG – aceita formatos inválidos** 
| TC005 | Register – Campo Country bloqueado por anúncio **BUG crítico – UI bloqueada** 
| TC006 | Register – Submit bloqueado **BUG crítico – anúncio bloqueia ação** 
| TC007 | Download TXT funciona 
| TC008 | Download PDF baixa arquivo incorreto  BUG 
| TC009 | Upload não funciona   BUG – botão não executa ação 

📄 **PDF com evidências (prints + análise dos bugs):**  
👉 [Test Manual IDEA (PDF)](evidence/pdf/Teste_Manual_IDEA_QA.pdf)

🖼 Prints armazenados em  
➡ `evidence/screenshots/`

---

### ▶ Execução dos testes Web

mvn -Dtest=web.NomeDoTeste test

makefile
Copiar código

Exemplo:

mvn -Dtest=web.J_RegisterTest test

yaml
Copiar código

---

## Testes de API (RestAssured)

API Utilizada: https://dummyjson.com/products
 Método Endpoint Resultado
POST `/products/add`
GET `/products`
PUT `/products/1`
DELETE `/products/1`

Execução:

mvn -Dtest=api.ProductApiTest test

yaml
Copiar código

Validação por JSON Schema também aplicada 

---

## Teste de Performance (K6)

Script:

k6 run performance/k6-script.js

yaml
Copiar código

Resultado obtido:

 Métrica  Resultado 

p95  abaixo de 5s 
Falhas 0% 
Requests executados  21.946 


##  Allure Report

Gerar report:

mvn clean test
allure serve allure-results

yaml
Copiar código

---

## CI/CD — GitHub Actions

Arquivo incluído:  
`.github/workflows/ci.yml`

Pipeline executa:

✔ build  
✔ testes  
✔ gera Allure report  

---

## Como rodar o projeto local

git clone https://github.com/RENAN10-AFK/idea-maker-qa-project.git
cd idea-maker-qa-project
mvn clean test

yaml
Copiar código

---

##  Vídeo da execução

(Adicionar depois o vídeo na pasta `/evidence/videos/` e colocar o link aqui)

---
**Projeto desenvolvido para avaliação técnica — IDEA**