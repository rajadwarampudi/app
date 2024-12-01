# app
A webapp that takes phrase and counts the total number of every word hits in several search services 

This is a spring-boot web application thymeleaf template engine.

The user needs to configure the following parameters to make google custom search engine to work. Follow the link (https://developers.google.com/custom-search/v1/overview) documentation to set api key and cse id.
searchservice.google.api.key=YOUR_CUSTOM_SEARCH_GOOGLE_API_KEY
searchservice.yahoo.api.key=YOUR_CUSTOM_SEARCH_YAHOO_API_KEY
searchservice.cse.id=YOUR_CSE_ID

Please note that you don't need to create two CSE IDs for two search services. One CSE ID is sufficient for both of them.

Running the application:
1. The spring boot application with the following command:  mvn spring-boot:run
2. The spring boot application initiates its embedded tomcat server that runs the web-app
3. open the link [local](http://localhost:8080/),  enter any phrase and click "search"
4. The result will appear on the updated screen with total number of hits.


Tech stack used for during the implementation
* Languages: Java, HTML (a little)
* Framework: Spring boot
* tools: git, github, maven
