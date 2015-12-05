package com.bit.learning.hadoop.fs.windows;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.DelegateToFileSystem;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsConstants;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WindowsDelegateToFileSystem extends DelegateToFileSystem {
    public WindowsDelegateToFileSystem(URI theUri, FileSystem theFsImpl, Configuration conf, String supportedScheme, boolean authorityRequired) throws IOException, URISyntaxException {
        super(theUri, theFsImpl, conf, supportedScheme, authorityRequired);
    }

    public WindowsDelegateToFileSystem(final Configuration conf) throws IOException, URISyntaxException {
        this(FsConstants.LOCAL_FS_URI, conf);
    }


    public WindowsDelegateToFileSystem(URI localFsUri, Configuration conf) throws IOException, URISyntaxException {
        super(localFsUri, new WindowsRawLocalFileSystem(), conf, localFsUri.getScheme(), false);
    }
}
