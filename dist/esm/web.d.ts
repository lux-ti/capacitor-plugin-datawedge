import { PluginListenerHandle, WebPlugin } from '@capacitor/core';
import { DataWedgePlugin, DataWedgeScanResult } from './definitions';
export declare class DataWedgeWeb extends WebPlugin implements DataWedgePlugin {
    initVirtual(): void;
    addListener(eventName: any, listenerFunc: any): PluginListenerHandle;
    protected notifyListeners(eventName: string, data: any): void;
    scan(data: Partial<DataWedgeScanResult>): void;
    createProfile(_options: {
        name: string;
    }): Promise<any>;
    alert(): void;
}
