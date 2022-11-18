call gradle build
call copy build\libs\service_sum-0.0.1-SNAPSHOT.jar
call del "sum_service.jar"
call rename "service_sum-0.0.1-SNAPSHOT.jar" "sum_service.jar"
