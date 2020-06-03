#:!/bin/bash

BASE_DIR=$(cd `dirname $0`; pwd)
echo "Welcome enter $BASE_DIR"

server_name=webchat.jar
config_file=conf/application.yml
console_out="logs/server.log"

#Set heap memory and Metaspace memory
#JAVA_OPT='-Xms1g -Xmx1g'
JAVA_OPT=-'Xms1g -Xmx1g -Xmn372m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m'
JAVA_OPT="${JAVA_OPT} -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/logs/webchat_heapdump.hprof"
JAVA_OPT="${JAVA_OPT} -XX:+PrintGCDetails -Xloggc:logs/webchat-gc.log"

cd $BASE_DIR

if [ ! -d "logs" ] ; 
then
  echo '创建日志目录'
  mkdir -p "logs"
fi


function start() {
    PID=$(ps -ef | grep $server_name | grep -v grep | awk '{ print $2 }')
    if [ -z "$PID" ]
	    then
	    echo will start ...
    else
	    echo "Start fail, app runing. at $CURRENT_DIR, pid=$PID"
	    exit 1
    fi
    nohup java $JAVA_OPT -jar $server_name --spring.config.location=$config_file>$console_out 2>&1 &
    tail -f $console_out
}

function stop() {
    _kill
}

function _kill() {
    PID=$(ps -ef | grep $server_name | grep -v grep | awk '{ print $2 }')
    if [ -z "$PID" ]
	    then
	    echo Application is already stopped
    else
	    echo kill $PID
	    kill $PID
    fi
}

case $1 in
    start)
      shift 1
      start $@
      ;;
    stop)
      shift 1
      stop
      ;;
    kill)
      shift 1
      _kill $@
      ;;
    restart)
      shift 1
      stop
      sleep 4
      start $@
      ;;
esac
