cd src/main/java/com/myenterprise/inventory
mkdir application domain infrastructure 
cd application
mkdir usecases exceptions
cd ..
cd domain
mkdir ports/input ports/output models --parents
cd ..
cd infrastructure
mkdir adapters/input/http adapters/input/mappers adapters/output/persistence/repository adapters/output/persistence/entities adapters/output/mappers --parents
cd ../../../../..
cd src/main/resources
cat <<EOT >> application.yml
spring:
  application:
    name: inventory-service 
EOT

rm application.properties