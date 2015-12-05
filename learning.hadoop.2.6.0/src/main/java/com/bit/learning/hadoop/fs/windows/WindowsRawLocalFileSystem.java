package com.bit.learning.hadoop.fs.windows;

import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RawLocalFileSystem;
import org.apache.hadoop.fs.permission.FsPermission;

public class WindowsRawLocalFileSystem extends RawLocalFileSystem {
    @Override
    /** Convert a path to a File. */
    public File pathToFile(Path path) {
        checkPath(path);
        if (!path.isAbsolute()) {
            path = new Path(getWorkingDirectory(), path);
        }
        return new File(path.toUri().getPath());
    }

    @Override
    public void setPermission(Path p, FsPermission permission)
            throws IOException {

    }

    @Override
    public boolean mkdirs(Path f, FsPermission permission
    ) throws IOException {
        return mkdirs(f);
    }

    @Override
    public FileStatus[] listStatus(Path f) throws IOException {
        File localf = pathToFile(f);
        FileStatus[] results;

        if (!localf.exists()) {
            throw new FileNotFoundException("File " + f + " does not exist");
        }
        if (localf.isFile()) {
            return new FileStatus[]{
                    new WindowRawLocalFileStatus(localf, getDefaultBlockSize(f), this)};
        }

        String[] names = localf.list();
        if (names == null) {
            return null;
        }
        results = new FileStatus[names.length];
        int j = 0;
        for (int i = 0; i < names.length; i++) {
            try {
                // Assemble the path using the Path 3 arg constructor to make sure
                // paths with colon are properly resolved on Linux
                results[j] = getFileStatus(new Path(f, new Path(null, null, names[i])));
                j++;
            } catch (FileNotFoundException e) {
                // ignore the files not found since the dir list may have have changed
                // since the names[] list was generated.
            }
        }
        if (j == names.length) {
            return results;
        }
        return Arrays.copyOf(results, j);
    }

    @Override
    public FileStatus getFileStatus(Path f) throws IOException {
        File path = pathToFile(f);
        if (path.exists()) {
            return new WindowRawLocalFileStatus(pathToFile(f), getDefaultBlockSize(f), this);
        } else {
            throw new FileNotFoundException("File " + f + " does not exist");
        }
    }


    static class WindowRawLocalFileStatus extends FileStatus {
        /* We can add extra fields here. It breaks at least CopyFiles.FilePair().
         * We recognize if the information is already loaded by check if
         * onwer.equals("").
         */
        private boolean isPermissionLoaded() {
            return true;
        }

        WindowRawLocalFileStatus(File f, long defaultBlockSize, FileSystem fs) {
            super(f.length(), f.isDirectory(), 1, defaultBlockSize,
                    f.lastModified(), new Path(f.getPath()).makeQualified(fs.getUri(),
                            fs.getWorkingDirectory()));
        }

        @Override
        public FsPermission getPermission() {
            if (!isPermissionLoaded()) {
                loadPermissionInfo();
            }
            return super.getPermission();
        }

        @Override
        public String getOwner() {
            return System.getProperty("user.name");
        }

        @Override
        public String getGroup() {
            return System.getProperty("user.name");
        }

        /// loads permissions, owner, and group from `ls -ld`
        private void loadPermissionInfo() {

        }

        @Override
        public void write(DataOutput out) throws IOException {
            if (!isPermissionLoaded()) {
                loadPermissionInfo();
            }
            super.write(out);
        }
    }
}
