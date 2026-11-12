import React from 'react';
import { useTime } from '../../hooks/useTime';

interface DigitalClockProps {
  timeFormat: '12h' | '24h';
  showSeconds: boolean;
  nightMode: boolean;
  isFullScreen?: boolean;
}

export const DigitalClock: React.FC<DigitalClockProps> = ({
  timeFormat,
  showSeconds,
  nightMode,
  isFullScreen = false,
}) => {
  const { hours12, hours24, minutes, seconds, period, dayName, monthName, dayNumber } = useTime();

  const pad = (n: number) => n.toString().padStart(2, '0');
  const displayHours = timeFormat === '12h' ? pad(hours12) : pad(hours24);
  const displayMinutes = pad(minutes);
  const displaySeconds = pad(seconds);

  const primaryColor = nightMode ? '#ff3b30' : '#ffffff';
  const secondaryColor = nightMode ? '#a31b14' : '#8e8e93';
  const accentColor = nightMode ? '#ff453a' : '#ff9f0a';

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        width: '100%',
        height: '100%',
        padding: isFullScreen ? '2rem' : '1rem',
        boxSizing: 'border-box',
        color: primaryColor,
        textAlign: 'center',
      }}
    >
      {/* Date & Weekday Pill */}
      <div
        style={{
          display: 'flex',
          alignItems: 'center',
          gap: '0.5rem',
          fontSize: isFullScreen ? '1.5rem' : '1.1rem',
          fontWeight: 600,
          color: accentColor,
          letterSpacing: '0.04em',
          textTransform: 'uppercase',
          marginBottom: isFullScreen ? '1.2rem' : '0.6rem',
        }}
      >
        <span>{dayName}</span>
        <span style={{ opacity: 0.5 }}>•</span>
        <span>{monthName} {dayNumber}</span>
      </div>

      {/* Main Clock Digits */}
      <div
        style={{
          display: 'flex',
          alignItems: 'baseline',
          justifyContent: 'center',
          fontFamily: "'Inter', -apple-system, BlinkMacSystemFont, sans-serif",
          fontWeight: 800,
          letterSpacing: '-0.04em',
          lineHeight: 0.9,
          fontSize: isFullScreen ? 'clamp(6rem, 18vw, 15rem)' : 'clamp(3.8rem, 9.5vw, 6.8rem)',
          textShadow: nightMode
            ? '0 0 35px rgba(255, 59, 48, 0.45)'
            : '0 8px 32px rgba(0, 0, 0, 0.6)',
        }}
      >
        <span>{displayHours}</span>
        <span
          style={{
            margin: '0 0.08em',
            opacity: seconds % 2 === 0 ? 1 : 0.4,
            transition: 'opacity 0.2s ease',
          }}
        >
          :
        </span>
        <span>{displayMinutes}</span>

        {/* Small seconds / AM-PM badge */}
        <div
          style={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'flex-start',
            marginLeft: '0.2em',
            fontSize: isFullScreen ? '0.28em' : '0.3em',
            fontWeight: 700,
            lineHeight: 1.2,
          }}
        >
          {timeFormat === '12h' && (
            <span
              style={{
                color: secondaryColor,
                letterSpacing: '0.06em',
              }}
            >
              {period}
            </span>
          )}
          {showSeconds && (
            <span
              style={{
                color: accentColor,
                fontFamily: "'JetBrains Mono', monospace",
              }}
            >
              {displaySeconds}
            </span>
          )}
        </div>
      </div>
    </div>
  );
};
