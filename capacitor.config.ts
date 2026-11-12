import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.standbyplus.app',
  appName: 'StandBy+',
  webDir: 'dist',
  bundledWebRuntime: false,
  plugins: {
    SplashScreen: {
      launchShowDuration: 0,
      launchAutoHide: true,
      backgroundColor: '#000000',
    },
    StatusBar: {
      style: 'dark',
      backgroundColor: '#000000',
      overlay: true,
    },
  },
  android: {
    allowMixedContent: true,
    backgroundColor: '#000000',
    buildOptions: {
      keystorePath: undefined,
      keystoreAlias: undefined,
    },
  },
};

export default config;
