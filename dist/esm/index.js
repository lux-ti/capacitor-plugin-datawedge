import { registerPlugin } from '@capacitor/core';
const DataWedge = registerPlugin('DataWedge', {
    web: () => import('./web').then(m => new m.DataWedgeWeb()),
});
export * from './definitions';
export { DataWedge };
//# sourceMappingURL=index.js.map