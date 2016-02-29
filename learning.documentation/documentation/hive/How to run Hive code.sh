
HADOOP_HIVE_CLASSPATH=/export/App/hadoop-2.6.0/share/hadoop/common/*.jar:/export/App/hadoop-2.6.0/share/hadoop/hdfs/*.jar:/export/App/hadoop-2.6.0/share/hadoop/yarn/*.jar:/export/App/hadoop-2.6.0/share/hadoop/mapreduce/*.jar:/export/App/hadoop-2.6.0/share/hadoop/tools/lib/*.jar:/export/App/hive-0.14.0/lib/*.jar


CP=$HADOOP_HIVE_CLASSPATH:learning.hive.0.14.0-1.0-SNAPSHOT.jar

java -cp $CP com.bit.leanring.hive.hiveapi.HiveUsag