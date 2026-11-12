import { useState, useEffect } from 'react';

export function usePixelShift(enabled: boolean = true) {
  const [offset, setOffset] = useState<{ x: number; y: number }>({ x: 0, y: 0 });

  useEffect(() => {
    if (!enabled) {
      setOffset({ x: 0, y: 0 });
      return;
    }

    // Shift pixels every 60 seconds by +/- 1 or 2 pixels
    const interval = setInterval(() => {
      const randomX = Math.floor(Math.random() * 5) - 2; // -2, -1, 0, 1, 2
      const randomY = Math.floor(Math.random() * 5) - 2;
      setOffset({ x: randomX, y: randomY });
    }, 60000);

    return () => clearInterval(interval);
  }, [enabled]);

  return offset;
}
