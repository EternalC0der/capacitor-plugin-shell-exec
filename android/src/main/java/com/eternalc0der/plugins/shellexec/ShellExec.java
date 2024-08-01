package com.eternalc0der.plugins.shellexec;

import android.util.Log;

public class ShellExec {

    public String execute(String command) {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitCode = process.waitFor();
        JSObject result = new JSObject();
        result.put("output", output.toString());
        result.put("exitCode", exitCode);

        return result
    }
}
