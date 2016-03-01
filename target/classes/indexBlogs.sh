#!/bin/bash

COUNTER=1
for file in src/main/resources/articles/*.json; do
DATA=$(less $file)
curl -XPUT 'http://51.255.202.84:9200/blogs/articulos/'$COUNTER -d "$DATA" -vn
sleep 4
COUNTER=$(($COUNTER+1))
done
echo "finished"
