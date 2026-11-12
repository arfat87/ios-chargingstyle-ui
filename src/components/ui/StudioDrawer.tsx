import React from 'react';
import { X, Sparkles, Check } from 'lucide-react';
import type { AppSettings, ClockType, WidgetType, ThemeId } from '../../types';

interface StudioDrawerProps {
  isOpen: boolean;
  onClose: () => void;
  settings: AppSettings;
  onUpdateSettings: (newSettings: Partial<AppSettings>) => void;
}

export const StudioDrawer: React.FC<StudioDrawerProps> = ({
  isOpen,
  onClose,
  settings,
  onUpdateSettings,
}) => {
  if (!isOpen) return null;

  const themes: { id: ThemeId; name: string; color: string }[] = [
    { id: 'minimal-black', name: 'AMOLED Black', color: '#16161a' },
    { id: 'midnight-blue', name: 'Midnight Blue', color: '#0d1b2a' },
    { id: 'cyber-neon', name: 'Cyber Neon', color: '#00f2fe' },
    { id: 'retro-cream', name: 'Vintage Flip', color: '#d4a373' },
    { id: 'red-night', name: 'Apple Red Shift', color: '#ff3b30' },
  ];

  const clockOptions: { id: ClockType; label: string }[] = [
    { id: 'digital', label: 'Digital Bold' },
    { id: 'analog3d', label: '3D Analog (R3F)' },
    { id: 'flip', label: 'Retro Flip 3D' },
    { id: 'world', label: 'World Clocks' },
  ];

  const widgetOptions: { id: WidgetType; label: string }[] = [
    { id: 'weather', label: 'Weather Hub' },
    { id: 'calendar', label: 'Calendar & Agenda' },
    { id: 'battery', label: 'Battery & Watts' },
    { id: 'music', label: 'Music Studio' },
    { id: 'photo', label: 'Photo Frame' },
    { id: 'reminders', label: 'Bedside Reminders' },
  ];

  return (
    <div
      style={{
        position: 'fixed',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        backgroundColor: 'rgba(0, 0, 0, 0.75)',
        backdropFilter: 'blur(16px)',
        zIndex: 100,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        padding: '1.5rem',
        boxSizing: 'border-box',
      }}
      onClick={onClose}
    >
      <div
        onClick={(e) => e.stopPropagation()}
        style={{
          width: '100%',
          maxWidth: '680px',
          maxHeight: '90vh',
          backgroundColor: '#16181f',
          borderRadius: '28px',
          border: '1px solid rgba(255, 255, 255, 0.15)',
          boxShadow: '0 25px 60px rgba(0, 0, 0, 0.8)',
          display: 'flex',
          flexDirection: 'column',
          overflow: 'hidden',
          color: '#ffffff',
          fontFamily: "'Inter', sans-serif",
        }}
      >
        {/* Header */}
        <div
          style={{
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'center',
            padding: '1.4rem 1.8rem',
            borderBottom: '1px solid rgba(255, 255, 255, 0.1)',
          }}
        >
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.6rem' }}>
            <Sparkles size={20} color="#ff9f0a" />
            <h2 style={{ margin: 0, fontSize: '1.25rem', fontWeight: 800 }}>
              StandBy+ Customization Studio
            </h2>
          </div>
          <button
            onClick={onClose}
            style={{
              background: 'rgba(255, 255, 255, 0.1)',
              border: 'none',
              color: '#ffffff',
              borderRadius: '50%',
              width: '36px',
              height: '36px',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              cursor: 'pointer',
            }}
          >
            <X size={18} />
          </button>
        </div>

        {/* Content Body */}
        <div
          style={{
            padding: '1.6rem 1.8rem',
            overflowY: 'auto',
            display: 'flex',
            flexDirection: 'column',
            gap: '1.6rem',
          }}
        >
          {/* Clock Style Selection */}
          <div>
            <div style={{ fontSize: '0.88rem', fontWeight: 700, color: '#8e8e93', marginBottom: '0.7rem' }}>
              PRIMARY CLOCK ENGINE
            </div>
            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(2, 1fr)', gap: '0.6rem' }}>
              {clockOptions.map((opt) => (
                <button
                  key={opt.id}
                  onClick={() => onUpdateSettings({ clockType: opt.id })}
                  style={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'space-between',
                    padding: '0.8rem 1rem',
                    borderRadius: '16px',
                    backgroundColor:
                      settings.clockType === opt.id ? 'rgba(255, 159, 10, 0.2)' : 'rgba(255, 255, 255, 0.05)',
                    border:
                      settings.clockType === opt.id
                        ? '1px solid rgba(255, 159, 10, 0.5)'
                        : '1px solid rgba(255, 255, 255, 0.08)',
                    color: settings.clockType === opt.id ? '#ff9f0a' : '#ffffff',
                    fontSize: '0.9rem',
                    fontWeight: 700,
                    cursor: 'pointer',
                  }}
                >
                  <span>{opt.label}</span>
                  {settings.clockType === opt.id && <Check size={16} />}
                </button>
              ))}
            </div>
          </div>

          {/* Right Widget Selection */}
          <div>
            <div style={{ fontSize: '0.88rem', fontWeight: 700, color: '#8e8e93', marginBottom: '0.7rem' }}>
              SMART STACK COMPANION WIDGET
            </div>
            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: '0.6rem' }}>
              {widgetOptions.map((opt) => (
                <button
                  key={opt.id}
                  onClick={() => onUpdateSettings({ rightWidget: opt.id })}
                  style={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'space-between',
                    padding: '0.75rem 0.9rem',
                    borderRadius: '16px',
                    backgroundColor:
                      settings.rightWidget === opt.id ? 'rgba(48, 209, 88, 0.2)' : 'rgba(255, 255, 255, 0.05)',
                    border:
                      settings.rightWidget === opt.id
                        ? '1px solid rgba(48, 209, 88, 0.5)'
                        : '1px solid rgba(255, 255, 255, 0.08)',
                    color: settings.rightWidget === opt.id ? '#30d158' : '#ffffff',
                    fontSize: '0.85rem',
                    fontWeight: 700,
                    cursor: 'pointer',
                  }}
                >
                  <span>{opt.label}</span>
                  {settings.rightWidget === opt.id && <Check size={14} />}
                </button>
              ))}
            </div>
          </div>

          {/* Theme Palette */}
          <div>
            <div style={{ fontSize: '0.88rem', fontWeight: 700, color: '#8e8e93', marginBottom: '0.7rem' }}>
              AMBIENT THEMES
            </div>
            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(5, 1fr)', gap: '0.6rem' }}>
              {themes.map((th) => (
                <button
                  key={th.id}
                  onClick={() => onUpdateSettings({ theme: th.id })}
                  style={{
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    gap: '0.4rem',
                    padding: '0.7rem 0.4rem',
                    borderRadius: '16px',
                    backgroundColor: settings.theme === th.id ? 'rgba(255, 255, 255, 0.15)' : 'rgba(255, 255, 255, 0.05)',
                    border: settings.theme === th.id ? '2px solid #ffffff' : '1px solid rgba(255, 255, 255, 0.08)',
                    cursor: 'pointer',
                  }}
                >
                  <div
                    style={{
                      width: '28px',
                      height: '28px',
                      borderRadius: '50%',
                      backgroundColor: th.color,
                      border: '1px solid rgba(255, 255, 255, 0.3)',
                    }}
                  />
                  <span style={{ fontSize: '0.7rem', fontWeight: 600, color: '#ffffff', textAlign: 'center' }}>
                    {th.name}
                  </span>
                </button>
              ))}
            </div>
          </div>

          {/* Time & OLED Preferences */}
          <div
            style={{
              display: 'flex',
              flexDirection: 'column',
              gap: '0.8rem',
              backgroundColor: 'rgba(255, 255, 255, 0.04)',
              padding: '1.2rem',
              borderRadius: '20px',
            }}
          >
            {/* 12h / 24h Toggle */}
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
              <span style={{ fontSize: '0.9rem', fontWeight: 600 }}>24-Hour Military Time</span>
              <button
                onClick={() =>
                  onUpdateSettings({ timeFormat: settings.timeFormat === '24h' ? '12h' : '24h' })
                }
                style={{
                  padding: '0.4rem 0.9rem',
                  borderRadius: '20px',
                  backgroundColor: settings.timeFormat === '24h' ? '#0a84ff' : 'rgba(255, 255, 255, 0.15)',
                  color: '#ffffff',
                  border: 'none',
                  fontSize: '0.82rem',
                  fontWeight: 700,
                  cursor: 'pointer',
                }}
              >
                {settings.timeFormat === '24h' ? '24H ON' : '12H (AM/PM)'}
              </button>
            </div>

            {/* Show Seconds */}
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
              <span style={{ fontSize: '0.9rem', fontWeight: 600 }}>Show Live Seconds</span>
              <button
                onClick={() => onUpdateSettings({ showSeconds: !settings.showSeconds })}
                style={{
                  padding: '0.4rem 0.9rem',
                  borderRadius: '20px',
                  backgroundColor: settings.showSeconds ? '#0a84ff' : 'rgba(255, 255, 255, 0.15)',
                  color: '#ffffff',
                  border: 'none',
                  fontSize: '0.82rem',
                  fontWeight: 700,
                  cursor: 'pointer',
                }}
              >
                {settings.showSeconds ? 'ACTIVE' : 'OFF'}
              </button>
            </div>

            {/* OLED Burn-in Shift */}
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
              <div>
                <div style={{ fontSize: '0.9rem', fontWeight: 600 }}>AMOLED Burn-In Protection</div>
                <div style={{ fontSize: '0.72rem', color: '#8e8e93' }}>
                  Micro pixel-shifting (+/- 2px shift every 60s)
                </div>
              </div>
              <button
                onClick={() => onUpdateSettings({ burnInProtection: !settings.burnInProtection })}
                style={{
                  padding: '0.4rem 0.9rem',
                  borderRadius: '20px',
                  backgroundColor: settings.burnInProtection ? '#30d158' : 'rgba(255, 255, 255, 0.15)',
                  color: '#ffffff',
                  border: 'none',
                  fontSize: '0.82rem',
                  fontWeight: 700,
                  cursor: 'pointer',
                }}
              >
                {settings.burnInProtection ? 'PROTECTED' : 'DISABLED'}
              </button>
            </div>

            {/* Night Mode Brightness Slider */}
            <div>
              <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '0.4rem' }}>
                <span style={{ fontSize: '0.9rem', fontWeight: 600 }}>Night Mode Dimming Level</span>
                <span style={{ fontSize: '0.82rem', fontWeight: 700, color: '#ff3b30' }}>
                  {settings.nightModeBrightness}%
                </span>
              </div>
              <input
                type="range"
                min="15"
                max="100"
                value={settings.nightModeBrightness}
                onChange={(e) => onUpdateSettings({ nightModeBrightness: Number(e.target.value) })}
                style={{ width: '100%', accentColor: '#ff3b30', cursor: 'pointer' }}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
