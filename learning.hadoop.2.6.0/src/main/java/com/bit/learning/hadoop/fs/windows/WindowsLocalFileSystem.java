package com.bit.learning.hadoop.fs.windows;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;

public class WindowsLocalFileSystem extends LocalFileSystem {
    public WindowsLocalFileSystem() {
        this(new WindowsRawLocalFileSystem());
    }

    public WindowsLocalFileSystem(FileSystem fs) {
        super(fs);
    }

}
