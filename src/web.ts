import { WebPlugin } from '@capacitor/core';
import { ShellExecPlugin } from './definitions';

export class ShellExecWeb extends WebPlugin implements ShellExecPlugin {
  constructor() {
    super({
      name: 'ShellExec',
      platforms: ['web'],
    });
  }

  async execute(options: { command: string }): Promise<{ output: string; exitCode: number }> {
    // To suppress the unused parameter warning
    void options;

    console.log('This function is not available in the web');
    return { output: '', exitCode: -1 };
  }
}

const ShellExec = new ShellExecWeb();

export { ShellExec };
