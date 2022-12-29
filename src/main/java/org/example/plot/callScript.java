package org.example.plot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class callScript {
    public callScript(String str) {
        String Script_Path = "./src/main/java/org/example/plot/" + str;
        ProcessBuilder Process_Builder = new ProcessBuilder("py", Script_Path).inheritIO();
        Process Demo_Process = null;
        try {
            Demo_Process = Process_Builder.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            Demo_Process.waitFor();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedReader Buffered_Reader = new BufferedReader(new InputStreamReader(Demo_Process.getInputStream()));
    }

}
