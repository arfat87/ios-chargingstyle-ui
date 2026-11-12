import React, { useState, useEffect } from 'react';
import { Play, Pause, SkipBack, SkipForward } from 'lucide-react';
import { DEMO_TRACKS } from '../../utils/weatherData';

interface MusicWidgetProps {
  nightMode: boolean;
}

export const MusicWidget: React.FC<MusicWidgetProps> = ({ nightMode }) => {
  const [trackIndex, setTrackIndex] = useState(0);
  const [isPlaying, setIsPlaying] = useState(true);
  const [progress, setProgress] = useState(85); // in seconds

  const track = DEMO_TRACKS[trackIndex];

  useEffect(() => {
    if (!isPlaying) return;
    const interval = setInterval(() => {
      setProgress((prev) => (prev >= track.duration ? 0 : prev + 1));
    }, 1000);
    return () => clearInterval(interval);
  }, [isPlaying, track.duration]);

  const handleNext = () => {
    setTrackIndex((prev) => (prev + 1) % DEMO_TRACKS.length);
    setProgress(0);
  };

  const handlePrev = () => {
    setTrackIndex((prev) => (prev - 1 + DEMO_TRACKS.length) % DEMO_TRACKS.length);
    setProgress(0);
  };

  const formatTime = (secs: number) => {
    const m = Math.floor(secs / 60);
    const s = secs % 60;
    return `${m}:${s.toString().padStart(2, '0')}`;
  };

  const textColor = nightMode ? '#ff3b30' : '#ffffff';
  const subTextColor = nightMode ? '#a31b14' : '#8e8e93';
  const accentRed = nightMode ? '#ff3b30' : '#fa2d48';

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
        width: '100%',
        height: '100%',
        padding: '1.4rem',
        boxSizing: 'border-box',
        color: textColor,
      }}
    >
      {/* Top Track & Album Info */}
      <div style={{ display: 'flex', gap: '1rem', alignItems: 'center' }}>
        <img
          src={track.coverUrl}
          alt={track.album}
          style={{
            width: '68px',
            height: '68px',
            borderRadius: '16px',
            objectFit: 'cover',
            border: `1px solid ${nightMode ? 'rgba(255, 59, 48, 0.4)' : 'rgba(255, 255, 255, 0.2)'}`,
            filter: nightMode ? 'grayscale(80%) sepia(100%) hue-rotate(320deg) saturate(300%)' : 'none',
            boxShadow: '0 8px 20px rgba(0,0,0,0.5)',
          }}
        />

        <div style={{ overflow: 'hidden', flex: 1 }}>
          <div
            style={{
              fontSize: '1rem',
              fontWeight: 800,
              whiteSpace: 'nowrap',
              textOverflow: 'ellipsis',
              overflow: 'hidden',
              letterSpacing: '-0.01em',
            }}
          >
            {track.title}
          </div>
          <div
            style={{
              fontSize: '0.82rem',
              fontWeight: 600,
              color: subTextColor,
              marginTop: '0.2rem',
            }}
          >
            {track.artist}
          </div>

          {/* Animated Waveform Visualizer */}
          <div
            style={{
              display: 'flex',
              alignItems: 'flex-end',
              gap: '3px',
              height: '16px',
              marginTop: '0.4rem',
            }}
          >
            {[40, 90, 60, 100, 75, 45, 85, 30, 95].map((height, i) => (
              <div
                key={i}
                style={{
                  width: '3px',
                  height: isPlaying ? `${height}%` : '20%',
                  backgroundColor: accentRed,
                  borderRadius: '2px',
                  transition: 'height 0.2s ease',
                  animation: isPlaying
                    ? `soundWave 1.2s ease-in-out infinite alternate ${i * 0.12}s`
                    : 'none',
                }}
              />
            ))}
          </div>
        </div>
      </div>

      {/* Scrub Bar */}
      <div>
        <div
          style={{
            position: 'relative',
            width: '100%',
            height: '4px',
            backgroundColor: nightMode ? 'rgba(255, 59, 48, 0.2)' : 'rgba(255, 255, 255, 0.15)',
            borderRadius: '4px',
            overflow: 'hidden',
            margin: '0.4rem 0',
          }}
        >
          <div
            style={{
              width: `${(progress / track.duration) * 100}%`,
              height: '100%',
              backgroundColor: textColor,
              borderRadius: '4px',
              transition: 'width 1s linear',
            }}
          />
        </div>
        <div
          style={{
            display: 'flex',
            justifyContent: 'space-between',
            fontSize: '0.68rem',
            fontWeight: 600,
            color: subTextColor,
          }}
        >
          <span>{formatTime(progress)}</span>
          <span>-{formatTime(track.duration - progress)}</span>
        </div>
      </div>

      {/* Media Controls */}
      <div
        style={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          gap: '1.8rem',
        }}
      >
        <button
          onClick={handlePrev}
          style={{
            background: 'none',
            border: 'none',
            color: textColor,
            cursor: 'pointer',
            padding: '4px',
            display: 'flex',
          }}
        >
          <SkipBack size={22} fill="currentColor" />
        </button>

        <button
          onClick={() => setIsPlaying(!isPlaying)}
          style={{
            width: '46px',
            height: '46px',
            borderRadius: '50%',
            backgroundColor: textColor,
            color: nightMode ? '#000000' : '#121316',
            border: 'none',
            cursor: 'pointer',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            boxShadow: '0 4px 14px rgba(0,0,0,0.4)',
            transition: 'transform 0.15s ease',
          }}
          onMouseDown={(e) => (e.currentTarget.style.transform = 'scale(0.92)')}
          onMouseUp={(e) => (e.currentTarget.style.transform = 'scale(1)')}
        >
          {isPlaying ? <Pause size={22} fill="currentColor" /> : <Play size={22} fill="currentColor" style={{ marginLeft: '3px' }} />}
        </button>

        <button
          onClick={handleNext}
          style={{
            background: 'none',
            border: 'none',
            color: textColor,
            cursor: 'pointer',
            padding: '4px',
            display: 'flex',
          }}
        >
          <SkipForward size={22} fill="currentColor" />
        </button>
      </div>
    </div>
  );
};
