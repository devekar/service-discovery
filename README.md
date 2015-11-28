# service-discovery
Using zookeeper for service discovery with spring apps

##Summary
Service B is a client of service A. We will have multiple instances of service A and B running at various times. Using zookeeper, we can have service A register themselves to live. Service B will discover available instances dynamically and call a available instance.

##Usage
1. Start multiple insatnces of service A by changing port and using maven spring-boot or tomcat instances.
2. Start an instance of serviceB and use GET: http://localhost:7090/serviceB/message
3. Responses should cycle though the available instances of service A.

##Libraries
Apache Zookeeper  
Apache Curator  
Archaius  
Spring Cloud  

##References
1. http://sleeplessinslc.blogspot.com/2014/09/dynamic-service-discovery-with-apache.html
2. http://blog.arungupta.me/zookeeper-microservice-registration-discovery/
3. http://www.javacodegeeks.com/2013/11/coordination-and-service-discovery-with-apache-zookeeper.html
