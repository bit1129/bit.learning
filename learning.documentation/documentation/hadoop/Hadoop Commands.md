### Hadoop Commands

#### HDFS

- 查看HDFS文件的block个数

～～～bash
 hdfs fsck /user/hive/warehouse/nogz/part-00000 -files -blocks
～～～

说明：part-00000是HDFS的一个文件

- 查看HDFS目录的大小

～～～bash
hdfs dfs -du -s -h /user/hive/warehouse
～～～
说明：
-- 加-s选项表示显示warehouse目录的总大小，不加-s则会列出warehouse的一级子目录下所有的目录和文件的大小
-- 加-h选项表示以human readable的方式显示大小，比如1M，1G等
