import { PluginListenerHandle } from "@capacitor/core";

export interface DataWedgeCommandResult {
  result: string;
  command: string;
  info: string;
  commandId: string;
}

export interface DataWedgeScanResult {
  scanData: string,
  labelType: string,
  source: string
}

type DataWedgeEvents = {
  ready: {}
  action_result: DataWedgeCommandResult;
  scan_result: DataWedgeScanResult;
}

export interface DataWedgePlugin {
  
  addListener<E extends keyof DataWedgeEvents>(eventName: E, listenerFunc: (event: DataWedgeEvents[E]) => void): PluginListenerHandle;
  removeAllListeners(): void;

  createProfile(options: { name: string }): Promise<any>;
  alert():void
}