import { PluginListenerHandle, WebPlugin } from '@capacitor/core';
import { DataWedgePlugin, DataWedgeScanResult } from './definitions';


const listenersMap = new Map<string, Set<Function>>();

export class DataWedgeWeb extends WebPlugin implements DataWedgePlugin {

  initVirtual() {
    // @ts-ignore
   globalThis.dw = this
  }

  // @ts-ignore
  addListener(eventName: any, listenerFunc: any): PluginListenerHandle {        
    const l = listenersMap.get(eventName) ?? new Set()

    l.add(listenerFunc)

    listenersMap.set(eventName, l)
    
    return {
      remove: async () => {
        l.delete(listenerFunc)
      }
    }
  }

  protected notifyListeners(eventName: string, data: any): void {
    const l = listenersMap.get(eventName) ?? new Set()    
    
    l.forEach((listener) => listener(data))
  }

  scan(data: Partial<DataWedgeScanResult>) {
    this.notifyListeners('scan_result', {
      labelType: 'EAN13',
      scanData: '1234567890128',
      source: 'scanner',
      ...data
    } as DataWedgeScanResult)
  }

  async createProfile(_options: { name: string; }): Promise<any> {
    console.debug('DW: createProfile');

    this.initVirtual()
  }

  alert(): void {
    console.warn('DW: alert');
  }
}
