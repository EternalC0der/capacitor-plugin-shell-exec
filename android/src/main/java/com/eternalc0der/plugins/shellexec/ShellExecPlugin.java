package io.ionic.plugins.shellexec;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@CapacitorPlugin(name = "ShellExec")
public class ShellExecPlugin extends Plugin {

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    @PluginMethod
    public void execute(final PluginCall call) {
        final String command = call.getString("command");

        if (command == null || command.isEmpty()) {
            call.reject("Command is required");
            return;
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
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

                    call.resolve(result);
                } catch (Exception e) {
                    call.reject("Execution failed", e);
                }
            }
        });
    }
}
