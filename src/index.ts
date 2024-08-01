import { registerPlugin } from '@capacitor/core';

import type { ShellExecPlugin } from './definitions';

const ShellExec = registerPlugin<ShellExecPlugin>('ShellExec', {
  web: () => import('./web').then(m => new m.ShellExecWeb()),
});

export * from './definitions';
export { ShellExec };
