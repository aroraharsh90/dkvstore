# Distributed Key Value Store
A simple distributed <String, String> key value store created with Spring Boot which can run across processes within systems or systems connected over network to replicate data between them.

## How to Run?
1. Clone the Repository
2. Edit application.properties (src/main/resources/) to add ip address and port of all the nodes where key-store will be running. *Ignore the server.port property for now. To make the process easier, We will define this property via command line in Step 4*
3. Use `mvn clean package` to create jar or build in Eclipse using `Maven Build...` and write goal as `package`. 
4. Execute following command on all nodes specified in Step 2 with port number as mentioned.
```
java -jar dkvstore.jar --server.port={port}
```
5. Test with following commands.
> Get {Key}
```
curl -XPOST http://{hostname}:{port}/get/{key}
```
> Set {Key}->{Val}
```
curl -XPOST http://{hostname}:{port}/set/{key} -d "val={val}"
```

## Extended Features Implemented
1. Replicates Key-Value over all nodes across different systems over network
2. Maintain Heartbeat between all nodes and keep track of alive nodes.
3. Can have any number of nodes with Key-Value pairing.
4. Appication layer Fault Tolerant.
