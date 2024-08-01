import { registerPlugin } from '@capacitor/core';

import type { ShellExecPlugin } from './definitions';

const Example = registerPlugin<ShellExecPlugin>('Example', {
  web: () => import('./web').then(m => new m.ShellExecWeb()),
});

export * from './definitions';
export { Example };
