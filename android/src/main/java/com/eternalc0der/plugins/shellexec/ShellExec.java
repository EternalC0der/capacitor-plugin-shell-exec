package com.eternalc0der.plugins.shellexec;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@NativePlugin
public class ShellExec extends Plugin {

    @PluginMethod
    public void execute(PluginCall call) {
        String command = call.getString("command");
        if (command == null || command.isEmpty()) {
            call.reject("Must provide a command to execute.");
            return;
        }

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            reader.close();
            process.waitFor();

            JSObject ret = new JSObject();
            ret.put("output", output.toString());
            call.resolve(ret);
        } catch (IOException | InterruptedException e) {
            call.reject("Failed to execute command", e);
        }
    }
}
