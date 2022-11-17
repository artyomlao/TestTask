call gradle build
call copy /-y build\libs\service_sum-0.0.1-SNAPSHOT.jar
call rename "service_sum-0.0.1-SNAPSHOT.jar" "sum_service.jar"
