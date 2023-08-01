import { WebPlugin } from '@capacitor/core';
const listenersMap = new Map();
export class DataWedgeWeb extends WebPlugin {
    initVirtual() {
        // @ts-ignore
        globalThis.dw = this;
    }
    // @ts-ignore
    addListener(eventName, listenerFunc) {
        var _a;
        const l = (_a = listenersMap.get(eventName)) !== null && _a !== void 0 ? _a : new Set();
        l.add(listenerFunc);
        listenersMap.set(eventName, l);
        return {
            remove: async () => {
                l.delete(listenerFunc);
            }
        };
    }
    notifyListeners(eventName, data) {
        var _a;
        const l = (_a = listenersMap.get(eventName)) !== null && _a !== void 0 ? _a : new Set();
        l.forEach((listener) => listener(data));
    }
    scan(data) {
        this.notifyListeners('scan_result', Object.assign({ labelType: 'EAN13', scanData: '1234567890128', source: 'scanner' }, data));
    }
    async createProfile(_options) {
        console.debug('DW: createProfile');
        this.initVirtual();
    }
    alert() {
        console.warn('DW: alert');
    }
}
//# sourceMappingURL=web.js.map