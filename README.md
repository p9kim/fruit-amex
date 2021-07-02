# Notes

Here are some notes before getting started:

- This api supports calculating and storing apples and oranges cost through both url params
  and JSON post request. Wasn't specified which one way to implement so I implemented both:
  
  - Through url params
  ```
  $ curl -d "apples=10&oranges=8&applePromo=false&orangePromo=false" http://localhost:8080/api/fruitshop/checkout
    {"id":"8d1c355b-c163-4e55-af30-c4e54d2df5d2","appleCount":10,"orangeCount":8,"applesCost":6.0,"orangesCost":2.0,"totalCost":8.0,"applePrice":0.6,"orangePrice":0.25}%
  ```
  - Through JSON post request
  ```
  $ curl --header "Content-Type: application/json" --request POST --data '{"apple_count": 10, "orange_count": 4, "apple_promo": true, "orange_promo": false}' http://localhost:8080/api/fruitshop
    {"id":"33ceb68b-3246-4ae6-8073-a549043f4428","appleCount":10,"orangeCount":4,"applesCost":3.0,"orangesCost":1.0,"totalCost":4.0,"applePrice":0.6,"orangePrice":0.25}%
  ```

## Setup

Api is build using spring boot and gradle. Repository library was used to create a mock database.
Mockmvc was used for api JUnit5 testing, but testing through Postman was also used along the way.




