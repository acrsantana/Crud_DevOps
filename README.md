# Crud_DevOps<BR>
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/acrsantana/Crud_DevOps/maven.yml?style=plastic)<BR><BR>
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


Instruções para executar o projeto
--
Baixar o arquivo [docker-compose.yml](https://github.com/acrsantana/Crud_DevOps/blob/master/docker-compose.yml) (necessário ter o docker)<BR>
No mesmo diretorio onde o arquivo foi baixado, executar o comando: docker-compose up <BR>
URL da API: http://localhost:8080/usuarios <BR>
URL do Zipkin: http://localhost:9411 <BR>
Documentação da API: http://localhost:8080/swagger-ui/index.html <BR>
<BR>
Zipkin AWS: http://ec2-44-200-93-82.compute-1.amazonaws.com:9411 <BR>
URL da API na AWS: http://ec2-44-200-93-82.compute-1.amazonaws.com:8080/usuarios <BR>

Instruções para executar o script terraform
--
Configurar a variável de ambiente AWS_ACCESS_KEY_ID com o seu Access Key Id da AWS<BR>
Configurar a variável de ambiente AWS_SECRET_ACCESS_KEY com o seu Access Key Secret da AWS<BR>
Entrar na pasta Terraform e executar o script de acordo com as instruções da Hashicorp<BR>
