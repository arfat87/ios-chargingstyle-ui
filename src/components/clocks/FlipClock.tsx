import React, { useEffect, useState } from 'react';
import { useTime } from '../../hooks/useTime';
import { playFlipTick } from '../../utils/audioSynth';

interface FlipClockProps {
  timeFormat: '12h' | '24h';
  nightMode: boolean;
  soundEnabled: boolean;
  isFullScreen?: boolean;
}

interface FlipUnitProps {
  value: string;
  prevValue: string;
  nightMode: boolean;
  size: 'small' | 'large';
  label?: string;
}

const FlipCard: React.FC<FlipUnitProps> = ({ value, prevValue, nightMode, size, label }) => {
  const [isFlipping, setIsFlipping] = useState(false);

  useEffect(() => {
    if (value !== prevValue) {
      setIsFlipping(true);
      const timer = setTimeout(() => setIsFlipping(false), 550);
      return () => clearTimeout(timer);
    }
  }, [value, prevValue]);

  const isLarge = size === 'large';
  const width = isLarge ? 'clamp(110px, 14vw, 190px)' : 'clamp(74px, 8.5vw, 115px)';
  const height = isLarge ? 'clamp(140px, 18vw, 240px)' : 'clamp(95px, 11vw, 150px)';
  const fontSize = isLarge ? 'clamp(5rem, 12vw, 11rem)' : 'clamp(3.2rem, 7vw, 6.2rem)';

  const cardBg = nightMode ? '#1e0404' : '#18191f';
  const cardBorder = nightMode ? '#420a0a' : '#2b2d38';
  const textColor = nightMode ? '#ff3b30' : '#f5f5f7';
  const dividerColor = nightMode ? '#000000' : '#0d0e12';

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        gap: '0.4rem',
      }}
    >
      <div
        style={{
          position: 'relative',
          width,
          height,
          perspective: '600px',
          userSelect: 'none',
        }}
      >
        {/* Base Static Card Container */}
        <div
          style={{
            position: 'absolute',
            width: '100%',
            height: '100%',
            backgroundColor: cardBg,
            borderRadius: '16px',
            border: `1px solid ${cardBorder}`,
            display: 'flex',
            flexDirection: 'column',
            overflow: 'hidden',
            boxShadow: nightMode
              ? '0 10px 30px rgba(255, 59, 48, 0.15)'
              : '0 12px 35px rgba(0, 0, 0, 0.7)',
          }}
        >
          {/* Top Half */}
          <div
            style={{
              position: 'relative',
              width: '100%',
              height: '50%',
              backgroundColor: cardBg,
              overflow: 'hidden',
              display: 'flex',
              alignItems: 'flex-end',
              justifyContent: 'center',
              borderBottom: `1px solid ${dividerColor}`,
            }}
          >
            <span
              style={{
                fontFamily: "'Bebas Neue', 'Outfit', sans-serif",
                fontSize,
                lineHeight: 1,
                color: textColor,
                transform: 'translateY(50%)',
              }}
            >
              {value}
            </span>
          </div>

          {/* Bottom Half */}
          <div
            style={{
              position: 'relative',
              width: '100%',
              height: '50%',
              backgroundColor: cardBg,
              overflow: 'hidden',
              display: 'flex',
              alignItems: 'flex-start',
              justifyContent: 'center',
            }}
          >
            <span
              style={{
                fontFamily: "'Bebas Neue', 'Outfit', sans-serif",
                fontSize,
                lineHeight: 1,
                color: textColor,
                transform: 'translateY(-50%)',
              }}
            >
              {value}
            </span>
          </div>
        </div>

        {/* 3D Flipping Flap Overlay */}
        {isFlipping && (
          <div
            style={{
              position: 'absolute',
              top: 0,
              left: 0,
              width: '100%',
              height: '50%',
              backgroundColor: cardBg,
              borderRadius: '16px 16px 0 0',
              border: `1px solid ${cardBorder}`,
              borderBottom: `1px solid ${dividerColor}`,
              overflow: 'hidden',
              display: 'flex',
              alignItems: 'flex-end',
              justifyContent: 'center',
              transformOrigin: 'bottom',
              animation: 'flapDown 0.5s cubic-bezier(0.4, 0, 0.2, 1) forwards',
              zIndex: 3,
            }}
          >
            <span
              style={{
                fontFamily: "'Bebas Neue', 'Outfit', sans-serif",
                fontSize,
                lineHeight: 1,
                color: textColor,
                transform: 'translateY(50%)',
              }}
            >
              {prevValue}
            </span>
          </div>
        )}

        {/* Center Split Shadow Pin */}
        <div
          style={{
            position: 'absolute',
            top: '50%',
            left: '-3px',
            right: '-3px',
            height: '2px',
            backgroundColor: dividerColor,
            zIndex: 4,
            boxShadow: '0 1px 3px rgba(0,0,0,0.8)',
          }}
        />
      </div>

      {label && (
        <span
          style={{
            fontSize: '0.75rem',
            fontWeight: 700,
            letterSpacing: '0.12em',
            color: nightMode ? '#a31b14' : '#636366',
            textTransform: 'uppercase',
          }}
        >
          {label}
        </span>
      )}
    </div>
  );
};

