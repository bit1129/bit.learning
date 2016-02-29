#!/bin/bash
SERVERS=(132 133 134 135 136 137 161 162 163 164)

COMMAND='grep -C 2 "fs.defaultFS" /export/App/hadoop-2.6.0/etc/hadoop/core-site.xml'
for server in ${SERVERS[@]}
do
  echo "============================================================================"
  echo "Current IP is:" 172.18.149.$server
  pssh -h 172.18.149.$server -i $COMMAND
done