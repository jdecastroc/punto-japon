#!/bin/bash

COUNTER=1
for file in src/main/resources/articles/*.json; do
DATA=$(less $file)
curl -XPUT 'http://51.255.202.84:9200/articulos/blogs/'$COUNTER -d "$DATA" -vn
sleep 10
COUNTER=$(($COUNTER+1))
done
