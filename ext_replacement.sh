#!/bin/bash

while [ -n "$1" ]
do
case "$1" in
--file) fileName="$2"
shift ;;
--extention) ext="$2"
shift ;;
--replacement) rep="$2"
shift ;;
esac
shift
done

mv -- "$fileName" "${fileName%.$ext}.$rep"
