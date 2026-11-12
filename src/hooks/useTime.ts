import { useState, useEffect } from 'react';

export interface TimeState {
  date: Date;
  hours24: number;
  hours12: number;
  minutes: number;
  seconds: number;
  period: 'AM' | 'PM';
  dayName: string;
  monthName: string;
  dayNumber: number;
  year: number;
  formattedTime12: string;
  formattedTime24: string;
  formattedSeconds: string;
}

const DAYS = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
const MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

export function useTime(): TimeState {
  const [time, setTime] = useState<Date>(new Date());

  useEffect(() => {
    // High-resolution interval matching the second boundary
    const interval = setInterval(() => {
      setTime(new Date());
    }, 250);

    return () => clearInterval(interval);
  }, []);

  const hours24 = time.getHours();
  const hours12 = hours24 % 12 === 0 ? 12 : hours24 % 12;
  const minutes = time.getMinutes();
  const seconds = time.getSeconds();
  const period = hours24 >= 12 ? 'PM' : 'AM';

  const pad = (n: number) => n.toString().padStart(2, '0');

  return {
    date: time,
    hours24,
    hours12,
    minutes,
    seconds,
    period,
    dayName: DAYS[time.getDay()],
    monthName: MONTHS[time.getMonth()],
    dayNumber: time.getDate(),
    year: time.getFullYear(),
    formattedTime12: `${pad(hours12)}:${pad(minutes)}`,
    formattedTime24: `${pad(hours24)}:${pad(minutes)}`,
    formattedSeconds: pad(seconds),
  };
}
