import { WebPlugin } from '@capacitor/core';
import { DataWedgePlugin, DataWedgeScanResult } from './definitions';

export class DataWedgeWeb extends WebPlugin implements DataWedgePlugin {
  initVirtual() {
    const reader = window.open('', '_blank', 'location=yes,height=570,width=520,scrollbars=yes,status=yes');

    if (reader) {
      const form = reader.document.createElement('form')
      const data = reader.document.createElement('input')
      const btn = reader.document.createElement('button')

      btn.innerText = 'Enviar'

      const title = reader.document.createElement('h1')

      title.innerText = 'Leitor de códigos'

      form.appendChild(title)
      form.appendChild(data)
      form.appendChild(btn)

      reader.document.body.appendChild(form)
      
      form.addEventListener('submit', (e) => {
        e.preventDefault()

        this.notifyListeners('scan_result', {
          labelType: 'EAN13',
          scanData: data.value,
          source: 'VIRTUAL'
        } as DataWedgeScanResult)
        form.reset()
      })
    }
  }

  async createProfile(options: { name: string; }): Promise<any> {
    console.debug('DW: createProfile', options);

    if (window.confirm('Abrir leitor de códigos virtual?')) {
      this.initVirtual()
    }
  }

  alert(): void {
    console.warn('DW: alert');
  }
}
