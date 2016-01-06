package com.bit.learning.java.runtime;

import java.io.*;

public class ProcessBuilderTest {
    public static void main(String[] args) throws IOException, InterruptedException {


        ProcessBuilder builder = new ProcessBuilder();
        File workDir = new File(System.getProperty("user.dir"));
        System.out.println(workDir.getAbsolutePath());

        String shFile = workDir.getAbsolutePath() + "/learning.java/src/main/java/com/bit/learning/java/runtime/echo.sh";

        if (!new File(shFile).exists()) {
            throw new IllegalArgumentException(String.format("The file [%s] doesn't exist", shFile));
        }

        builder.directory(workDir);
        boolean useExistShellFile = true;
        shFile = useExistShellFile ? shFile : shFile + ".noneExist";
        String[] validCommands = new String[]{"sh", shFile, "argument1", "argument2"};

        /**
         * sh echo.sh args does NOT work for the java ProcessBuilder, must separate the script into command string
         */
        String[] invalidCommands = new String[]{String.format("sh %s argument1 argument2", shFile)};

        boolean useValidCommands = true;
        String[] commands;
        if (useValidCommands) {
            commands = validCommands;
        } else {
            commands = invalidCommands;
        }
        builder.command(commands);

        final Process proc = builder.start();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                BufferedReader br = null;
                try {
                    InputStream in = proc.getInputStream();
                    br = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println("standard output: " + line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };
        t1.start();


        Thread t2 = new Thread() {
            @Override
            public void run() {
                BufferedReader br = null;
                try {
                    InputStream in = proc.getErrorStream();
                    br = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println("standard error output: " + line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };
        t2.start();


        //while loop before finished
        int code = proc.waitFor();

        //When proc finished, wait for the threads to finish
        t1.join();
        t2.join();

        System.out.println("waitFor rc: " + code);
        code = proc.exitValue();

        System.out.println("exitValue rc: " + code);


    }
}
