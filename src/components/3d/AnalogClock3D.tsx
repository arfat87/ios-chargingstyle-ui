import React, { useRef } from 'react';
import { Canvas, useFrame } from '@react-three/fiber';
import * as THREE from 'three';

interface AnalogClock3DProps {
  nightMode?: boolean;
}

function ClockScene({ nightMode }: { nightMode: boolean }) {
  const hourHandRef = useRef<THREE.Group>(null);
  const minuteHandRef = useRef<THREE.Group>(null);
  const secondHandRef = useRef<THREE.Group>(null);

  useFrame(() => {
    const now = new Date();
    const hours = now.getHours() % 12;
    const minutes = now.getMinutes();
    const seconds = now.getSeconds();
    const millis = now.getMilliseconds();

    // Exact continuous rotation angles (in radians, clockwise -> negative z rotation)
    const secondFraction = seconds + millis / 1000;
    const minuteFraction = minutes + secondFraction / 60;
    const hourFraction = hours + minuteFraction / 60;

    const secondAngle = -(secondFraction / 60) * Math.PI * 2;
    const minuteAngle = -(minuteFraction / 60) * Math.PI * 2;
    const hourAngle = -(hourFraction / 12) * Math.PI * 2;

    if (secondHandRef.current) secondHandRef.current.rotation.z = secondAngle;
    if (minuteHandRef.current) minuteHandRef.current.rotation.z = minuteAngle;
    if (hourHandRef.current) hourHandRef.current.rotation.z = hourAngle;
  });

  const primaryColor = nightMode ? '#ff3b30' : '#ffffff';
  const secondaryColor = nightMode ? '#b31e17' : '#8e8e93';
  const secondHandColor = nightMode ? '#ff453a' : '#ff9f0a';
  const dialColor = nightMode ? '#140303' : '#111216';
  const bezelColor = nightMode ? '#330808' : '#22252e';

  // Generate 12 hour ticks
  const ticks = Array.from({ length: 12 }, (_, i) => {
    const angle = (i / 12) * Math.PI * 2;
    const radius = 2.15;
    const x = Math.sin(angle) * radius;
    const y = Math.cos(angle) * radius;
    const isMajor = i % 3 === 0;
    return (
      <mesh
        key={i}
        position={[x, y, 0.05]}
        rotation={[0, 0, -angle]}
      >
        <boxGeometry args={[isMajor ? 0.08 : 0.04, isMajor ? 0.28 : 0.16, 0.04]} />
        <meshStandardMaterial
          color={isMajor ? primaryColor : secondaryColor}
          emissive={isMajor ? primaryColor : '#000000'}
          emissiveIntensity={nightMode ? 0.4 : 0.1}
          metalness={0.7}
          roughness={0.2}
        />
      </mesh>
    );
  });

  return (
    <>
      <ambientLight intensity={nightMode ? 0.4 : 0.8} />
      <directionalLight position={[2, 3, 4]} intensity={nightMode ? 0.6 : 1.2} />
      <pointLight position={[0, 0, 3]} intensity={nightMode ? 0.8 : 1.0} color={nightMode ? '#ff3b30' : '#ffffff'} />

      {/* Outer Bezel Rim */}
      <mesh position={[0, 0, -0.05]}>
        <cylinderGeometry args={[2.55, 2.6, 0.2, 64]} />
        <meshStandardMaterial
          color={bezelColor}
          metalness={0.85}
          roughness={0.25}
        />
      </mesh>

      {/* Clock Face Dial */}
      <mesh position={[0, 0, 0]}>
        <cylinderGeometry args={[2.45, 2.45, 0.08, 64]} />
        <meshStandardMaterial
          color={dialColor}
          metalness={0.3}
          roughness={0.7}
        />
      </mesh>

      {/* Subtle Inner Ring */}
      <mesh position={[0, 0, 0.045]}>
        <ringGeometry args={[1.5, 1.52, 64]} />
        <meshBasicMaterial color={secondaryColor} transparent opacity={0.25} />
      </mesh>

      {/* Hour Ticks */}
      {ticks}

      {/* Center Pin & Cap */}
      <mesh position={[0, 0, 0.22]}>
        <cylinderGeometry args={[0.12, 0.12, 0.08, 32]} />
        <meshStandardMaterial
          color={primaryColor}
          metalness={0.9}
          roughness={0.1}
        />
      </mesh>
      <mesh position={[0, 0, 0.26]}>
        <sphereGeometry args={[0.07, 16, 16]} />
        <meshStandardMaterial
          color={secondHandColor}
          metalness={0.8}
          roughness={0.2}
        />
      </mesh>

      {/* Hour Hand */}
      <group ref={hourHandRef} position={[0, 0, 0.1]}>
        <mesh position={[0, 0.65, 0]}>
          <boxGeometry args={[0.13, 1.3, 0.04]} />
          <meshStandardMaterial
            color={primaryColor}
            metalness={0.8}
            roughness={0.2}
          />
        </mesh>
      </group>

      {/* Minute Hand */}
      <group ref={minuteHandRef} position={[0, 0, 0.14]}>
        <mesh position={[0, 0.95, 0]}>
          <boxGeometry args={[0.08, 1.9, 0.03]} />
          <meshStandardMaterial
            color={primaryColor}
            metalness={0.8}
            roughness={0.2}
          />
        </mesh>
      </group>

      {/* Second Hand (Sweeping smoothly) */}
      <group ref={secondHandRef} position={[0, 0, 0.18]}>
        <mesh position={[0, 0.85, 0]}>
          <boxGeometry args={[0.03, 2.1, 0.02]} />
          <meshStandardMaterial
            color={secondHandColor}
            emissive={secondHandColor}
            emissiveIntensity={nightMode ? 0.6 : 0.2}
            metalness={0.5}
            roughness={0.3}
          />
        </mesh>
        {/* Second hand counter-weight tail */}
        <mesh position={[0, -0.4, 0]}>
          <boxGeometry args={[0.05, 0.5, 0.02]} />
          <meshStandardMaterial color={secondHandColor} />
        </mesh>
      </group>
    </>
  );
}

export const AnalogClock3D: React.FC<AnalogClock3DProps> = ({ nightMode = false }) => {
  return (
    <div style={{ width: '100%', height: '100%', position: 'relative' }}>
      <Canvas
        camera={{ position: [0, 0, 5.2], fov: 46 }}
        style={{ width: '100%', height: '100%' }}
        gl={{ antialias: true, alpha: true }}
      >
        <ClockScene nightMode={nightMode} />
      </Canvas>
    </div>
  );
};
