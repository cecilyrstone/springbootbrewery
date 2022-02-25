## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/cecilyrstone/springbootbrewery.git
```

**2. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## Explore Rest APIs

The app defines following CRUD APIs.

### Breweries

| Method | Url                                | Description                                       |
| ------ |------------------------------------|---------------------------------------------------|
| GET    | /api/breweries/{breweryId}         | Get brewery by id                                 |
| GET    | /api/breweries/search/{searchTerm} | Get breweries that match the search term provided |

Test them using postman or any other rest client.