# Distributed Key Value Store
A simple distributed <String, String> key value store created with Spring Boot which can run more than 1 different process to replicate data between them.

## How to Run?
1. Clone the Repository
2. Edit application.properties (src/main/resources/) to add ip address and port of all the nodes where key-store will be running. *Ignore the server.port property for now. To make the process easier, We will define this property via command line in Step 4*
3. Use `mvn clean package` to create jar or build in Eclipse using `Maven Build...` 
4. Execute following command on all nodes specified in Step 2 with port number as mentioned.
```
java -jar dkvstore.jar --server.port={port}
```
