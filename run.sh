#!/usr/bin/env bash

./gradlew clean build
./gradlew jar
java -jar ././build/libs/drone-delivery-challenge-1.0-SNAPSHOT.jar $1
echo $(pwd)/output.txt