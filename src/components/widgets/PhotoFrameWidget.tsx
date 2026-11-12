import React, { useState, useEffect } from 'react';
import { Image, ChevronRight, ChevronLeft } from 'lucide-react';
import { CURATED_PHOTOS } from '../../utils/weatherData';
import { useTime } from '../../hooks/useTime';

interface PhotoFrameWidgetProps {
  nightMode: boolean;
  timeFormat: '12h' | '24h';
}

export const PhotoFrameWidget: React.FC<PhotoFrameWidgetProps> = ({ nightMode, timeFormat }) => {
  const [photoIndex, setPhotoIndex] = useState(0);
  const { formattedTime12, formattedTime24, period } = useTime();

  const photo = CURATED_PHOTOS[photoIndex];

  // Rotate photos every 15 seconds
  useEffect(() => {
    const interval = setInterval(() => {
      setPhotoIndex((prev) => (prev + 1) % CURATED_PHOTOS.length);
    }, 15000);
    return () => clearInterval(interval);
  }, []);

  const handleNext = (e: React.MouseEvent) => {
    e.stopPropagation();
    setPhotoIndex((prev) => (prev + 1) % CURATED_PHOTOS.length);
  };

  const handlePrev = (e: React.MouseEvent) => {
    e.stopPropagation();
    setPhotoIndex((prev) => (prev - 1 + CURATED_PHOTOS.length) % CURATED_PHOTOS.length);
  };

  const timeDisplay = timeFormat === '12h' ? formattedTime12 : formattedTime24;

  return (
    <div
      style={{
        position: 'relative',
        width: '100%',
        height: '100%',
        overflow: 'hidden',
        borderRadius: '28px',
      }}
    >
      {/* Background Photo with subtle zoom transition */}
      <img
        src={photo.url}
        alt={photo.title}
        style={{
          width: '100%',
          height: '100%',
          objectFit: 'cover',
          filter: nightMode
            ? 'grayscale(100%) sepia(100%) hue-rotate(320deg) saturate(350%) brightness(0.7)'
            : 'brightness(0.85)',
          transition: 'transform 10s ease-out',
          transform: 'scale(1.05)',
        }}
      />

      {/* Dark Vignette Overlay */}
      <div
        style={{
          position: 'absolute',
          top: 0,
          left: 0,
          right: 0,
          bottom: 0,
          background: 'linear-gradient(to top, rgba(0,0,0,0.85) 0%, rgba(0,0,0,0.2) 50%, rgba(0,0,0,0.6) 100%)',
          pointerEvents: 'none',
        }}
      />

      {/* Top Left Clock Overlay */}
      <div
        style={{
          position: 'absolute',
          top: '1rem',
          left: '1.2rem',
          color: nightMode ? '#ff3b30' : '#ffffff',
          textShadow: '0 2px 10px rgba(0,0,0,0.8)',
        }}
      >
        <div
          style={{
            fontFamily: "'Inter', sans-serif",
            fontSize: '2rem',
            fontWeight: 800,
            lineHeight: 1,
            letterSpacing: '-0.02em',
          }}
        >
          {timeDisplay}
          {timeFormat === '12h' && (
            <span style={{ fontSize: '0.9rem', fontWeight: 600, marginLeft: '0.3rem', opacity: 0.8 }}>
              {period}
            </span>
          )}
        </div>
      </div>

      {/* Navigation Arrow buttons */}
      <button
        onClick={handlePrev}
        style={{
          position: 'absolute',
          left: '0.5rem',
          top: '50%',
          transform: 'translateY(-50%)',
          background: 'rgba(0,0,0,0.4)',
          border: 'none',
          color: '#ffffff',
          borderRadius: '50%',
          width: '32px',
          height: '32px',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          cursor: 'pointer',
          backdropFilter: 'blur(8px)',
        }}
      >
        <ChevronLeft size={18} />
      </button>

      <button
        onClick={handleNext}
        style={{
          position: 'absolute',
          right: '0.5rem',
          top: '50%',
          transform: 'translateY(-50%)',
          background: 'rgba(0,0,0,0.4)',
          border: 'none',
          color: '#ffffff',
          borderRadius: '50%',
          width: '32px',
          height: '32px',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          cursor: 'pointer',
          backdropFilter: 'blur(8px)',
        }}
      >
        <ChevronRight size={18} />
      </button>

      {/* Bottom Info Bar */}
      <div
        style={{
          position: 'absolute',
          bottom: '0.9rem',
          left: '1.2rem',
          right: '1.2rem',
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          color: '#ffffff',
          textShadow: '0 1px 6px rgba(0,0,0,0.8)',
        }}
      >
        <div>
          <div style={{ fontSize: '0.85rem', fontWeight: 700 }}>{photo.title}</div>
          <div style={{ fontSize: '0.7rem', opacity: 0.75, marginTop: '1px' }}>{photo.date}</div>
        </div>
        <div
          style={{
            padding: '0.2rem 0.5rem',
            borderRadius: '12px',
            background: 'rgba(255,255,255,0.2)',
            backdropFilter: 'blur(10px)',
            fontSize: '0.65rem',
            fontWeight: 700,
            display: 'flex',
            alignItems: 'center',
            gap: '0.3rem',
          }}
        >
          <Image size={12} />
          <span>{photoIndex + 1}/{CURATED_PHOTOS.length}</span>
        </div>
      </div>
    </div>
  );
};
