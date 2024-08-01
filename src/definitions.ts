declare module '@capacitor/core' {
  interface PluginRegistry {
    ShellExec: ShellExecPlugin;
  }
}

export interface ShellExecPlugin {
  execute(options: { command: string }): Promise<{ output: string; exitCode: number }>;
}
