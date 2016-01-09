### Hadoop Commands

#### HDFS

- 查看HDFS文件的block个数

～～～bash
 hdfs fsck /user/hive/warehouse/nogz/part-00000 -files -blocks
～～～

说明：part-00000是HDFS的一个文件