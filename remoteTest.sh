#!/bin/bash

mvn clean

while [ -n "$1" ]
do
case "$1" in
--url) url="$2"
shift ;;
--browserName) browserName="$2"
shift ;;
--browserVersion) browserVersion="$2"
shift ;;
esac
shift
done

curl -H'Connect-Type: application/json' "$url/session" -d '{
"capabilities": {
"alwaysMatch": {
"browserName": "'$browserName'",
"browserVersion": "'$browserVersion'",
"selenoid:options": {
"name": "Session started using curl command...",
"sessionTimeout": "1m"
}
}
}
}'

mvn test
