# regex-address
Parse a given address string into a json


## Challange
Implement a application to parse an `address string` to an `JSON` composed by `street` and `housenumber`. 
This implementation will consider that any address with no housenumber is an invalid address. 

##### Challenge requirements
**Input:** String of an address.
**Output:** string of street and string of street-number as JSON object.
**Stack:** `JAVA` | `Spring-boot` | `Maven`.

## Running Appliaction and Tests.

##### RUNNING THE TESTS
To run the tests, open the root directiry of the project in the `terminal` and run the command `mvn test`.
By doing this, maven should download the dependencies and then run the tests.
This application has `18 tests in total`, and totalize `over 90%` of code coverage.

### STARTING THE APPLICATION:
To start the application, its necessary to go to the root directory of the project and run in `terminal` the command `mvn spring-boot:run`. 
The  application will starting on port `8080`.


## API

**ENDPOINTS**
```
POST http://localhost:8080/parse/address/
```

**REQUEST**

|Method | Call | Body | Description|
|------------ | ------------- | -------------| -------------|
|POST | `/parse/address/` | String of an address | Return a JSON with the street name and house number.


**REQUEST AND RESPONSE EXEMPLE**
1. Most simple cases, e.g.
   * `"Winterallee 3"` -> `{"street": "Winterallee", "housenumber": "3"}`
   * `"Musterstrasse 45"` -> `{"street": "Musterstrasse", "housenumber": "45"}`
   * `"Blaufeldweg 123B"` -> `{"street": "Blaufeldweg", "housenumber": "123B"}`

2. More complicated cases
   * `"Am Bächle 23"` -> `{"street": "Am Bächle", "housenumber": "23"}`
   * `"Auf der Vogelwiese 23 b"` -> `{"street": "Auf der Vogelwiese", "housenumber": "23 b"}`

3. Other countries - More complex cases.
   1. `"4, rue de la revolution"` -> `{"street": "rue de la revolution", "housenumber": "4"}`
   1. `"200 Broadway Av"` -> `{"street": "Broadway Av", "housenumber": "200"}`
   1. `"Calle Aduana, 29"` -> `{"street": "Calle Aduana", "housenumber": "29"}`
   1. `"Calle 39 No 1540"` -> `{"street": "Calle 39", "housenumber": "No 1540"}`


**RESPONSES**

Código | Resposta
------------ | -------------
`200` | Json of an Address.
`400` | Invalid House number message
