import React, { useEffect, useState } from 'react';

interface ChargingEdgeGlowProps {
  isCharging: boolean;
  nightMode: boolean;
}

export const ChargingEdgeGlow: React.FC<ChargingEdgeGlowProps> = ({ isCharging, nightMode }) => {
  const [showBurst, setShowBurst] = useState(false);

  useEffect(() => {
    if (isCharging) {
      setShowBurst(true);
      const timer = setTimeout(() => setShowBurst(false), 2400);
      return () => clearTimeout(timer);
    }
  }, [isCharging]);

  if (!isCharging) return null;

  const glowColor = nightMode ? '#ff3b30' : '#30d158';
  const pulseColor = nightMode ? 'rgba(255, 59, 48, 0.4)' : 'rgba(48, 209, 88, 0.35)';

  return (
    <div
      style={{
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        pointerEvents: 'none',
        zIndex: 10,
        boxShadow: showBurst
          ? `inset 0 0 70px ${glowColor}, inset 0 0 140px ${pulseColor}`
          : `inset 0 0 25px ${pulseColor}`,
        transition: 'box-shadow 0.8s ease-out',
        animation: showBurst ? 'chargingPulse 1.8s ease-out' : 'subtleBreathing 4s infinite ease-in-out',
      }}
    />
  );
};
