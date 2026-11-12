import React from 'react';
import { Calendar as CalendarIcon, MapPin } from 'lucide-react';
import { useTime } from '../../hooks/useTime';
import { UPCOMING_EVENTS } from '../../utils/weatherData';

interface CalendarWidgetProps {
  nightMode: boolean;
}

export const CalendarWidget: React.FC<CalendarWidgetProps> = ({ nightMode }) => {
  const { dayNumber, monthName, dayName } = useTime();

  const textColor = nightMode ? '#ff3b30' : '#ffffff';
  const subTextColor = nightMode ? '#a31b14' : '#8e8e93';
  const headerRed = nightMode ? '#ff3b30' : '#ff453a';

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
      {/* Top Section: Month & Big Date */}
      <div style={{ display: 'flex', alignItems: 'center', gap: '1.2rem' }}>
        <div
          style={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            padding: '0.4rem 0.8rem',
            borderRadius: '16px',
            backgroundColor: nightMode ? 'rgba(255, 59, 48, 0.12)' : 'rgba(255, 255, 255, 0.08)',
            border: `1px solid ${nightMode ? 'rgba(255, 59, 48, 0.25)' : 'rgba(255, 255, 255, 0.1)'}`,
            minWidth: '60px',
          }}
        >
          <span
            style={{
              fontSize: '0.75rem',
              fontWeight: 800,
              color: headerRed,
              textTransform: 'uppercase',
              letterSpacing: '0.08em',
            }}
          >
            {monthName.slice(0, 3)}
          </span>
          <span
            style={{
              fontSize: '2.2rem',
              fontWeight: 900,
              fontFamily: "'Inter', sans-serif",
              lineHeight: 1,
              marginTop: '0.1rem',
            }}
          >
            {dayNumber}
          </span>
        </div>

        <div>
          <div
            style={{
              fontSize: '1.1rem',
              fontWeight: 700,
              letterSpacing: '-0.01em',
            }}
          >
            {dayName}
          </div>
          <div
            style={{
              fontSize: '0.8rem',
              fontWeight: 500,
              color: subTextColor,
              marginTop: '0.1rem',
              display: 'flex',
              alignItems: 'center',
              gap: '0.3rem',
            }}
          >
            <CalendarIcon size={13} color={headerRed} />
            <span>3 Events Scheduled</span>
          </div>
        </div>
      </div>

      {/* Agenda Events List */}
      <div
        style={{
          display: 'flex',
          flexDirection: 'column',
          gap: '0.45rem',
          marginTop: '0.6rem',
        }}
      >
        {UPCOMING_EVENTS.slice(0, 2).map((ev) => (
          <div
            key={ev.id}
            style={{
              display: 'flex',
              alignItems: 'flex-start',
              gap: '0.6rem',
              padding: '0.5rem 0.7rem',
              borderRadius: '12px',
              backgroundColor: nightMode ? 'rgba(255, 59, 48, 0.06)' : 'rgba(255, 255, 255, 0.04)',
            }}
          >
            {/* Tag Pill */}
            <div
              style={{
                width: '3.5px',
                height: '28px',
                borderRadius: '3px',
                backgroundColor: nightMode ? '#ff3b30' : ev.tagColor,
                marginTop: '2px',
                flexShrink: 0,
              }}
            />

            <div style={{ overflow: 'hidden', flex: 1 }}>
              <div
                style={{
                  fontSize: '0.82rem',
                  fontWeight: 700,
                  whiteSpace: 'nowrap',
                  textOverflow: 'ellipsis',
                  overflow: 'hidden',
                }}
              >
                {ev.title}
              </div>
              <div
                style={{
                  fontSize: '0.7rem',
                  fontWeight: 500,
                  color: subTextColor,
                  display: 'flex',
                  alignItems: 'center',
                  gap: '0.4rem',
                  marginTop: '0.1rem',
                }}
              >
                <span>{ev.time}</span>
                {ev.location && (
                  <>
                    <span>•</span>
                    <span style={{ display: 'flex', alignItems: 'center', gap: '0.2rem' }}>
                      <MapPin size={10} />
                      {ev.location}
                    </span>
                  </>
                )}
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
