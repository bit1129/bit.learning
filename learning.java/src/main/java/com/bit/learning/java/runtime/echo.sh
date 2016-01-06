#!/bin/bash

if [ $# -lt 2 ];then
	echo "param length is error $#, it should be 2"
	exit -1
fi

echo "Hello, I called!" $1 $2