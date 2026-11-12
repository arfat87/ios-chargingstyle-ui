import React from 'react';
import {
  BatteryCharging,
  Battery,
  Moon,
  Sun,
  Volume2,
  VolumeX,
  Sliders,
  Maximize2,
  Minimize2,
  Smartphone,
  Layers,
  Code2,
} from 'lucide-react';
import type { AppSettings, StandByLayout } from '../../types';
import { playHapticClick, playNightChime, playChargingChime } from '../../utils/audioSynth';

interface ControlBarProps {
  settings: AppSettings;
  onUpdateSettings: (newSettings: Partial<AppSettings>) => void;
  onOpenStudio: () => void;
  onOpenAndroidModal: () => void;
  isFullscreen: boolean;
  onToggleFullscreen: () => void;
}

export const ControlBar: React.FC<ControlBarProps> = ({
  settings,
  onUpdateSettings,
  onOpenStudio,
  onOpenAndroidModal,
  isFullscreen,
  onToggleFullscreen,
}) => {
  const handleToggleCharging = () => {
    const next = !settings.isCharging;
    playHapticClick(settings.soundEnabled);
    if (next) {
      playChargingChime(settings.soundEnabled);
    }
    onUpdateSettings({ isCharging: next });
  };

  const handleToggleNight = () => {
    const next = !settings.nightMode;
    playNightChime(next, settings.soundEnabled);
    onUpdateSettings({ nightMode: next });
  };

  const cycleLayout = () => {
    playHapticClick(settings.soundEnabled);
    const layouts: StandByLayout[] = ['split', 'full-clock', 'full-photo'];
    const currentIndex = layouts.indexOf(settings.currentLayout);
    const nextLayout = layouts[(currentIndex + 1) % layouts.length];
    onUpdateSettings({ currentLayout: nextLayout });
  };

  const textColor = settings.nightMode ? '#ff3b30' : '#ffffff';
  const barBg = settings.nightMode
    ? 'rgba(25, 4, 4, 0.78)'
    : 'rgba(20, 22, 28, 0.75)';
  const border = settings.nightMode
    ? '1px solid rgba(255, 59, 48, 0.3)'
    : '1px solid rgba(255, 255, 255, 0.15)';

  return (
    <div
      style={{
        position: 'absolute',
        bottom: '1rem',
        left: '50%',
        transform: 'translateX(-50%)',
        display: 'flex',
        alignItems: 'center',
        gap: '0.4rem',
        padding: '0.4rem 0.7rem',
        borderRadius: '32px',
        backgroundColor: barBg,
        backdropFilter: 'blur(20px)',
        border,
        boxShadow: '0 12px 36px rgba(0,0,0,0.6)',
        zIndex: 50,
        userSelect: 'none',
      }}
    >
      {/* Charging Simulator Button */}
      <button
        onClick={handleToggleCharging}
        title={settings.isCharging ? 'Disconnect Charger' : 'Connect Charger'}
        style={{
          display: 'flex',
          alignItems: 'center',
          gap: '0.4rem',
          padding: '0.45rem 0.85rem',
          borderRadius: '24px',
          border: 'none',
          backgroundColor: settings.isCharging
            ? (settings.nightMode ? 'rgba(255, 59, 48, 0.25)' : 'rgba(48, 209, 88, 0.22)')
            : 'rgba(255, 255, 255, 0.08)',
          color: settings.isCharging
            ? (settings.nightMode ? '#ff3b30' : '#30d158')
            : textColor,
          fontSize: '0.8rem',
          fontWeight: 700,
          cursor: 'pointer',
          transition: 'all 0.2s ease',
        }}
      >
        {settings.isCharging ? <BatteryCharging size={16} /> : <Battery size={16} />}
        <span>{settings.isCharging ? 'Charging' : 'Plug In'}</span>
      </button>

      {/* Night Mode Toggle */}
      <button
        onClick={handleToggleNight}
        title={settings.nightMode ? 'Exit Night Mode' : 'Enter Red Night Mode'}
        style={{
          display: 'flex',
          alignItems: 'center',
          gap: '0.35rem',
          padding: '0.45rem 0.75rem',
          borderRadius: '24px',
          border: 'none',
          backgroundColor: settings.nightMode
            ? 'rgba(255, 59, 48, 0.25)'
            : 'rgba(255, 255, 255, 0.08)',
          color: settings.nightMode ? '#ff3b30' : textColor,
          fontSize: '0.8rem',
          fontWeight: 700,
          cursor: 'pointer',
          transition: 'all 0.2s ease',
        }}
      >
        {settings.nightMode ? <Sun size={16} /> : <Moon size={16} />}
        <span>{settings.nightMode ? 'Red Mode' : 'Night'}</span>
      </button>

      {/* Cycle Layout View */}
      <button
        onClick={cycleLayout}
        title={`Current Layout: ${settings.currentLayout}. Tap to switch`}
        style={{
          display: 'flex',
          alignItems: 'center',
          gap: '0.35rem',
          padding: '0.45rem 0.75rem',
          borderRadius: '24px',
          border: 'none',
          backgroundColor: 'rgba(255, 255, 255, 0.08)',
          color: textColor,
          fontSize: '0.8rem',
          fontWeight: 700,
          cursor: 'pointer',
        }}
      >
        <Layers size={15} />
        <span style={{ textTransform: 'capitalize' }}>
          {settings.currentLayout === 'split'
            ? 'Widgets'
            : settings.currentLayout === 'full-clock'
            ? 'Full Clock'
            : 'Photo'}
        </span>
      </button>

      {/* Sound Toggle */}
      <button
        onClick={() => {
          playHapticClick(!settings.soundEnabled);
          onUpdateSettings({ soundEnabled: !settings.soundEnabled });
        }}
        title={settings.soundEnabled ? 'Mute Sounds' : 'Enable Audio Chimes'}
        style={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          width: '34px',
          height: '34px',
          borderRadius: '50%',
          border: 'none',
          backgroundColor: 'rgba(255, 255, 255, 0.08)',
          color: textColor,
          cursor: 'pointer',
        }}
      >
        {settings.soundEnabled ? <Volume2 size={16} /> : <VolumeX size={16} />}
      </button>

      {/* Bezel / Stand Mockup Toggle */}
      <button
        onClick={() => {
          playHapticClick(settings.soundEnabled);
          onUpdateSettings({ showPhoneBezel: !settings.showPhoneBezel });
        }}
        title="Toggle Phone Stand Mockup"
        style={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          width: '34px',
          height: '34px',
          borderRadius: '50%',
          border: 'none',
          backgroundColor: settings.showPhoneBezel ? 'rgba(255, 255, 255, 0.22)' : 'rgba(255, 255, 255, 0.08)',
          color: textColor,
          cursor: 'pointer',
        }}
      >
        <Smartphone size={16} />
      </button>

      {/* Android Blueprint Modal Button */}
      <button
        onClick={() => {
          playHapticClick(settings.soundEnabled);
          onOpenAndroidModal();
        }}
        title="Android App Blueprint & Native Architecture"
        style={{
          display: 'flex',
          alignItems: 'center',
          gap: '0.35rem',
          padding: '0.45rem 0.75rem',
          borderRadius: '24px',
          border: 'none',
          backgroundColor: 'rgba(10, 132, 255, 0.2)',
          color: settings.nightMode ? '#ff3b30' : '#2997ff',
          fontSize: '0.8rem',
          fontWeight: 700,
          cursor: 'pointer',
        }}
      >
        <Code2 size={15} />
        <span>Android Arch</span>
      </button>

      {/* Studio Drawer / Settings */}
      <button
        onClick={() => {
          playHapticClick(settings.soundEnabled);
          onOpenStudio();
        }}
        title="Open Studio Settings"
        style={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          width: '34px',
          height: '34px',
          borderRadius: '50%',
          border: 'none',
          backgroundColor: 'rgba(255, 255, 255, 0.08)',
          color: textColor,
          cursor: 'pointer',
        }}
      >
        <Sliders size={16} />
      </button>

      {/* Fullscreen Button */}
      <button
        onClick={onToggleFullscreen}
        title="Fullscreen Mode"
        style={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          width: '34px',
          height: '34px',
          borderRadius: '50%',
          border: 'none',
          backgroundColor: 'rgba(255, 255, 255, 0.08)',
          color: textColor,
          cursor: 'pointer',
        }}
      >
        {isFullscreen ? <Minimize2 size={16} /> : <Maximize2 size={16} />}
      </button>
    </div>
  );
};
