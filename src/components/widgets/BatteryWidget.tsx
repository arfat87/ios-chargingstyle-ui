import React from 'react';
import { Zap, BatteryCharging, ShieldCheck, Thermometer } from 'lucide-react';

interface BatteryWidgetProps {
  nightMode: boolean;
  batteryLevel: number;
  isCharging: boolean;
  chargingWatts: number;
}

export const BatteryWidget: React.FC<BatteryWidgetProps> = ({
  nightMode,
  batteryLevel,
  isCharging,
  chargingWatts,
}) => {
  const textColor = nightMode ? '#ff3b30' : '#ffffff';
  const subTextColor = nightMode ? '#a31b14' : '#8e8e93';
  const greenCharge = nightMode ? '#ff3b30' : '#30d158';

  // SVG circular ring calculation
  const radius = 42;
  const circumference = 2 * Math.PI * radius;
  const strokeDashoffset = circumference - (batteryLevel / 100) * circumference;

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
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: '0.4rem' }}>
          <BatteryCharging size={18} color={greenCharge} />
          <span style={{ fontSize: '0.9rem', fontWeight: 700 }}>
            {isCharging ? 'Charging' : 'On Battery'}
          </span>
        </div>
        <div
          style={{
            padding: '0.2rem 0.6rem',
            borderRadius: '20px',
            backgroundColor: nightMode ? 'rgba(255, 59, 48, 0.15)' : 'rgba(48, 209, 88, 0.15)',
            border: `1px solid ${nightMode ? 'rgba(255, 59, 48, 0.3)' : 'rgba(48, 209, 88, 0.3)'}`,
            fontSize: '0.72rem',
            fontWeight: 800,
            color: greenCharge,
          }}
        >
          {isCharging ? `${chargingWatts}W ULTRA` : 'NORMAL'}
        </div>
      </div>

      {/* Center Circular Progress Ring & Percentage */}
      <div
        style={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          gap: '1.5rem',
          margin: '0.4rem 0',
        }}
      >
        <div style={{ position: 'relative', width: '100px', height: '100px' }}>
          <svg width="100" height="100" style={{ transform: 'rotate(-90deg)' }}>
            {/* Background Track */}
            <circle
              cx="50"
              cy="50"
              r={radius}
              stroke={nightMode ? 'rgba(255, 59, 48, 0.15)' : 'rgba(255, 255, 255, 0.1)'}
              strokeWidth="9"
              fill="none"
            />
            {/* Active Charging Progress Ring */}
            <circle
              cx="50"
              cy="50"
              r={radius}
              stroke={greenCharge}
              strokeWidth="9"
              strokeDasharray={circumference}
              strokeDashoffset={strokeDashoffset}
              strokeLinecap="round"
              fill="none"
              style={{
                transition: 'stroke-dashoffset 0.8s ease',
                filter: isCharging ? `drop-shadow(0 0 6px ${greenCharge})` : 'none',
              }}
            />
          </svg>

          {/* Center Lightning / Icon */}
          <div
            style={{
              position: 'absolute',
              top: 0,
              left: 0,
              width: '100%',
              height: '100%',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              color: greenCharge,
            }}
          >
            <Zap
              size={26}
              style={{
                animation: isCharging ? 'pulseGlow 1.8s infinite' : 'none',
              }}
            />
          </div>
        </div>

        <div>
          <div
            style={{
              fontSize: 'clamp(2.4rem, 5vw, 3.4rem)',
              fontWeight: 900,
              fontFamily: "'Inter', sans-serif",
              lineHeight: 1,
              letterSpacing: '-0.03em',
            }}
          >
            {batteryLevel}%
          </div>
          <div
            style={{
              fontSize: '0.78rem',
              fontWeight: 600,
              color: subTextColor,
              marginTop: '0.3rem',
            }}
          >
            {isCharging ? '24m until 100%' : '14h remaining'}
          </div>
        </div>
      </div>

      {/* Bottom Health & Thermal Stats */}
      <div
        style={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          fontSize: '0.75rem',
          fontWeight: 600,
          color: subTextColor,
          borderTop: `1px solid ${nightMode ? 'rgba(255, 59, 48, 0.12)' : 'rgba(255, 255, 255, 0.08)'}`,
          paddingTop: '0.5rem',
        }}
      >
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.3rem' }}>
          <ShieldCheck size={14} color={greenCharge} />
          Optimized Battery Charging
        </span>
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.2rem' }}>
          <Thermometer size={14} />
          29°C Normal
        </span>
      </div>
    </div>
  );
};
