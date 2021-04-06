# URL Shortener
URL Shortener will allow users to pass a long URL and will be given a shortened URL.
That shortened URL will redirect to the complete URL.

## Requests
| METHOD | URL | DESCRIPTION |
| ------ | ------ | ------ |
| POST | /shorten-link | Takes JSON object(```{"link": "https://example-link.com"}```), generates unique short-link and saves it to the system |
| GET | /links | Returns full list of links stored on system |
| GET | /{short-link} | Redirects from the provided short-link to the corresponding full-link |

## Maven Spring Boot
Run the api
```./mvnw spring-boot:run```

Run the ui
```grunt serve```

## Docker
Build the Docker image
```./mvnw spring-boot:build-image```

Run the Docker image
```docker run -it -p8080:8080 demo:0.0.1-SNAPSHOT```

App will be running at
```localhost:8080```

## Tech
URL-Shortener-api is built using the following technologies:
- Java 16
- Spring 2.4.4
- Maven 3.6.3
- JUnit 5.7.0
- jackson 2.12.2
- Docker
URL-shortener-ui is built 
- AngularJS
- Tailwind CSS

Progress on this project can be seen on the [Trello] board  
User stories & UML diagrams can be viewed on the [Miro] board

[Trello]: <https://trello.com/b/XV99y2JP>
[Miro]: https://miro.com/app/board/o9J_lMOnXfU=/