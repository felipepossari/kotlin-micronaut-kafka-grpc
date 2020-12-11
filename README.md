## General info

Simple project that uses Micronaut, Kotlin, Kafka and Grpc. What it does:

* Receive a request thought GRPC;
* Save the data into db;
* Publish the data saved in the kafka topic;
* A consumer consumes the message and logs the data received;
	
## Technologies
Project is created with:
* Micronaut;
* Kotlin;
* Postgres;
* Kafka;
* GRPC
* BloomRPC
	
## Setup
To run this project:

* Clone the project;
* Execute: ``docker-compose up -d`` 
* Execute: `` gradle clean build``
* Execute: `` gradle run``
* Download the [BloomRPC](https://github.com/uw-labs/bloomrpc);
* Load the kafka.proto file located in kotlin-micronaut-kafka-grpc\src\main\proto
* Set the destination to 0.0.0.0:50051
* Send the request
