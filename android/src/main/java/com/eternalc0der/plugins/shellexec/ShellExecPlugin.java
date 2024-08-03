package io.ionic.plugins.shellexec;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.JSArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

@CapacitorPlugin(name = "ShellExec")
public class ShellExecPlugin extends Plugin {

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    @PluginMethod
    public void execute(final PluginCall call) {
        final String commandStr = call.getString("command");
        final JSArray commandArray = call.getArray("command");

        if ((commandStr == null || commandStr.isEmpty()) && (commandArray == null || commandArray.length() == 0)) {
            call.reject("Command is required");
            return;
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Process process;
                    if (commandArray != null && commandArray.length() > 0) {
                        String[] commandArr = new String[commandArray.length()];
                        for (int i = 0; i < commandArray.length(); i++) {
                            commandArr[i] = commandArray.getString(i);
                        }
                        process = Runtime.getRuntime().exec(commandArr);
                    } else {
                        process = Runtime.getRuntime().exec(commandStr);
                    }

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

                    call.resolve(result);
                } catch (IOException e) {
                    call.reject("IOException occurred: " + e.getMessage());
                    Logger.getLogger(ShellExecPlugin.class.getName()).log(Level.SEVERE, null, e);
                } catch (InterruptedException e) {
                    call.reject("Command execution interrupted: " + e.getMessage());
                    Logger.getLogger(ShellExecPlugin.class.getName()).log(Level.SEVERE, null, e);
                } catch (Exception e) {
                    call.reject("Unexpected error occurred: " + e.getMessage());
                    Logger.getLogger(ShellExecPlugin.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        });
    }
}
