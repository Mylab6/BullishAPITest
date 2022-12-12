#Bullish API Test 

Based off the official skeleton provided by the Cucumber organization 

https://github.com/cucumber/cucumber-java-skeleton

Dependencies have been added using gradle , so it's recommended to use gradle to run tests.

Run the provided jar API file, and in this project.


cd gradle

./gradlew test --rerun-tasks --info

After running the tests, an html report can be found here. 

projectRoot/gradle/build/reports/tests/test/index.html

[TestAPI](gradle/src/test/resources/io/cucumber/skeleton/TestAPI.feature) has 7 tests , one of which is skipped intentionally as the Swagger documentation is wrong. A query for a single for student that doesn't exist will always return a 200 OK status code, rather than a 404 Not Found status code.

[TestWebPage](gradle/src/test/resources/io/cucumber/skeleton/TestWebPage.feature) has 5 tests, all of which pass. 
