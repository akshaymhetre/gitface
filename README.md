# GitFace Application

Gitface is demo application to interact with your github and provide features like:
- Show all repositories
- Show all branches
- Create a branch
- Create content
- Update content
- Create PR

## Technology stack

This app is microservices based and uses following tech:
- Java 18
- Spring boot 3
- Spring cloud module
- Netflix OSS Eureka
- Docker
- Docker compose
- html, css & jquery


## Building and running the microservices

This project uses [Docker Compose](https://docs.docker.com/compose/) to run the services

### Pre-requisites:

- Install Java 18, Docker & Docker compose
- Create [Github OAuth APP](https://docs.github.com/en/apps/oauth-apps/building-oauth-apps/creating-an-oauth-app) 
- Set environment variables of created app in gitface.env

### The quick way

The quickest way to build and run the services on Linux/Mac OSX is with the following commands:

Create github oauth app

```
./gradle-all.sh clean build -x test
docker-compose up -d
```

### Application Interface

Go to http://localhost:8060/

Login screen:

![image](https://github-production-user-asset-6210df.s3.amazonaws.com/2605952/253859017-039d06c7-36ce-4b60-8996-aebb1706b944.png)

Home Screen:

![image](https://github-production-user-asset-6210df.s3.amazonaws.com/2605952/253859625-2620baed-fa46-4abb-b17d-fc08ca6dc457.png)
