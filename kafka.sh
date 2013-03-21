#!/bin/sh
mvn exec:java -Dexec.mainClass="KafkaMain" -Dexec.args="src/main/resources/words.txt"