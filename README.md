#Bullish API Test 

Based off the official skeleton provided by the Cucumber organization 

https://github.com/cucumber/cucumber-java-skeleton

Dependencies have been added using gradle , so it's recommended to use gradle to run tests.

cd gradle

./gradlew test --rerun-tasks --info

TestAPI has 7 tests , one of which fails intentionally as the Swagger documentation is wrong. A query for a single for student that doesn't exist will always return a 200 OK status code, rather than a 404 Not Found status code.

TestWebPage has 5 tests, all of which pass. 