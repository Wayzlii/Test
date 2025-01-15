#!/bin/bash

while [ -n "$1" ]
do
case "$1" in
--file) fileName="$2"
shift ;;
--search) searchName="$2"
shift ;;
esac
shift
done

echo $(cat $fileName | grep -o $searchName | wc -l)

