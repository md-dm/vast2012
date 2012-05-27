#!/bin/bash -l

PID=`ps -ef | grep -v grep | grep mongo/bin/mongod | awk '{ print $2 }'`
if [ -z "$PID" ]
then
	echo "mongod is not running!"
else
	if [ "$1" = "force" ]
	then
		echo "Stopping mongod with kill -9..."
		kill -9 $PID > /dev/null
	else
		echo "Stopping mongod with kill -2..."
		kill -2 $PID > /dev/null
	fi
	sleep 5
	echo "...Done."
fi