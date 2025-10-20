#Arranque para dev (H2). Ejecuta desde la raíz del proyecto.
$env:SPRING_PROFILES_ACTIVE=""
# Si cambias a Postgres: descomenta y ajusta
# $env:SPRING_PROFILES_ACTIVE="prod"
# $env:DB_URL="jdbc:postgresql://localhost:5432/orders"
# $env:DB_USER="postgres"
# $env:DB_PASSWORD="postgres"


mvn -q -DskipTests spring-boot:run