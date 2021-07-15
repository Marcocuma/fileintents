import { WebPlugin, registerWebPlugin } from '@capacitor/core';

import type { FileIntentsPlugin } from './definitions';

export class FileIntentsWeb extends WebPlugin implements FileIntentsPlugin {

  constructor() {
    super({
      name: 'FileIntents',
      platforms: ['web']
    });
  }

  checkIntentReceived(): Promise<{ data: string  }> {
    return Promise.resolve({ data: '' });
  }

}
const FileIntents = new FileIntentsWeb();

export { FileIntents };

registerWebPlugin(FileIntents);

