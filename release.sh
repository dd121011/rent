#!/bin/bash

sed 's/active: dev/active: release/g' src/main/resources/application.yml > src/main/resources/.application.yml.tmp
mv src/main/resources/.application.yml.tmp src/main/resources/application.yml

mvn clean package

sed 's/active: release/active: dev/g' src/main/resources/application.yml > src/main/resources/.application.yml.tmp
mv src/main/resources/.application.yml.tmp src/main/resources/application.yml

scp target/rent.jar root@47.106.212.7:/root/project/Rent/