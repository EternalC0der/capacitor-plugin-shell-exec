export interface ShellExecPlugin {
  execute(options: { command: string }): Promise<{ output: string; exitCode: number }>;
}
