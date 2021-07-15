declare module "@capacitor/core" {
  interface PluginRegistry {
    FileIntents: FileIntentsPlugin;
  }
}

export interface FileIntentsPlugin {
  checkIntentReceived(): Promise<{data: string }>;
}
