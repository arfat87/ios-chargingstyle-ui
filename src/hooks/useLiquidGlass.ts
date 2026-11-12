import { useEffect, useRef } from 'react';
import { LiquidGlassEngine, MATERIAL_PRESETS } from 'quick-liquid';

interface UseLiquidGlassOptions {
  preset?: keyof typeof MATERIAL_PRESETS;
  tint?: string;
  tintOpacity?: number;
  refractionStrength?: number;
  blur?: number;
  borderRadius?: number;
  disabled?: boolean;
}

export function useLiquidGlass<T extends HTMLElement>(options: UseLiquidGlassOptions = {}) {
  const elementRef = useRef<T | null>(null);
  const engineRef = useRef<LiquidGlassEngine | null>(null);

  const {
    preset = 'regular',
    tint = '255, 255, 255',
    tintOpacity = 0.08,
    refractionStrength = 16,
    blur = 14,
    borderRadius = 28,
    disabled = false,
  } = options;

  useEffect(() => {
    if (disabled || !elementRef.current || typeof window === 'undefined') {
      if (engineRef.current) {
        engineRef.current.destroy();
        engineRef.current = null;
      }
      return;
    }

    try {
      const basePreset = MATERIAL_PRESETS[preset] || MATERIAL_PRESETS.regular;
      
      const engine = new LiquidGlassEngine(elementRef.current, {
        ...basePreset,
        tint,
        tintOpacity,
        refractionStrength,
        blur,
        borderRadius,
        specularStrength: 0.35,
        edgeHighlight: 0.85,
        hoverLighting: true,
        dynamicLighting: true,
      });

      engineRef.current = engine;

      return () => {
        try {
          engine.destroy();
        } catch {
          // ignore cleanup errors
        }
        engineRef.current = null;
      };
    } catch (err) {
      console.warn('Quick-liquid engine init fallback:', err);
    }
  }, [preset, tint, tintOpacity, refractionStrength, blur, borderRadius, disabled]);

  return elementRef;
}
