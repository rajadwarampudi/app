# app
A webapp that takes phrase and counts the total number of every word hits in several search services 

This is a spring-boot web application thymeleft template engine.

The user needs to configure the following parameters to make google custom search engine to work. Follow the link (https://developers.google.com/custom-search/v1/overview) documentation to set api key and cse id.
searchservice.google.api.key=YOUR_GOOGLE_CUSTOM_SEARCH_API_KEY ()
searchservice.google.cse.id=YOUR_CSE_ID

Running the application:
1. The the spring boot application with the following command:  mvn spring-boot:run
2. Springboot application initiates the its embedded tomcat server that runs the web-app
3. open the link [local](http://localhost:8080/),  enter any phrase and click "search"
4. The result will appear on the updated screen with totalnumber of hits.
