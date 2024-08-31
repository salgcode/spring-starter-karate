8001 - admin
8000 - access

Kong has two things:
    - route - path to forward to
    - services - target to work with

#### Run Kong

cd /Users/sailendragautam/devtools/projects/insta-infra
./run.sh kong sonarqube


#### List Kong services
http://localhost:8001/services

#### Create a new service

curl -i -s -X POST http://localhost:8001/services \
--data name=bin \
--data url='http://httpbin.org'

#### List my service
curl -X GET http://localhost:8001/services/bin

#### Update attributes for a service

curl --request PATCH \
--url localhost:8001/services/bin \
--data retries=6 \
--data read_timeout=30000

#### Create a route in the service
curl -i -X POST http://localhost:8001/services/bin/routes \
--data 'paths[]=/mock' \
--data name=example1

curl -i -X POST http://localhost:8001/services/bin/routes \
--data 'paths[]=/ip' \
--data name=example2


#### View my routes for services
http://localhost:8001/services/example_service/routes

Or I can check specific route by name
http://localhost:8001/services/example_service/routes/example1

curl --request PATCH \
--url localhost:8001/services/bin/routes/example1 \
--data tags="httpbin"


#### Our service and route are set.
We can check the routing through kong now.

curl -X GET http://localhost:8000/mock/anything


#### Now setup sonarqube route for /sonar

curl -i -s -X POST http://localhost:8001/services \
--data name=codequality \
--data url='http://sonarqube:9000'


Let's check if there are any routes. There should be none.
http://localhost:8001/services/codequality/routes

#### Create a new route within sonar service
curl -i -X POST http://localhost:8001/services/codequality/routes \
--data 'paths[]=/sonar' \
--data name=landing \
--data strip_path=false


#### Test sonarqube from kong

curl -i -s -X GET http://localhost:8000/sonar


#### Delete the service I created

curl -X DELETE http://localhost:8001/services/codequality/routes/landing
curl -X DELETE http://localhost:8001/services/codequality


Test Adding analysis result to sonar
./gradlew sonar -x test

http://localhost:8000/sonar/projects
