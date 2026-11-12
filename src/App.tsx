import React, { useState, useEffect } from 'react';
import type { AppSettings } from './types';
import { PhoneFrameSimulator } from './components/views/PhoneFrameSimulator';
import { StandByLandscapeView } from './components/views/StandByLandscapeView';
import { ControlBar } from './components/ui/ControlBar';
import { ChargingEdgeGlow } from './components/ui/ChargingEdgeGlow';
import { StudioDrawer } from './components/ui/StudioDrawer';
import { AndroidIntegrationModal } from './components/ui/AndroidIntegrationModal';
import { AmbientStars3D } from './components/3d/AmbientStars3D';
import { usePixelShift } from './hooks/usePixelShift';

export const App: React.FC = () => {
  const [settings, setSettings] = useState<AppSettings>({
    timeFormat: '12h',
    showSeconds: true,
    nightMode: false,
    nightModeBrightness: 65,
    burnInProtection: true,
    isCharging: true,
    batteryLevel: 84,
    chargingWatts: 45,
    theme: 'minimal-black',
    soundEnabled: true,
    currentLayout: 'split',
    leftWidget: 'weather',
    rightWidget: 'calendar',
    clockType: 'digital',
    showPhoneBezel: true,
  });

  const [isStudioOpen, setIsStudioOpen] = useState(false);
  const [isAndroidModalOpen, setIsAndroidModalOpen] = useState(false);
  const [isFullscreen, setIsFullscreen] = useState(false);

  const pixelOffset = usePixelShift(settings.burnInProtection);

  const handleUpdateSettings = (newSettings: Partial<AppSettings>) => {
    setSettings((prev) => ({ ...prev, ...newSettings }));
  };

  const handleToggleFullscreen = () => {
    if (!document.fullscreenElement) {
      document.documentElement.requestFullscreen().then(() => setIsFullscreen(true)).catch(() => {});
    } else {
      document.exitFullscreen().then(() => setIsFullscreen(false)).catch(() => {});
    }
  };

  useEffect(() => {
    const handleFsChange = () => {
      setIsFullscreen(!!document.fullscreenElement);
    };
    document.addEventListener('fullscreenchange', handleFsChange);
    return () => document.removeEventListener('fullscreenchange', handleFsChange);
  }, []);

  // Theme background color resolution
  const getThemeBackground = () => {
    if (settings.nightMode) return '#080101';
    switch (settings.theme) {
      case 'midnight-blue':
        return '#040914';
      case 'cyber-neon':
        return '#020d18';
      case 'retro-cream':
        return '#12100d';
      case 'red-night':
        return '#120202';
      case 'minimal-black':
      default:
        return '#000000';
    }
  };

  return (
    <div
      style={{
        width: '100vw',
        height: '100vh',
        backgroundColor: getThemeBackground(),
        position: 'relative',
        overflow: 'hidden',
        transition: 'background-color 0.5s ease',
      }}
    >
      <PhoneFrameSimulator
        showBezel={settings.showPhoneBezel && !isFullscreen}
        nightMode={settings.nightMode}
        isCharging={settings.isCharging}
      >
        {/* Subtle 3D Ambient Stars / Orbs Backdrop */}
        <AmbientStars3D nightMode={settings.nightMode} />

        {/* Dynamic Charging Edge Lighting Indicator */}
        <ChargingEdgeGlow isCharging={settings.isCharging} nightMode={settings.nightMode} />

        {/* Main Smart Landscape Display View */}
        <StandByLandscapeView
          settings={settings}
          onUpdateSettings={handleUpdateSettings}
          pixelOffset={pixelOffset}
        />

        {/* Floating Minimalist Control Bar */}
        <ControlBar
          settings={settings}
          onUpdateSettings={handleUpdateSettings}
          onOpenStudio={() => setIsStudioOpen(true)}
          onOpenAndroidModal={() => setIsAndroidModalOpen(true)}
          isFullscreen={isFullscreen}
          onToggleFullscreen={handleToggleFullscreen}
        />
      </PhoneFrameSimulator>

      {/* Customization Studio Drawer */}
      <StudioDrawer
        isOpen={isStudioOpen}
        onClose={() => setIsStudioOpen(false)}
        settings={settings}
        onUpdateSettings={handleUpdateSettings}
      />

      {/* Android Blueprint & Native Architecture Modal */}
      <AndroidIntegrationModal
        isOpen={isAndroidModalOpen}
        onClose={() => setIsAndroidModalOpen(false)}
      />
    </div>
  );
};

export default App;
