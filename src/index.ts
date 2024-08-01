import { registerPlugin } from '@capacitor/core';
import type { ShellExecPlugin } from './definitions';
import { ShellExecWeb } from './web';

const ShellExec = registerPlugin<ShellExecPlugin>('ShellExec', {
  web: () => new ShellExecWeb(),
});

export * from './definitions';
export { ShellExec };
