# Crud_DevOps
Projeto da disciplina Integração Contínua e DevOps [23E1_3] da Pós Graduação de Engenharia de Software com Java do Instituto Infnet
--
Crie um projeto com Spring Boot expondo algumas APIS. O projeto pode ser o mais simples possível, um CRUD, um conversor de medidas etc. É importante construir o projeto através das seguintes regras:<BR>
<BR>
Criar testes unitários para métodos utilizando:<BR>
- JUNIT, MOCKITO, TESTContainers (Opcional).
- Expor o health check do seu projeto com o Actuator.
- Exportar métricas do seu projeto para o formato do Prometheus utilizando o micrometer.
- Exportar LOGs do seu projeto para alguma ferramenta de logs. (Ex. Papertrail)
- Exportar dados do seu projeto para o Zipkin.
- Criar Script no terraform em qualquer provedor que crie apenas uma máquina virtual de qualquer formato. Esse script deve possuir 1 arquivo main, um arquivo de variáveis e um arquivo de outputs.
- Criar um pipeline de build do seu projeto no Gitlab.

O projeto precisa ser entregue da seguinte forma:<BR>
- Versionado em alguma das seguintes ferramentas: Github, bitbucket etc.
- O projeto deve estar público.
- Deve ser entregue o link para o projeto.
- Na raiz do projeto deve ter arquivos de imagem com printscreens do zipkin rodando.
- Todos devem postar o link para o projeto no Moodle.
