
#! /bin/sh
name="zax-app-1.0-SNAPSHOT.jar"
Xmx=1024m
Xms=1024m
Xmn=512m
PermSize=256m
MaxPermSize=512m
pid="zax-app-1.0-SNAPSHOT.jar.pid"
logName="all"
jarName="zax-app-1.0-SNAPSHOT.jar"
 
 
start(){
	if [ -f "$pid" ]
	then
		echo "$jarName is running !"
		exit 0;
	else
		echo -n  "start ${jarName} ..."
		nohup java 	-Xmx${Xmx} -Xms${Xms} -Xmn${Xmn}   \
			-XX:PermSize=${PermSize} \
			-XX:MaxPermSize=${MaxPermSize} \
			-XX:+UseParNewGC \
			-XX:+UseConcMarkSweepGC \
			-XX:CMSFullGCsBeforeCompaction=3 \
			-XX:CMSInitiatingOccupancyFraction=60 -jar ${jarName} >/dev/null 2>&1 &   #注意：必须有&让其后台执行，否则没有pid生
		[ $? -eq 0 ] && echo   "[ok]"
		echo $! > ${pid}   # 将jar包启动对应的pid写入文件中，为停止时提供pi
		#currentLogFile=`ls ./logs/ | grep  "$logName" |sort -r | head -n 1`
		#echo $currentLogFile
		
      fi
}
#停止方法
stop(){
	echo -n "stop $name ..."
 	if [ -f "$pid" ]
	then
		PID=$(cat ${pid})
	        kill -9 $PID
		[ $? -eq 0 ] && echo  "[ok]"
		rm -fr $pid
	else
		echo  "[ok]"
	fi
}
 
case "$1" in
start)
  start
  ;;
stop)
  stop
  ;;
restart)
  stop
  start
  ;;
*)
  printf 'Usage: server.sh { start|stop|restart}\n'
  exit 1
  ;;
esac
