#!/bin/bash

COUNTER=1
for file in src/main/resources/articles/*.json; do
DATA=$(less $file)
curl -XPUT 'http://91.134.143.80:9200/blogs/articulos/'$COUNTER -d "$DATA" -vn
sleep 4
COUNTER=$(($COUNTER+1))
done
echo "finished"
