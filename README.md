# URL Shortener
URL Shortener will allow users to pass a long URL and will be given a shortened URL.
That shortened URL will redirect to the complete URL.  

Progress on this project can be seen on the [Trello] board  
User stories & UML diagrams can be viewed on the [Miro] board

## Requests
| METHOD | URL | DESCRIPTION |
| ------ | ------ | ------ |
| POST | /shorten-link | Takes JSON object(```{"link": "https://example-link.com"}```), generates unique short-link and saves it to the system |
| GET | /links | Returns full list of links stored on system |
| GET | /{short-link} | Redirects from the provided short-link to the corresponding full-link |

## API
The api is built with Maven Spring Boot  
```cd url-shortener-api```  
```./mvnw spring-boot:run```  
To run tests only:  
```mvn test```  

## UI
The ui is built in AngularJS.  
```cd url-shortener-ui```  
```ng serve```

## Docker
Build the API Docker image  
```mvn spring-boot:build-image```

Run the API Docker image  
```docker run -it -p 8080:8080 url-shortener-api:0.0.1-SNAPSHOT```

API will be running at  
```localhost:8080```  

Build the UI Docker image  
```docker build -t url-shortener-ui .```

Run the UI Docker image  
```docker run -d -p 80:80 url-shortener-ui```

UI will be running at  
```localhost:80```

## Tech
url-Shortener-api was built using:
- Java 11
- Spring 2.4.4
- Maven 3.6.3
- JUnit 5.7.0
- H2 Database Engine
  
url-shortener-ui was built using:
- AngularJS
- Tailwind CSS

[Trello]: <https://trello.com/b/XV99y2JP>
[Miro]: https://miro.com/app/board/o9J_lMOnXfU=/