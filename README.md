# Product API
REST api that aggregates product data from multiple sources and return JSON and also disclose an operation to update the price for a given product.

Installation instruction:

build application as maven : mvn install

Once you have the artifact built successfully, just run the jar file from commandLine using command bellow:

java -jar product-api-0.0.1-SNAPSHOT.jar com.target.product.api.ProductApplication --spring.profiles.active=local

NOTE: the local profile must be activate in order to run it locally, other else you have to set up "PORT" env variable.

refer to swagger-ui for try-outs and more on documentation.

- swagger json file (/contract/product-api.json)
- swagger ui = http://host:port/swagger-ui.html
