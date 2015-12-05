package com.bit.learning.hadoop.fs.rw;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by yuzhitao on 2015/12/5.
 */
public class WindowFileSystemReadWriteTest {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        ClassLoader loader = WindowFileSystemReadWriteTest.class.getClassLoader();
        InputStream in = loader.getResourceAsStream("local.file.system.xml");
        if (in == null) {
            in = loader.getResourceAsStream("/local.file.system.xml");
        }
        conf.addResource(in);
        FileSystem fs = FileSystem.get(conf);

        //输出WindowsLocalFileSystem
        System.out.println(fs.getClass().getName());

        //当前的路径是什么？
        Path workingDirectory = fs.getWorkingDirectory();
        Path homeDirectory = fs.getHomeDirectory();
        String scheme = fs.getScheme();
        URI uri = fs.getUri();

        //输出当前工程的位置，
        System.out.println("WorkingDirectory: " + workingDirectory.toUri().getPath());

        //输出user.dir目录
        System.out.println("HomeDirectory: " + homeDirectory.toUri().getPath());

        //输出file
        System.out.println("scheme: " + scheme.toString());

        //输出/，也就是说在根目录下
        System.out.println("fs.getUri() is : " + uri.getPath());

        listFiles(fs, "/");

        long suffix = System.currentTimeMillis();

        //创建新文件并写入内容，同时生成一个同名的crc文件
        System.out.println("Write a new file to the file system's root directory");
        OutputStream out = fs.create(new Path("/fs.txt" + suffix));
        out.write("Hello,World\n".getBytes());
        out.close();

        //不支持append操作
/*        out = fs.append(new Path("/fs.txt" + suffix));
        out.write("How are you\n".getBytes());
        out.close();*/

        System.out.println("copy a file to the file system's root directory");
        fs.copyFromLocalFile(new Path("c:/students-1447659105167.txt"), new Path("/"));


        //user.dir表示用户的工作目录，跟fs.getWorkingDirectory()返回的结果是一致的，因为
        //fs.getWorkingDirectory()也是通过System.getProperty("user.dir")获得的
        String userDir = System.getProperty("user.dir");
        listFiles(fs, userDir);


        fs.close();
    }

    //非递归的列出文件系统根目录下的所有文件
    public static void listFiles(FileSystem fs, String path) throws Exception {

        RemoteIterator<LocatedFileStatus> fileStatuses = fs.listFiles(new Path(path), false);
        System.out.println("Unrecursively list all the files under the " + path);

        //当前的工程是在D盘，即working directory是d:/myprojects/learning,为什么/指向了d盘？
        while (fileStatuses.hasNext()) {
            LocatedFileStatus fileStatus = fileStatuses.next();
            System.out.println(fileStatus.getPath().toString());
        }
    }
}
