if you are unable to debug in spring boot, use following command spring-boot:run -Dspring-boot.run.fork=false

Acuator url : http://localhost:8092/actuator/
Hal browser url : http://localhost:8092
Browse hal browser for metrics then memory used : http://localhost:8092/actuator/metrics/jvm.memory.used