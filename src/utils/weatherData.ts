import type { WeatherData, CalendarEvent, MusicTrack } from '../types';

export const INITIAL_WEATHER: WeatherData = {
  city: 'Mumbai',
  temperature: 28,
  condition: 'Partly Cloudy',
  high: 31,
  low: 24,
  humidity: 68,
  forecast: [
    { time: 'Now', temp: 28, icon: 'cloud-sun' },
    { time: '10 PM', temp: 27, icon: 'moon' },
    { time: '11 PM', temp: 26, icon: 'cloud-moon' },
    { time: '12 AM', temp: 25, icon: 'moon' },
    { time: '1 AM', temp: 24, icon: 'moon' },
  ],
};

export const CITIES_WEATHER: Record<string, WeatherData> = {
  Mumbai: INITIAL_WEATHER,
  Delhi: {
    city: 'New Delhi',
    temperature: 22,
    condition: 'Clear Sky',
    high: 26,
    low: 18,
    humidity: 45,
    forecast: [
      { time: 'Now', temp: 22, icon: 'moon' },
      { time: '10 PM', temp: 21, icon: 'moon' },
      { time: '11 PM', temp: 20, icon: 'moon' },
      { time: '12 AM', temp: 19, icon: 'moon' },
      { time: '1 AM', temp: 18, icon: 'moon' },
    ],
  },
  London: {
    city: 'London',
    temperature: 14,
    condition: 'Light Rain',
    high: 16,
    low: 11,
    humidity: 82,
    forecast: [
      { time: 'Now', temp: 14, icon: 'cloud-rain' },
      { time: '6 PM', temp: 13, icon: 'cloud-rain' },
      { time: '7 PM', temp: 13, icon: 'cloud' },
      { time: '8 PM', temp: 12, icon: 'moon' },
      { time: '9 PM', temp: 11, icon: 'cloud-moon' },
    ],
  },
  Cupertino: {
    city: 'Cupertino',
    temperature: 19,
    condition: 'Sunny',
    high: 23,
    low: 15,
    humidity: 50,
    forecast: [
      { time: 'Now', temp: 19, icon: 'sun' },
      { time: '3 PM', temp: 22, icon: 'sun' },
      { time: '4 PM', temp: 23, icon: 'sun' },
      { time: '5 PM', temp: 21, icon: 'sun' },
      { time: '6 PM', temp: 18, icon: 'sunset' },
    ],
  },
};

export const UPCOMING_EVENTS: CalendarEvent[] = [
  {
    id: 'ev-1',
    title: 'StandBy+ Sprint Review',
    time: '10:00 AM - 11:00 AM',
    tagColor: '#30d158',
    location: 'Design Studio Room 4',
  },
  {
    id: 'ev-2',
    title: 'Product Architecture Sync',
    time: '02:30 PM - 03:15 PM',
    tagColor: '#0a84ff',
    location: 'Google Meet',
  },
  {
    id: 'ev-3',
    title: 'Night Charging Test & Benchmarks',
    time: '08:00 PM',
    tagColor: '#ff9f0a',
  },
];

export const DEMO_TRACKS: MusicTrack[] = [
  {
    title: 'Midnight Reverie',
    artist: 'Apple Ambient Lab',
    album: 'StandBy Sessions Vol. 1',
    duration: 214,
    coverUrl: 'https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?auto=format&fit=crop&w=400&q=80',
  },
  {
    title: 'Fluid Neon Dreams',
    artist: 'Synthesize Collective',
    album: 'Liquid Glass Horizons',
    duration: 182,
    coverUrl: 'https://images.unsplash.com/photo-1550684848-fac1c5b4e853?auto=format&fit=crop&w=400&q=80',
  },
  {
    title: 'Starlight Lofi',
    artist: 'Bedside Waves',
    album: 'Sleep & Charge',
    duration: 245,
    coverUrl: 'https://images.unsplash.com/photo-1518709268805-4e9042af9f23?auto=format&fit=crop&w=400&q=80',
  },
];

export const CURATED_PHOTOS = [
  {
    url: 'https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=1400&q=80',
    title: 'Yosemite Valley at Dusk',
    date: 'October 2026',
  },
  {
    url: 'https://images.unsplash.com/photo-1511497584788-87676104235f?auto=format&fit=crop&w=1400&q=80',
    title: 'Misty Alpine Forest',
    date: 'September 2026',
  },
  {
    url: 'https://images.unsplash.com/photo-1470770841072-f978cf4d019e?auto=format&fit=crop&w=1400&q=80',
    title: 'Quiet Lakeside Sunset',
    date: 'August 2026',
  },
  {
    url: 'https://images.unsplash.com/photo-1519681393784-d120267933ba?auto=format&fit=crop&w=1400&q=80',
    title: 'Mount Rainier Milky Way',
    date: 'November 2026',
  },
];