export const FlipClock: React.FC<FlipClockProps> = ({
  timeFormat,
  nightMode,
  soundEnabled,
  isFullScreen = false,
}) => {
  const { hours12, hours24, minutes, period } = useTime();

  const pad = (n: number) => n.toString().padStart(2, '0');
  const currentHours = timeFormat === '12h' ? pad(hours12) : pad(hours24);
  const currentMinutes = pad(minutes);

  const [prevHours, setPrevHours] = useState(currentHours);
  const [prevMinutes, setPrevMinutes] = useState(currentMinutes);

  useEffect(() => {
    if (currentMinutes !== prevMinutes) {
      playFlipTick(soundEnabled);
      setPrevMinutes(currentMinutes);
    }
  }, [currentMinutes, prevMinutes, soundEnabled]);

  useEffect(() => {
    if (currentHours !== prevHours) {
      setPrevHours(currentHours);
    }
  }, [currentHours, prevHours]);

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        width: '100%',
        height: '100%',
        padding: '1rem',
        boxSizing: 'border-box',
      }}
    >
      <div
        style={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          gap: isFullScreen ? '1.5rem' : '0.8rem',
        }}
      >
        <FlipCard
          value={currentHours}
          prevValue={prevHours}
          nightMode={nightMode}
          size={isFullScreen ? 'large' : 'small'}
          label="HOURS"
        />

        {/* Colon separator */}
        <div
          style={{
            display: 'flex',
            flexDirection: 'column',
            gap: isFullScreen ? '1.2rem' : '0.7rem',
            alignItems: 'center',
            justifyContent: 'center',
          }}
        >
          <div
            style={{
              width: isFullScreen ? '14px' : '9px',
              height: isFullScreen ? '14px' : '9px',
              borderRadius: '50%',
              backgroundColor: nightMode ? '#ff3b30' : '#ffffff',
              boxShadow: nightMode ? '0 0 10px #ff3b30' : 'none',
            }}
          />
          <div
            style={{
              width: isFullScreen ? '14px' : '9px',
              height: isFullScreen ? '14px' : '9px',
              borderRadius: '50%',
              backgroundColor: nightMode ? '#ff3b30' : '#ffffff',
              boxShadow: nightMode ? '0 0 10px #ff3b30' : 'none',
            }}
          />
        </div>

        <FlipCard
          value={currentMinutes}
          prevValue={prevMinutes}
          nightMode={nightMode}
          size={isFullScreen ? 'large' : 'small'}
          label="MINUTES"
        />

        {timeFormat === '12h' && (
          <div
            style={{
              alignSelf: 'flex-end',
              marginBottom: isFullScreen ? '2rem' : '1.2rem',
              marginLeft: '0.4rem',
              padding: '0.3rem 0.6rem',
              borderRadius: '8px',
              backgroundColor: nightMode ? '#2b0606' : '#22232a',
              border: `1px solid ${nightMode ? '#4d0b0b' : '#333540'}`,
              color: nightMode ? '#ff453a' : '#8e8e93',
              fontSize: isFullScreen ? '1.2rem' : '0.85rem',
              fontWeight: 800,
              letterSpacing: '0.08em',
            }}
          >
            {period}
          </div>
        )}
      </div>
    </div>
  );
};
