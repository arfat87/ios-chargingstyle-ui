import React, { useState, useEffect } from 'react';
import { Globe } from 'lucide-react';

interface WorldClockProps {
  nightMode: boolean;
  timeFormat: '12h' | '24h';
}

interface CityTime {
  name: string;
  country: string;
  offsetHours: number;
  timeString: string;
  diffString: string;
  period: string;
}

export const WorldClock: React.FC<WorldClockProps> = ({ nightMode, timeFormat }) => {
  const [times, setTimes] = useState<CityTime[]>([]);

  useEffect(() => {
    const updateTimes = () => {
      const now = new Date();
      const utcTime = now.getTime() + now.getTimezoneOffset() * 60000;
      const userOffset = -now.getTimezoneOffset() / 60;

      const cities = [
        { name: 'New York', country: 'USA', offset: -5 },
        { name: 'London', country: 'UK', offset: 0 },
        { name: 'New Delhi', country: 'India', offset: 5.5 },
        { name: 'Tokyo', country: 'Japan', offset: 9 },
      ];

      const list: CityTime[] = cities.map((c) => {
        const cityDate = new Date(utcTime + 3600000 * c.offset);
        const hours24 = cityDate.getHours();
        const hours12 = hours24 % 12 === 0 ? 12 : hours24 % 12;
        const minutes = cityDate.getMinutes().toString().padStart(2, '0');
        const period = hours24 >= 12 ? 'PM' : 'AM';

        const diff = c.offset - userOffset;
        const diffString =
          diff === 0 ? 'Local' : `${diff > 0 ? '+' : ''}${diff % 1 === 0 ? diff : diff.toFixed(1)} HRS`;

        const timeString =
          timeFormat === '12h'
            ? `${hours12.toString().padStart(2, '0')}:${minutes}`
            : `${hours24.toString().padStart(2, '0')}:${minutes}`;

        return {
          name: c.name,
          country: c.country,
          offsetHours: c.offset,
          timeString,
          diffString,
          period,
        };
      });

      setTimes(list);
    };

    updateTimes();
    const timer = setInterval(updateTimes, 1000);
    return () => clearInterval(timer);
  }, [timeFormat]);

  const textColor = nightMode ? '#ff3b30' : '#ffffff';
  const subTextColor = nightMode ? '#a31b14' : '#8e8e93';
  const badgeBg = nightMode ? '#280606' : '#22232a';

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        width: '100%',
        height: '100%',
        padding: '1.2rem',
        boxSizing: 'border-box',
        gap: '0.8rem',
      }}
    >
      <div
        style={{
          display: 'flex',
          alignItems: 'center',
          gap: '0.5rem',
          fontSize: '0.9rem',
          fontWeight: 700,
          color: subTextColor,
          letterSpacing: '0.08em',
          textTransform: 'uppercase',
          marginBottom: '0.2rem',
        }}
      >
        <Globe size={16} />
        <span>World Clocks</span>
      </div>

      <div
        style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(2, 1fr)',
          gap: '0.75rem',
        }}
      >
        {times.map((city) => (
          <div
            key={city.name}
            style={{
              display: 'flex',
              flexDirection: 'column',
              padding: '0.75rem 0.9rem',
              borderRadius: '16px',
              backgroundColor: badgeBg,
              border: `1px solid ${nightMode ? '#470b0b' : '#31333d'}`,
            }}
          >
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
              <span
                style={{
                  fontSize: '0.9rem',
                  fontWeight: 700,
                  color: textColor,
                }}
              >
                {city.name}
              </span>
              <span
                style={{
                  fontSize: '0.68rem',
                  fontWeight: 600,
                  color: subTextColor,
                }}
              >
                {city.diffString}
              </span>
            </div>

            <div
              style={{
                display: 'flex',
                alignItems: 'baseline',
                gap: '0.3rem',
                marginTop: '0.2rem',
              }}
            >
              <span
                style={{
                  fontFamily: "'Inter', sans-serif",
                  fontSize: '1.5rem',
                  fontWeight: 800,
                  color: textColor,
                  letterSpacing: '-0.02em',
                }}
              >
                {city.timeString}
              </span>
              {timeFormat === '12h' && (
                <span
                  style={{
                    fontSize: '0.75rem',
                    fontWeight: 700,
                    color: subTextColor,
                  }}
                >
                  {city.period}
                </span>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
