import type { AppSettings, ClockType, WidgetType } from '../../types';
import { LiquidGlassCard } from '../ui/LiquidGlassCard';
import { DigitalClock } from '../clocks/DigitalClock';
import { FlipClock } from '../clocks/FlipClock';
import { WorldClock } from '../clocks/WorldClock';
import { AnalogClock3D } from '../3d/AnalogClock3D';
import { WeatherWidget } from '../widgets/WeatherWidget';
import { CalendarWidget } from '../widgets/CalendarWidget';
import { BatteryWidget } from '../widgets/BatteryWidget';
import { MusicWidget } from '../widgets/MusicWidget';
import { PhotoFrameWidget } from '../widgets/PhotoFrameWidget';
import { RemindersWidget } from '../widgets/RemindersWidget';
import { playHapticClick } from '../../utils/audioSynth';
import { ChevronUp, ChevronDown } from 'lucide-react';

interface StandByLandscapeViewProps {
  settings: AppSettings;
  onUpdateSettings: (newSettings: Partial<AppSettings>) => void;
  pixelOffset: { x: number; y: number };
}

export const StandByLandscapeView: React.FC<StandByLandscapeViewProps> = ({
  settings,
  onUpdateSettings,
  pixelOffset,
}) => {
  const clockCycle: ClockType[] = ['digital', 'analog3d', 'flip', 'world'];
  const widgetCycle: WidgetType[] = ['weather', 'calendar', 'battery', 'music', 'photo', 'reminders'];

  const cycleClockNext = () => {
    playHapticClick(settings.soundEnabled);
    const idx = clockCycle.indexOf(settings.clockType);
    onUpdateSettings({ clockType: clockCycle[(idx + 1) % clockCycle.length] });
  };

  const cycleClockPrev = () => {
    playHapticClick(settings.soundEnabled);
    const idx = clockCycle.indexOf(settings.clockType);
    onUpdateSettings({ clockType: clockCycle[(idx - 1 + clockCycle.length) % clockCycle.length] });
  };

  const cycleWidgetNext = () => {
    playHapticClick(settings.soundEnabled);
    const idx = widgetCycle.indexOf(settings.rightWidget);
    onUpdateSettings({ rightWidget: widgetCycle[(idx + 1) % widgetCycle.length] });
  };

  const cycleWidgetPrev = () => {
    playHapticClick(settings.soundEnabled);
    const idx = widgetCycle.indexOf(settings.rightWidget);
    onUpdateSettings({ rightWidget: widgetCycle[(idx - 1 + widgetCycle.length) % widgetCycle.length] });
  };

  const renderClock = (isFullScreen = false) => {
    switch (settings.clockType) {
      case 'analog3d':
        return <AnalogClock3D nightMode={settings.nightMode} />;
      case 'flip':
        return (
          <FlipClock
            timeFormat={settings.timeFormat}
            nightMode={settings.nightMode}
            soundEnabled={settings.soundEnabled}
            isFullScreen={isFullScreen}
          />
        );
      case 'world':
        return <WorldClock nightMode={settings.nightMode} timeFormat={settings.timeFormat} />;
      case 'digital':
      default:
        return (
          <DigitalClock
            timeFormat={settings.timeFormat}
            showSeconds={settings.showSeconds}
            nightMode={settings.nightMode}
            isFullScreen={isFullScreen}
          />
        );
    }
  };

  const renderWidget = () => {
    switch (settings.rightWidget) {
      case 'calendar':
        return <CalendarWidget nightMode={settings.nightMode} />;
      case 'battery':
        return (
          <BatteryWidget
            nightMode={settings.nightMode}
            batteryLevel={settings.batteryLevel}
            isCharging={settings.isCharging}
            chargingWatts={settings.chargingWatts}
          />
        );
      case 'music':
        return <MusicWidget nightMode={settings.nightMode} />;
      case 'photo':
        return <PhotoFrameWidget nightMode={settings.nightMode} timeFormat={settings.timeFormat} />;
      case 'reminders':
        return <RemindersWidget nightMode={settings.nightMode} soundEnabled={settings.soundEnabled} />;
      case 'weather':
      default:
        return <WeatherWidget nightMode={settings.nightMode} />;
    }
  };

  // Night Mode opacity and brightness dimming
  const dimOpacity = settings.nightMode ? settings.nightModeBrightness / 100 : 1;

  // Render Full Screen Clock Mode
  if (settings.currentLayout === 'full-clock') {
    return (
      <div
        style={{
          width: '100%',
          height: '100%',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          opacity: dimOpacity,
          transform: `translate(${pixelOffset.x}px, ${pixelOffset.y}px)`,
          transition: 'opacity 0.4s ease',
        }}
      >
        <LiquidGlassCard
          nightMode={settings.nightMode}
          style={{ width: '92%', height: '84%', maxWidth: '1200px' }}
        >
          {renderClock(true)}
        </LiquidGlassCard>
      </div>
    );
  }

  // Render Full Screen Photo Mode
  if (settings.currentLayout === 'full-photo') {
    return (
      <div
        style={{
          width: '100%',
          height: '100%',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          opacity: dimOpacity,
          transform: `translate(${pixelOffset.x}px, ${pixelOffset.y}px)`,
          transition: 'opacity 0.4s ease',
        }}
      >
        <div style={{ width: '92%', height: '84%', maxWidth: '1200px', borderRadius: '32px', overflow: 'hidden' }}>
          <PhotoFrameWidget nightMode={settings.nightMode} timeFormat={settings.timeFormat} />
        </div>
      </div>
    );
  }

  // Default: Dual Split Stack Layout (iOS 17 StandBy Signature Mode)
  return (
    <div
      style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        gap: 'clamp(1rem, 2.5vw, 2.5rem)',
        width: '100%',
        height: '100%',
        padding: 'clamp(1rem, 2vw, 2rem)',
        boxSizing: 'border-box',
        opacity: dimOpacity,
        transform: `translate(${pixelOffset.x}px, ${pixelOffset.y}px)`,
        transition: 'opacity 0.4s ease',
      }}
    >
      {/* Left Slot: Clock Studio Stack */}
      <div
        style={{
          position: 'relative',
          flex: 1,
          maxWidth: '540px',
          height: 'min(82vh, 460px)',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
        }}
      >
        {/* Cycle Up/Down Arrows */}
        <div
          style={{
            position: 'absolute',
            right: '-16px',
            top: '50%',
            transform: 'translateY(-50%)',
            display: 'flex',
            flexDirection: 'column',
            gap: '6px',
            zIndex: 20,
          }}
        >
          <button
            onClick={cycleClockPrev}
            title="Previous Clock"
            style={{
              background: 'rgba(0,0,0,0.5)',
              border: '1px solid rgba(255,255,255,0.15)',
              color: settings.nightMode ? '#ff3b30' : '#ffffff',
              borderRadius: '50%',
              width: '26px',
              height: '26px',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              cursor: 'pointer',
              backdropFilter: 'blur(10px)',
            }}
          >
            <ChevronUp size={14} />
          </button>
          <button
            onClick={cycleClockNext}
            title="Next Clock"
            style={{
              background: 'rgba(0,0,0,0.5)',
              border: '1px solid rgba(255,255,255,0.15)',
              color: settings.nightMode ? '#ff3b30' : '#ffffff',
              borderRadius: '50%',
              width: '26px',
              height: '26px',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              cursor: 'pointer',
              backdropFilter: 'blur(10px)',
            }}
          >
            <ChevronDown size={14} />
          </button>
        </div>

        <LiquidGlassCard nightMode={settings.nightMode}>
          {renderClock(false)}
        </LiquidGlassCard>
      </div>

      {/* Right Slot: Smart Widgets Stack */}
      <div
        style={{
          position: 'relative',
          flex: 1,
          maxWidth: '540px',
          height: 'min(82vh, 460px)',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
        }}
      >
        {/* Cycle Up/Down Arrows */}
        <div
          style={{
            position: 'absolute',
            right: '-16px',
            top: '50%',
            transform: 'translateY(-50%)',
            display: 'flex',
            flexDirection: 'column',
            gap: '6px',
            zIndex: 20,
          }}
        >
          <button
            onClick={cycleWidgetPrev}
            title="Previous Widget"
            style={{
              background: 'rgba(0,0,0,0.5)',
              border: '1px solid rgba(255,255,255,0.15)',
              color: settings.nightMode ? '#ff3b30' : '#ffffff',
              borderRadius: '50%',
              width: '26px',
              height: '26px',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              cursor: 'pointer',
              backdropFilter: 'blur(10px)',
            }}
          >
            <ChevronUp size={14} />
          </button>
          <button
            onClick={cycleWidgetNext}
            title="Next Widget"
            style={{
              background: 'rgba(0,0,0,0.5)',
              border: '1px solid rgba(255,255,255,0.15)',
              color: settings.nightMode ? '#ff3b30' : '#ffffff',
              borderRadius: '50%',
              width: '26px',
              height: '26px',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              cursor: 'pointer',
              backdropFilter: 'blur(10px)',
            }}
          >
            <ChevronDown size={14} />
          </button>
        </div>

        <LiquidGlassCard nightMode={settings.nightMode}>
          {renderWidget()}
        </LiquidGlassCard>
      </div>
    </div>
  );
};
