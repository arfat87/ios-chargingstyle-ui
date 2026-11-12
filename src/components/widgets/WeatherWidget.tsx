import React from 'react';
import { Sun, CloudRain, CloudSun, Moon, CloudMoon, Wind, Droplets } from 'lucide-react';
import { INITIAL_WEATHER } from '../../utils/weatherData';

interface WeatherWidgetProps {
  nightMode: boolean;
}

export const WeatherWidget: React.FC<WeatherWidgetProps> = ({ nightMode }) => {
  const weather = INITIAL_WEATHER;

  const textColor = nightMode ? '#ff3b30' : '#ffffff';
  const subTextColor = nightMode ? '#a31b14' : '#8e8e93';
  const accentColor = nightMode ? '#ff453a' : '#2997ff';

  const renderIcon = (iconName: string, size = 20) => {
    switch (iconName) {
      case 'sun':
        return <Sun size={size} color={nightMode ? '#ff3b30' : '#ff9f0a'} />;
      case 'cloud-rain':
        return <CloudRain size={size} color={nightMode ? '#ff3b30' : '#5ac8fa'} />;
      case 'cloud-sun':
        return <CloudSun size={size} color={nightMode ? '#ff3b30' : '#ff9f0a'} />;
      case 'cloud-moon':
        return <CloudMoon size={size} color={nightMode ? '#ff3b30' : '#bf5af2'} />;
      case 'moon':
      default:
        return <Moon size={size} color={nightMode ? '#ff3b30' : '#ffcc00'} />;
    }
  };

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
      {/* Top Header: City & Main Temp */}
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
        <div>
          <div
            style={{
              fontSize: '1rem',
              fontWeight: 700,
              letterSpacing: '0.02em',
              display: 'flex',
              alignItems: 'center',
              gap: '0.4rem',
            }}
          >
            <span>{weather.city}</span>
          </div>
          <div
            style={{
              fontSize: '0.8rem',
              fontWeight: 500,
              color: subTextColor,
              marginTop: '0.1rem',
            }}
          >
            {weather.condition}
          </div>
        </div>

        <div style={{ textAlign: 'right' }}>
          <div
            style={{
              fontSize: 'clamp(2.5rem, 5vw, 3.8rem)',
              fontWeight: 800,
              fontFamily: "'Inter', sans-serif",
              lineHeight: 1,
              letterSpacing: '-0.03em',
            }}
          >
            {weather.temperature}°
          </div>
          <div
            style={{
              fontSize: '0.75rem',
              fontWeight: 600,
              color: subTextColor,
              marginTop: '0.2rem',
            }}
          >
            H:{weather.high}° L:{weather.low}°
          </div>
        </div>
      </div>

      {/* Middle Stats Bar */}
      <div
        style={{
          display: 'flex',
          gap: '1rem',
          alignItems: 'center',
          padding: '0.4rem 0.8rem',
          borderRadius: '12px',
          backgroundColor: nightMode ? 'rgba(255, 59, 48, 0.08)' : 'rgba(255, 255, 255, 0.06)',
          fontSize: '0.75rem',
          fontWeight: 600,
          color: subTextColor,
        }}
      >
        <div style={{ display: 'flex', alignItems: 'center', gap: '0.3rem' }}>
          <Droplets size={14} color={accentColor} />
          <span>{weather.humidity}% Humidity</span>
        </div>
        <div style={{ display: 'flex', alignItems: 'center', gap: '0.3rem' }}>
          <Wind size={14} color={accentColor} />
          <span>12 km/h</span>
        </div>
      </div>

      {/* Hourly Timeline Bar */}
      <div
        style={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          gap: '0.4rem',
        }}
      >
        {weather.forecast.map((item, idx) => (
          <div
            key={idx}
            style={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              gap: '0.3rem',
              flex: 1,
            }}
          >
            <span
              style={{
                fontSize: '0.68rem',
                fontWeight: 600,
                color: subTextColor,
              }}
            >
              {item.time}
            </span>
            {renderIcon(item.icon, 18)}
            <span
              style={{
                fontSize: '0.82rem',
                fontWeight: 700,
              }}
            >
              {item.temp}°
            </span>
          </div>
        ))}
      </div>
    </div>
  );
};
