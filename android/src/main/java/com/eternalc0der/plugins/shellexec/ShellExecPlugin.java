package com.eternalc0der.plugins.shellexec;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "ShellExec")
public class ShellExecPlugin extends Plugin {

    private ShellExec implementation = new ShellExec();

    @PluginMethod
    public void echo(PluginCall call) {
        String command = call.getString("command");

        if (command == null || command.isEmpty()) {
            call.reject("Command is required");
            return;
        }

        try {
            JSObject ret = implementation.echo(command);
            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Execution failed", e);
        }

    }
}
