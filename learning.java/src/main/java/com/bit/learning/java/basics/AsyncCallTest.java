package com.bit.learning.java.basics;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class AsyncCallTest {
    public static void main(String[] args) throws IOException {
        String path = new File("").getAbsolutePath() + "/learning.java/src/main/java/com/bit/learning/java/basics/Person.java";

        System.out.println("Begin to call AsyncRequestUtils.async:");

        AsyncRequestUtils.async(new URL("file:///" + path), new AsyncCallback() {
            public void onSuccess(Object successResponse) {
                System.out.println("onSuccess: \n" + successResponse);
            }

            public void onFailure(Object failResponse) {
                System.out.println("onFailure: \n " + failResponse);
            }
        });

        //
        System.out.println("End to call AsyncRequestUtils.async:");

    }
}
