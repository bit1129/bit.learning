package com.bit.learning.hadoop.fs.windows;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.AbstractFileSystem;
import org.apache.hadoop.fs.ChecksumFs;

import java.io.IOException;
import java.net.URISyntaxException;

public class WindowsChecksumFs extends ChecksumFs{
    public WindowsChecksumFs(AbstractFileSystem theFs) throws IOException, URISyntaxException {
        super(theFs);
    }
    public WindowsChecksumFs(Configuration conf) throws IOException, URISyntaxException {
        super(new WindowsDelegateToFileSystem(conf));
    }

}
