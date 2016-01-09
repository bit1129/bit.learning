### Hadoop Commands

#### HDFS

- 1. 查看HDFS文件的block个数

～～～bash
 hdfs fsck /user/hive/warehouse/nogz/part-00000 -files -blocks
～～～

说明：part-00000是HDFS的一个文件

- 2. 查看HDFS目录的大小

～～～bash
hdfs dfs -du -s -h /user/hive/warehouse
～～～
说明：
-- 加-s选项表示显示warehouse目录的总大小，不加-s则会列出warehouse的一级子目录下所有的目录和文件的大小
-- 加-h选项表示以human readable的方式显示大小，比如1M，1G等


- 3. 依次启动和停止Hadoop进程

-- 启动顺序

～～～
sbin/hadoop-daemon.sh start namenode
sbin/hadoop-daemon.sh start datanode
sbin/hadoop-daemon.sh start secondarynamenode


sbin/yarn-daemon.sh start resourcemanager
sbin/yarn-daemon.sh start nodemanager
～～～


-- 停止顺序

～～～bash
sbin/yarn-daemon.sh stop nodemanager
sbin/yarn-daemon.sh stop resourcemanager

sbin/hadoop-daemon.sh stop namenode
sbin/hadoop-daemon.sh stop secondarynamenode
sbin/hadoop-daemon.sh stop datanode

～～～