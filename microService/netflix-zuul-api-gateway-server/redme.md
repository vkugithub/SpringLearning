How to call currency exchange service using zuul api gateway
http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR

Install mqrabbit:
brew update
brew install rabbitmq

and start mqrabbit:
/usr/local/sbin/rabbitmq-server

Configure and start zipkin server
RABBIT_URI=amqp://localhost java -jar zipkin.jar

Dashboard of zipkin server
http://localhost:9411/zipkin/
