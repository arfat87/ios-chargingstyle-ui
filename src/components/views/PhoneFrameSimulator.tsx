import React from 'react';

interface PhoneFrameSimulatorProps {
  children: React.ReactNode;
  showBezel: boolean;
  nightMode: boolean;
  isCharging: boolean;
}

export const PhoneFrameSimulator: React.FC<PhoneFrameSimulatorProps> = ({
  children,
  showBezel,
  nightMode,
  isCharging,
}) => {
  if (!showBezel) {
    return (
      <div
        style={{
          width: '100vw',
          height: '100vh',
          backgroundColor: '#000000',
          position: 'relative',
          overflow: 'hidden',
        }}
      >
        {children}
      </div>
    );
  }

  // Realistic Bedside Landscape Stand Mockup
  return (
    <div
      style={{
        width: '100vw',
        height: '100vh',
        backgroundColor: '#08080a',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        position: 'relative',
        overflow: 'hidden',
      }}
    >
      {/* Wireless Charging Stand Base Shadow */}
      <div
        style={{
          position: 'absolute',
          bottom: '8%',
          width: '280px',
          height: '24px',
          borderRadius: '50%',
          backgroundColor: 'rgba(0, 0, 0, 0.85)',
          filter: 'blur(16px)',
          transform: 'scaleX(1.4)',
        }}
      />

      {/* Stand Metal Arm Stem */}
      <div
        style={{
          position: 'absolute',
          bottom: '10%',
          width: '18px',
          height: '120px',
          background: 'linear-gradient(to right, #2a2c35, #4e5261, #1e2026)',
          borderRadius: '9px',
          zIndex: 1,
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'flex-end',
          paddingBottom: '8px',
        }}
      >
        {/* MagSafe / Stand Charging Status LED */}
        <div
          style={{
            width: '4px',
            height: '4px',
            borderRadius: '50%',
            backgroundColor: isCharging ? (nightMode ? '#ff3b30' : '#30d158') : '#555966',
            boxShadow: isCharging ? `0 0 6px ${nightMode ? '#ff3b30' : '#30d158'}` : 'none',
          }}
        />
      </div>

      {/* Titanium Smartphone Frame (Landscape) */}
      <div
        style={{
          position: 'relative',
          width: 'min(94vw, 980px)',
          height: 'min(76vh, 480px)',
          backgroundColor: '#000000',
          borderRadius: '48px',
          padding: '12px',
          boxSizing: 'border-box',
          boxShadow: nightMode
            ? '0 30px 80px rgba(0, 0, 0, 0.95), 0 0 40px rgba(255, 59, 48, 0.15)'
            : '0 35px 90px rgba(0, 0, 0, 0.85), inset 0 0 4px rgba(255, 255, 255, 0.3)',
          border: '3px solid #2d3039',
          outline: '1px solid #18191f',
          zIndex: 2,
          display: 'flex',
          flexDirection: 'column',
          overflow: 'hidden',
        }}
      >
        {/* Dynamic Island / Camera Pill on Left Edge (since it's landscape) */}
        <div
          style={{
            position: 'absolute',
            left: '20px',
            top: '50%',
            transform: 'translateY(-50%)',
            width: '12px',
            height: '75px',
            backgroundColor: '#000000',
            borderRadius: '16px',
            zIndex: 40,
            border: '1px solid rgba(255, 255, 255, 0.08)',
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            gap: '8px',
          }}
        >
          <div
            style={{
              width: '8px',
              height: '8px',
              borderRadius: '50%',
              backgroundColor: '#0a0d14',
              boxShadow: 'inset 0 0 2px #32415c',
            }}
          />
        </div>

        {/* Inner OLED Display Container */}
        <div
          style={{
            position: 'relative',
            width: '100%',
            height: '100%',
            backgroundColor: '#000000',
            borderRadius: '38px',
            overflow: 'hidden',
          }}
        >
          {children}
        </div>
      </div>
    </div>
  );
};
