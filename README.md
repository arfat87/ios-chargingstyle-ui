# ⚡ StandBy+ | Your Phone. Your Smart Display.

> Premium Android & Web Smart Display experience inspired by Apple iOS 17 StandBy Mode, powered by **Quick-Liquid**, **React Three Fiber (Three.js)**, and native **Android DreamService**.

---

## 🚀 Key Highlights & Tech Stack

- **3D Clocks Studio (React Three Fiber & Three.js)**:
  - **3D Analog Clock**: Real-time sweeping second hand, metallic dial, ambient lighting, and glass bezel reflections.
  - **Retro Split-Flap 3D Flip Clock**: Animated 3D cards with mechanical click audio feedback.
  - **Bold Minimalist Digital Clock**: High-res typography, smooth seconds counter, and dynamic date pill.
  - **World Clocks**: Multi-timezone live display for London, New York, Tokyo, and New Delhi.
- **Apple Liquid Glass UI (`quick-liquid`)**:
  - Frosted glassmorphism with dynamic light refraction and specular highlights.
- **Modular Smart Stack Widgets**:
  - 🌦️ **Weather Hub**: Real-time conditions, high/low, and hourly timeline.
  - 📅 **Calendar & Agenda**: Large calendar numeral badge with schedule preview.
  - 🔋 **Battery & Watts Hub**: Circular charging ring, charging rate (Watts), and thermal stats.
  - 🎵 **Music Studio**: Active album art, live frequency audio visualizer bars, and media controls.
  - 🖼️ **Photo Frame**: Ambient landscape slideshow with clock overlay.
  - ✅ **Bedside Reminders**: Interactive checklist.
- **Night Mode (Apple Red Shift)**:
  - Deep monochrome red tint (`#ff3b30`) with ultra-dim brightness level control.
  - **AMOLED Burn-in Protection**: Active micro pixel-shifting (+/- 2px shift every 60s) to protect OLED displays.
- **Zero-Dependency Web Audio Synthesizer**:
  - iOS Harmonic Charging Chime (resonant double tone).
  - Mechanical Flip Clock tick and tactile haptic taps.

---

## 🛠️ Local Development

```bash
# 1. Install dependencies
npm install

# 2. Run dev server
npm run dev

# 3. Build for production
npm run build
```

---

## 📱 Native Android Packaging (Capacitor)

This project is pre-configured with `@capacitor/core` and `@capacitor/cli` (`com.standbyplus.app`).

```bash
# 1. Build the production web bundle
npm run build

# 2. Add Android platform
npx cap add android

# 3. Sync web assets to Android project
npx cap sync

# 4. Open in Android Studio & generate signed APK
npx cap open android
```

### Native Android DreamService (Screensaver) Integration
In Android Settings -> Display -> Screensaver, StandBy+ registers as a `DreamService` to launch automatically whenever your phone is charging on a dock.
