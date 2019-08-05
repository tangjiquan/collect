#!/bin/sh

#该脚本作为linux下启动java应用的通用脚本。
#该脚本直接使用kill命令强制终止java程序。需要在杀死进程前，需要自己做检查
#Java程序所在的目录（classes的上一级目录）
APP_HOME=$(cd `dirname $0`; cd ..; pwd)
APP_CONF=$APP_HOME/conf/
APP_LOG_DIR=$APP_HOME/../log
APP_LOG_FILE=$APP_LOG_DIR/APP.log

if [ ! -d "$APP_LOG_DIR" ];then
    mkdir $APP_LOG_DIR
fi


#执行程序启动所使用的系统用户，考虑到安全，推荐不使用root帐号
RUNNING_USER=tangjiquan

#需要启动的Java主程序（main方法类）
APP_MAINCLASS=com.sinosoft.live.boot.BootStart

#拼凑完整的classpath参数，包括指定lib目录下所有的jar
CLASSPATH=`find ../lib -name *.jar|xargs|sed "s/ /:/g"`
CLASSPATH=".:$CLASSPATH"
CLASSPATH="$CLASSPATH:$APP_CONF"

#java虚拟机启动参数
JAVA_OPTS="-server -Xms2048m -Xmx6144m -Xss256k -XX:MaxDirectMemorySize=1024m"


###################################
#(函数)判断程序是否已启动
#
#说明：
#使用JDK自带的JPS命令及grep命令组合，准确查找pid
#jps 加 l 参数，表示显示java的完整包路径
#使用awk，分割出pid ($1部分)，及Java程序名称($2部分)
###################################
#初始化psid变量（全局）
psid=0

checkpid() {
   javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAINCLASS`

   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $1}'`
   else
      psid=0
   fi
}

checkPidByPs() {
   javaps=`ps -ef | grep $APP_MAINCLASS | grep -v 'grep'`

   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $2}'`
   else
      psid=0
   fi
}

###################################
#(函数)启动程序
#
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则提示程序已启动
#3. 如果程序没有被启动，则执行启动命令行
#4. 启动命令执行后，再次调用checkpid函数
#5. 如果步骤4的结果能够确认程序的pid,则打印[OK]，否则打印[Failed]
#注意：echo -n 表示打印字符后，不换行
#注意: "nohup 某命令 >/dev/null 2>&1 &" 的用法
###################################
start() {
   checkpid
   info
   if [ $psid -ne 0 ]; then
      echo "================================"
      echo "[WARN] $APP_MAINCLASS already started! (pid=$psid)"
      echo "================================"
   else
      echo  "Starting $APP_MAINCLASS ..."
      #JAVA_CMD="nohup $JAVA_HOME/bin/java $JAVA_OPTS -cp $CLASSPATH $APP_MAINCLASS >/dev/null 2>&1 &"
      #JAVA_CMD="nohup $JAVA_HOME/bin/java $JAVA_OPTS -cp $CLASSPATH $APP_MAINCLASS &"
      JAVA_CMD ="nohup $JAVA_HOME/bin/java $JAVA_OPTS -cp $CLASSPATH  $APP_MAINCLASS  >> $APP_LOG_FILE &"
      su - $RUNNING_USER -c "$JAVA_CMD"
      checkpid
      if [ $psid -ne 0 ]; then
         echo "(pid=$psid) [OK]"
      else
         echo "[Failed]"
      fi
      echo "start success..."
      tail -f $APP_LOG_FILE
   fi
}


###################################
#(函数)停止程序
#
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则开始执行停止，否则，提示程序未运行
#3. 使用kill -9 pid命令进行强制杀死进程
#4. 执行kill命令行紧接其后，马上查看上一句命令的返回值: $?
#5. 如果步骤4的结果$?等于0,则打印[OK]，否则打印[Failed]
#6. 为了防止java程序被启动多次，这里增加反复检查进程，反复杀死的处理（递归调用stop）。
#注意：echo -n 表示打印字符后，不换行
#注意: 在shell编程中，"$?" 表示上一句命令或者一个函数的返回值
###################################
stopkill() {
   checkpid

   if [ $psid -ne 0 ]; then
      echo -n "Stopping $APP_MAINCLASS ...(pid=$psid) "
      su - $RUNNING_USER -c "kill -9 $psid"
      kill -9 $psid
      if [ $? -eq 0 ]; then
         echo "[OK]"
      else
         echo "[Failed]"
      fi

      checkpid
      if [ $psid -ne 0 ]; then
         stop
      fi
   else
      echo "================================"
      echo "warn: $APP_MAINCLASS is not running"
      echo "================================"
   fi
}

stop(){
    #停止进程
    if [ -n "$psid" ]; then
      for PID in $psid; do
          su - $RUNNING_USER -c "kill $PID"
          echo "kill $PID"
      done
    fi

    #等待50秒
    for i in 1 10; do
      if [ ! -n "$psid" ]; then
        echo "stop server success"
        echo -------------------------------------------
        break
      fi
      echo "sleep 5s"
      sleep 5
    done

    #如果等待50秒还没有停止完，直接杀掉
    if [ -n "$psid" ]; then
      for PID in $psid; do
          su - $RUNNING_USER -c "kill -9 $PID"
          echo "kill -9 $PID"
      done
    fi
}
###################################
#(函数)检查程序运行状态
#
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则提示正在运行并表示出pid
#3. 否则，提示程序未运行
###################################
status() {
   checkpid

   if [ $psid -ne 0 ];  then
      echo "$APP_MAINCLASS is running! (pid=$psid)"
   else
      echo "$APP_MAINCLASS is not running"
   fi
}


###################################
#(函数)打印系统环境参数
###################################
info() {
   echo "System Information:"
   echo "****************************"
   echo `head -n 1 /etc/issue`
   echo `uname -a`
   echo
   echo "JAVA_HOME=$JAVA_HOME"
   echo `$JAVA_HOME/bin/java -version`
   echo
   echo "APP_HOME=$APP_HOME"
   echo "APP_MAINCLASS=$APP_MAINCLASS"
   echo "****************************"
}


###################################
#读取脚本的第一个参数($1)，进行判断
#参数取值范围：{start|stop|restart|status|info}
#如参数不在指定范围之内，则打印帮助信息
###################################
case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'status')
     status
     ;;
   'info')
     info
     ;;
  *)
     echo "Usage: $0 {start|stop|restart|status|info}"
     exit 1
esac
exit 0