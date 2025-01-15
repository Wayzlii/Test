#!/bin/bash

inputNum=$1
resSum=0
resMult=1

if [ $(($inputNum % 2)) -eq 0 ]
then

for((i=1; i<=(($inputNum / 2)); i++))
do
resMult=$(($resMult * $i))
done
echo "mult is $resMult"

for ((i=(($inputNum / 2 + 1)); i<=$inputNum; i++))
do
resSum=$(($resSum + $i))
done
echo "sum is $resSum"

else

for((i=1 ; i<=(($inputNum / 2)); i++))
do
resMult=$(($resMult * $i))
done
echo "mult is $resMult"

for ((i=(($inputNum / 2 + 2)); i<=$inputNum; i++))
do
resSum=$(($resSum + $i))
done
echo "sum is $resSum"

fi

