export interface ShellExecPlugin {
  execute(options: { command: string | string[] }): Promise<{ output: string; exitCode: number }>;
}
