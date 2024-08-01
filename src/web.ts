import { WebPlugin } from '@capacitor/core';

import type { ShellExecPlugin } from './definitions';

export class ShellExecWeb extends WebPlugin implements ShellExecPlugin {
  async execute(options: { command: string }): Promise<{ output: string; exitCode: number }> {
    // To suppress the unused parameter warning
    void options;

    console.log('This function is not available in the web');
    return { output: '', exitCode: -1 };
  }
}
