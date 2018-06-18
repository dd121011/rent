#!/bin/bash

sed 's/active: dev/active: release/g' src/main/resources/application.yml > src/main/resources/.application.yml.tmp
mv src/main/resources/.application.yml.tmp src/main/resources/application.yml

echo '[package]'

mvn clean package

sed 's/active: release/active: dev/g' src/main/resources/application.yml > src/main/resources/.application.yml.tmp
mv src/main/resources/.application.yml.tmp src/main/resources/application.yml

echo '[sending]'

scp target/rent.jar root@47.106.212.7:/root/project/Rent/

echo '[restarting]'

ssh root@47.106.212.7 "sh /root/project/Rent/restart.sh"

echo '[done]'

# remove script [restart.sh] :
# ps aux | grep rent | grep java | awk '{print $2}' | xargs kill -9
# sleep 5
# nohup java -jar /root/project/Rent/rent.jar > /root/project/Rent/rent.log &