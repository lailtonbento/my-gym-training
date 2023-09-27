# Projeto Java 17 com Spring Boot

## Tecnologias utilizadas:

 - Java 17
 - Docker
 - Docker-compose
 - PostgreSQL
 - RabbitMQ
 - Spring Security
 - Spring Jpa
 - Spring Web

# Run with docker container:
Para construir a imagem Docker, execute os seguintes comandos na raiz do projeto:

### Dockerfile

    docker build -t my-gym-training .

### Docker-compose
Você pode usar o Docker Compose para iniciar facilmente o aplicativo com todos os serviços necessários (PostgreSQL, RabbitMQ):
    
    docker-compose up


O aplicativo estará disponível em http://localhost:8080