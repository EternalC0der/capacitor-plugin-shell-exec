import { WebPlugin } from '@capacitor/core';
import { ShellExecPlugin } from './definitions';

export class ShellExecWeb extends WebPlugin implements ShellExecPlugin {
  constructor() {
    super({
      name: 'ShellExec',
      platforms: ['web'],
    });
  }

  async execute(options: { command: string }): Promise<{ output: string }> {
    console.log('Execute command:', options.command);
    return { output: 'Shell execution is not supported on the web platform.' };
  }
}

const ShellExec = new ShellExecWeb();

export { ShellExec };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(ShellExec);
