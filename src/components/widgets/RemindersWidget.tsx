import React, { useState } from 'react';
import { CheckCircle2, Circle, ListTodo } from 'lucide-react';
import { playHapticClick } from '../../utils/audioSynth';

interface RemindersWidgetProps {
  nightMode: boolean;
  soundEnabled: boolean;
}

interface ReminderItem {
  id: string;
  text: string;
  completed: boolean;
  tag: string;
}

export const RemindersWidget: React.FC<RemindersWidgetProps> = ({ nightMode, soundEnabled }) => {
  const [items, setItems] = useState<ReminderItem[]>([
    { id: '1', text: 'Set morning alarm for 6:30 AM', completed: true, tag: 'Bedtime' },
    { id: '2', text: 'Drink glass of water', completed: true, tag: 'Health' },
    { id: '3', text: 'Review StandBy+ Android specs', completed: false, tag: 'Work' },
    { id: '4', text: 'Plug phone on charging dock', completed: true, tag: 'Power' },
  ]);

  const toggleItem = (id: string) => {
    playHapticClick(soundEnabled);
    setItems((prev) =>
      prev.map((item) => (item.id === id ? { ...item, completed: !item.completed } : item))
    );
  };

  const textColor = nightMode ? '#ff3b30' : '#ffffff';
  const subTextColor = nightMode ? '#a31b14' : '#8e8e93';
  const checkColor = nightMode ? '#ff3b30' : '#30d158';

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
          <ListTodo size={17} color={checkColor} />
          <span style={{ fontSize: '0.92rem', fontWeight: 700 }}>Reminders</span>
        </div>
        <span
          style={{
            fontSize: '0.72rem',
            fontWeight: 700,
            color: subTextColor,
          }}
        >
          {items.filter((i) => i.completed).length}/{items.length} Done
        </span>
      </div>

      <div style={{ display: 'flex', flexDirection: 'column', gap: '0.5rem', marginTop: '0.4rem' }}>
        {items.map((item) => (
          <div
            key={item.id}
            onClick={() => toggleItem(item.id)}
            style={{
              display: 'flex',
              alignItems: 'center',
              gap: '0.6rem',
              padding: '0.45rem 0.6rem',
              borderRadius: '12px',
              backgroundColor: nightMode ? 'rgba(255, 59, 48, 0.08)' : 'rgba(255, 255, 255, 0.05)',
              cursor: 'pointer',
              transition: 'background-color 0.2s ease',
            }}
          >
            {item.completed ? (
              <CheckCircle2 size={18} color={checkColor} />
            ) : (
              <Circle size={18} color={subTextColor} />
            )}
            <span
              style={{
                fontSize: '0.82rem',
                fontWeight: 600,
                textDecoration: item.completed ? 'line-through' : 'none',
                color: item.completed ? subTextColor : textColor,
                flex: 1,
                whiteSpace: 'nowrap',
                overflow: 'hidden',
                textOverflow: 'ellipsis',
              }}
            >
              {item.text}
            </span>
          </div>
        ))}
      </div>

      <div
        style={{
          fontSize: '0.7rem',
          fontWeight: 600,
          color: subTextColor,
          textAlign: 'center',
          marginTop: '0.3rem',
        }}
      >
        Tap to toggle completion
      </div>
    </div>
  );
};
