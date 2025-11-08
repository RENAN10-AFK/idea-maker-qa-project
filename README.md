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

O teste manual foi executado com foco na jornada completa do usuário, cobrindo:

Login (Sign In / Skip Sign In)
Register (Cadastro de usuário)
Upload e Download de arquivos
**Documento completo com prints, evidências e descrição dos bugs encontrados:*

**[Abrir Teste Manual (PDF)](./docs/manual-tests/TestPlan_IDEA_Renan_Ribeiro.pdf)**

## Bugs Encontrados (Evidenciados por prints)

ID do Cenário Tela Descrição do Bug Evidência
| TC001 | Home | Botão *Skip Sign In* funciona normalmente (cenário passa) 
| TC002 | Login | Erro no login com e-mail inválido → página exibe *alert inesperado*, fluxo quebra | TC002-Login-EmailErrado.png |
| TC003 | Login | Login sem preenchimento do e-mail → mesmo alerta indevido | TC003-Login-SemEmail.png |
| TC004 | Register | Campo *Phone* aceita somente 10 dígitos (fora do padrão brasileiro) | TC004-Register-PhoneBug.png |
| TC005 | Register | Dropdown de País bloqueado por anúncio (iframe impede interação) | TC005-Register-AdBlockedCountry.png |
| TC006 | Register | Botão *Submit* também fica bloqueado pelo anúncio → impossibilitando cadastro | TC006-Register-AdBlockedSubmit.png |
| TC007 | Download TXT | Download de arquivo TXT funciona corretamente 
| TC008 | Download PDF | PDF gerado vem corrompido / não abre após download | TC008-DownloadPDFBug.png |
| TC009 | Upload | Tela aceita arquivo, porém o botão *Upload* não realiza nenhuma ação | TC009-UploadBug.png |

##  Pontos de Melhoria (Sugestões para Correção)

Componente Problema Sugestão de Melhoria

Validação do Login Mensagens genéricas e alertas do navegador=Implementar validações HTML5 e mensagens amigáveis na UI
Campo Phone Obrigatoriedade de 10 dígitos — sem considerar diferentes formatos internacionais Ajustar RegEx ou permitir formatação dinâmica conforme país
Anúncios sobre o formulário iframe está sobrepondo elementos essenciais (Dropdown + Submit) Ajustar o layout com **z-index**, ou carregar anúncio apenas após o cadastro
Botão Submit Não clicável devido ao anúncio Verificar CSS `pointer-events: none` aplicado pelo ads
Download PDF Arquivo gerado está corrompido Validar backend de geração do PDF
Upload de arquivo Botão não dispara requisição Verificar listener JS do botão *Upload* e endpoint 
### ▶ Execução dos testes Web

mvn -Dtest=web.NomeDoTeste test

makefile

Exemplo:

mvn -Dtest=web.J_RegisterTest test

yaml

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

Validação por JSON Schema também aplicada 

---

## Teste de Performance (K6)

Script:

k6 run performance/k6-script.js

Resultado obtido:

 Métrica  Resultado 

p95  abaixo de 5s 
Falhas 0% 
Requests executados  21.946 


##  Allure Report

Gerar report:

mvn clean test
allure serve allure-results

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

---
**Projeto desenvolvido para avaliação técnica — IDEA**