# ⚡ StandBy+ | Your Phone. Your Smart Display.

> Premium Android & Web Smart Display experience inspired by Apple iOS 17 StandBy Mode, powered by **Quick-Liquid**, **React Three Fiber (Three.js)**, and a **Native Jetpack Compose Android Application** (`StandByPlus`).

---

## 🌟 Architecture & Dual-Track Implementation

This repository provides two complete, production-grade implementations:

1. **Interactive Web Simulation (`/`)**:
   - **Framework**: React 18, TypeScript, Tailwind CSS, Vite
   - **3D Engine**: `@react-three/fiber`, `@react-three/drei`, `three`
   - **Physics / Glassmorphism**: `quick-liquid` Apple Liquid Glass Engine
   - **Audio Engine**: Custom zero-dependency Web Audio API synthesizer for iOS harmonic chimes and mechanical ticks
   - **Standalone Preview**: Available in [`public/standalone-preview.html`](public/standalone-preview.html)

2. **Native Android Application (`/StandByPlus`)**:
   - **Target SDK**: Android 10+ (API 29–34)
   - **UI Engine**: 100% Kotlin + Jetpack Compose + Material 3
   - **Architecture**: Clean Architecture + MVVM + Repository Pattern
   - **Persistence**: Room Database (`StandByDatabase`, `LayoutDao`, `ThemeDao`) + DataStore Preferences (`StandByDataStore`)
   - **Screensaver Engine**: Native Android `DreamService` (`StandByDreamService.kt`) for automatic dock & charge display activation
   - **Charging Detection**: `ChargingReceiver.kt` for `ACTION_POWER_CONNECTED` with notification fallback
   - **OLED Protection**: `NightModeEngine.kt` with automated 60-second ±1px micro pixel-shifting
   - **Unit Testing**: JUnit 4 unit tests verifying time calculations and night mode logic (`./gradlew testDebugUnitTest`)

---

## 🚀 Key Features

### 🕒 Clock Studio
- **3D Analog Clock**: Real-time sweeping second hand, metallic bezel, ambient lighting, and glass reflections.
- **Retro Split-Flap Flip Clock**: Flip cards with authentic mechanical click audio feedback.
- **Bold Minimalist Digital Clock**: High-res typography, smooth seconds counter, and dynamic date pill.
- **World Clocks**: Multi-timezone live display for London, New York, Tokyo, and New Delhi.

### 📊 Smart Widgets Stack
- 🌦️ **Weather Hub**: Real-time conditions, high/low, and hourly timeline.
- 📅 **Calendar & Agenda**: Large calendar numeral badge with schedule preview.
- 🔋 **Battery & Watts Hub**: Circular charging ring, charging rate (Watts), and thermal stats.
- 🎵 **Music Studio**: Active album art, live frequency audio visualizer bars, and media controls.
- 🖼️ **Photo Frame**: Ambient landscape slideshow with clock overlay.
- ✅ **Bedside Reminders**: Interactive checklist with tactile tap sounds.

### 🌙 Night Mode & AMOLED Care
- Deep monochrome red tint (`#ff3b30` / `#ff453a`) with ultra-dim brightness level control.
- **AMOLED Burn-in Protection**: Active micro pixel-shifting (±1px shift every 60s) to safeguard OLED panels.

---

## 🛠️ Web App Development & Preview

```bash
# 1. Install dependencies
npm install

# 2. Run dev server (default port 5173)
npm run dev

# 3. Build optimized web bundle
npm run build
```

---

## 📱 Native Android Project (`StandByPlus`)

The native Android project is located under `StandByPlus/`:

```
StandByPlus/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/standbyplus/app/
│   │   │   ├── data/
│   │   │   │   ├── db/ (StandByDatabase, LayoutEntity, ThemeEntity, DAOs)
│   │   │   │   └── preferences/ (StandByDataStore)
│   │   │   ├── engine/ (ClockEngine, NightModeEngine)
│   │   │   ├── receiver/ (ChargingReceiver)
│   │   │   ├── service/ (StandByDreamService)
│   │   │   ├── ui/
│   │   │   │   ├── clocks/ (DigitalClockView, FlipClockView, AnalogClockView)
│   │   │   │   ├── screens/ (11 Compose screens: Splash, Onboarding, StandBy, Studios...)
│   │   │   │   ├── theme/ (Color, Theme, Type)
│   │   │   │   └── widgets/ (Weather, Calendar, Battery, Music, Reminders)
│   │   │   └── MainActivity.kt, StandByActivity.kt
│   └── build.gradle.kts
└── gradlew, settings.gradle.kts
```

### Build & Run Native Android App

```bash
cd StandByPlus

# Run Unit Tests
./gradlew testDebugUnitTest

# Assemble Debug APK
./gradlew assembleDebug

# Output APK location:
# app/build/outputs/apk/debug/app-debug.apk
```

### Install APK onto Android Device

```bash
adb install -r StandByPlus/app/build/outputs/apk/debug/app-debug.apk
```

### How to Enable Screensaver on Android

1. Open Android **Settings** -> **Display** -> **Screen saver** (Daydream).
2. Select **StandBy+ Smart Display**.
3. Set **When to start** to *"While charging"* or *"While docked and charging"*.
4. Place your phone horizontally on your bedside charger to experience StandBy+!

---

## 📄 License
MIT
