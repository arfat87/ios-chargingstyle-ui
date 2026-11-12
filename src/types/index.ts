export type ClockType = 'digital' | 'analog3d' | 'flip' | 'world';

export type WidgetType = 'weather' | 'calendar' | 'battery' | 'music' | 'photo' | 'reminders';

export type ThemeId = 'minimal-black' | 'midnight-blue' | 'retro-cream' | 'cyber-neon' | 'red-night';

export type StandByLayout = 'split' | 'full-clock' | 'full-photo';

export interface AppSettings {
  timeFormat: '12h' | '24h';
  showSeconds: boolean;
  nightMode: boolean;
  nightModeBrightness: number; // 10 to 100
  burnInProtection: boolean;
  isCharging: boolean;
  batteryLevel: number;
  chargingWatts: number;
  theme: ThemeId;
  soundEnabled: boolean;
  currentLayout: StandByLayout;
  leftWidget: WidgetType;
  rightWidget: WidgetType;
  clockType: ClockType;
  showPhoneBezel: boolean;
}

export interface WeatherData {
  city: string;
  temperature: number;
  condition: string;
  high: number;
  low: number;
  humidity: number;
  forecast: { time: string; temp: number; icon: string }[];
}

export interface CalendarEvent {
  id: string;
  title: string;
  time: string;
  tagColor: string;
  location?: string;
}

export interface MusicTrack {
  title: string;
  artist: string;
  album: string;
  duration: number; // in seconds
  coverUrl: string;
}
