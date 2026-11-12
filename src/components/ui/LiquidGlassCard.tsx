import React from 'react';
import { useLiquidGlass } from '../../hooks/useLiquidGlass';

interface LiquidGlassCardProps {
  children: React.ReactNode;
  nightMode?: boolean;
  className?: string;
  style?: React.CSSProperties;
  onClick?: () => void;
  interactive?: boolean;
}

export const LiquidGlassCard: React.FC<LiquidGlassCardProps> = ({
  children,
  nightMode = false,
  className = '',
  style = {},
  onClick,
  interactive = false,
}) => {
  const cardRef = useLiquidGlass<HTMLDivElement>({
    preset: nightMode ? 'thick' : 'regular',
    tint: nightMode ? '255, 59, 48' : '255, 255, 255',
    tintOpacity: nightMode ? 0.08 : 0.05,
    blur: nightMode ? 18 : 16,
    borderRadius: 28,
  });

  return (
    <div
      ref={cardRef}
      className={`liquid-glass-card ${className}`}
      onClick={onClick}
      style={{
        position: 'relative',
        width: '100%',
        height: '100%',
        borderRadius: '28px',
        backgroundColor: nightMode
          ? 'rgba(25, 4, 4, 0.72)'
          : 'rgba(28, 29, 36, 0.65)',
        backdropFilter: 'blur(30px) saturate(180%)',
        WebkitBackdropFilter: 'blur(30px) saturate(180%)',
        border: nightMode
          ? '1px solid rgba(255, 59, 48, 0.22)'
          : '1px solid rgba(255, 255, 255, 0.14)',
        boxShadow: nightMode
          ? '0 16px 40px rgba(0, 0, 0, 0.9), inset 0 1px 0 rgba(255, 59, 48, 0.25)'
          : '0 20px 45px rgba(0, 0, 0, 0.6), inset 0 1px 0 rgba(255, 255, 255, 0.25)',
        overflow: 'hidden',
        cursor: interactive ? 'pointer' : 'default',
        transition: 'transform 0.25s cubic-bezier(0.2, 0.8, 0.2, 1), box-shadow 0.25s ease, background-color 0.4s ease, border-color 0.4s ease',
        boxSizing: 'border-box',
        ...style,
      }}
    >
      {/* Liquid Glass Highlight Reflection Line on Top */}
      <div
        style={{
          position: 'absolute',
          top: 0,
          left: '10%',
          right: '10%',
          height: '1px',
          background: nightMode
            ? 'linear-gradient(90deg, transparent, rgba(255, 59, 48, 0.6), transparent)'
            : 'linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.5), transparent)',
          pointerEvents: 'none',
        }}
      />
      {children}
    </div>
  );
};
