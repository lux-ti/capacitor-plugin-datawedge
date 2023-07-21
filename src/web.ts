import { WebPlugin } from '@capacitor/core';

import type { DataWedgePlugin } from './definitions';

export class DataWedgeWeb extends WebPlugin implements DataWedgePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
