#! /bin/bash

x=10
y=20
z=100.1

###bash 001_arithmetic.sh
let result=$x+$y
echo $result

###注意是$(()),而不是(())
result=$((x*y))
echo $result
