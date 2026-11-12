import React, { useState } from 'react';
import { X, Copy, Check, Cpu } from 'lucide-react';

interface AndroidIntegrationModalProps {
  isOpen: boolean;
  onClose: () => void;
}

export const AndroidIntegrationModal: React.FC<AndroidIntegrationModalProps> = ({ isOpen, onClose }) => {
  const [activeTab, setActiveTab] = useState<'dreamservice' | 'receiver' | 'capacitor' | 'manifest'>('dreamservice');
  const [copied, setCopied] = useState(false);

  if (!isOpen) return null;

  const dreamServiceCode = `// 1. StandByDreamService.kt (Native Android Screensaver / Daydream API)
// Automatically activates when charging on a dock without background launch limits!
package com.standbyplus.app.service

import android.service.dreams.DreamService
import android.webkit.WebSettings
import android.webkit.WebView
import android.view.View

class StandByDreamService : DreamService() {
    private var webView: WebView? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        isInteractive = true
        isFullscreen = true
        isScreenBright = false // Uses ambient / night mode brightness

        webView = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.cacheMode = WebSettings.LOAD_DEFAULT
            setLayerType(View.LAYER_TYPE_HARDWARE, null)
            loadUrl("https://localhost/index.html") // or bundled assets
        }
        setContentView(webView)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        webView?.destroy()
    }
}`;

  const receiverCode = `// 2. ChargingReceiver.kt (Charging & Tilt Trigger)
package com.standbyplus.app.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.PendingIntent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.standbyplus.app.MainActivity

class ChargingReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_POWER_CONNECTED) {
            // Android 10+ Background Launch: Use high-priority FullScreenIntent
            val fullScreenIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                putExtra("TRIGGERED_BY_CHARGER", true)
            }
            val pendingIntent = PendingIntent.getActivity(
                context, 0, fullScreenIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // Auto-launch via fullScreenIntent notification
            val notification = NotificationCompat.Builder(context, "STANDBY_CHANNEL")
                .setSmallIcon(android.R.drawable.ic_lock_idle_charging)
                .setContentTitle("StandBy+ Activated")
                .setContentText("Docked and charging in landscape")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setFullScreenIntent(pendingIntent, true)
                .setAutoCancel(true)
                .build()

            NotificationManagerCompat.from(context).notify(101, notification)
        }
    }
}`;

  const manifestCode = `<!-- 3. AndroidManifest.xml (Permissions & Services) -->
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.USE_FULL_SCREEN_INTENT" />

    <application
        android:label="StandBy+"
        android:theme="@android:style/Theme.NoTitleBar.Fullscreen">

        <!-- StandBy DreamService (Screensaver) -->
        <service
            android:name=".service.StandByDreamService"
            android:exported="true"
            android:icon="@mipmap/ic_launcher"
            android:label="StandBy+ Smart Display"
            android:permission="android.permission.BIND_DREAM_SERVICE">
            <intent-filter>
                <action android:name="android.service.dreams.DreamService" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
            <meta-data
                android:name="android.service.dream"
                android:resource="@xml/dream_info" />
        </service>

        <!-- Charging Broadcast Receiver -->
        <receiver
            android:name=".receiver.ChargingReceiver"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.ACTION_POWER_CONNECTED" />
                <action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" />
            </intent-filter>
        </receiver>
    </application>
</manifest>`;

  const capacitorCode = `# 4. Packaging into Native Android APK (Capacitor Integration)

# Step 1: Build production web bundle
npm run build

# Step 2: Add Capacitor Android platform
npx cap add android

# Step 3: Copy web assets & plugins to Android project
npx cap sync

# Step 4: Open in Android Studio & Generate Signed APK / AAB
npx cap open android

# Ready to run on any Samsung, Pixel, OnePlus or Xiaomi device!`;

  const getActiveCode = () => {
    switch (activeTab) {
      case 'dreamservice':
        return dreamServiceCode;
      case 'receiver':
        return receiverCode;
      case 'manifest':
        return manifestCode;
      case 'capacitor':
        return capacitorCode;
    }
  };

  const handleCopy = () => {
    navigator.clipboard.writeText(getActiveCode());
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
  };

  return (
    <div
      style={{
        position: 'fixed',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        backgroundColor: 'rgba(0, 0, 0, 0.85)',
        backdropFilter: 'blur(16px)',
        zIndex: 110,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        padding: '1.5rem',
        boxSizing: 'border-box',
      }}
      onClick={onClose}
    >
      <div
        onClick={(e) => e.stopPropagation()}
        style={{
          width: '100%',
          maxWidth: '750px',
          maxHeight: '90vh',
          backgroundColor: '#12141a',
          borderRadius: '28px',
          border: '1px solid rgba(255, 255, 255, 0.15)',
          boxShadow: '0 25px 60px rgba(0, 0, 0, 0.9)',
          display: 'flex',
          flexDirection: 'column',
          overflow: 'hidden',
          color: '#ffffff',
          fontFamily: "'Inter', sans-serif",
        }}
      >
        {/* Header */}
        <div
          style={{
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'center',
            padding: '1.4rem 1.8rem',
            borderBottom: '1px solid rgba(255, 255, 255, 0.1)',
          }}
        >
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.6rem' }}>
            <Cpu size={22} color="#0a84ff" />
            <div>
              <h2 style={{ margin: 0, fontSize: '1.2rem', fontWeight: 800 }}>
                StandBy+ Android Native Architecture
              </h2>
              <div style={{ fontSize: '0.78rem', color: '#8e8e93' }}>
                Complete Blueprint implementation & APK packaging guide
              </div>
            </div>
          </div>
          <button
            onClick={onClose}
            style={{
              background: 'rgba(255, 255, 255, 0.1)',
              border: 'none',
              color: '#ffffff',
              borderRadius: '50%',
              width: '36px',
              height: '36px',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              cursor: 'pointer',
            }}
          >
            <X size={18} />
          </button>
        </div>

        {/* Tab Bar */}
        <div
          style={{
            display: 'flex',
            gap: '0.5rem',
            padding: '0.8rem 1.8rem 0',
            borderBottom: '1px solid rgba(255, 255, 255, 0.08)',
          }}
        >
          {[
            { id: 'dreamservice', label: 'DreamService.kt' },
            { id: 'receiver', label: 'ChargingReceiver.kt' },
            { id: 'manifest', label: 'AndroidManifest.xml' },
            { id: 'capacitor', label: 'APK Build (Capacitor)' },
          ].map((tab) => (
            <button
              key={tab.id}
              onClick={() => setActiveTab(tab.id as typeof activeTab)}
              style={{
                padding: '0.6rem 1rem',
                border: 'none',
                backgroundColor: 'transparent',
                color: activeTab === tab.id ? '#0a84ff' : '#8e8e93',
                fontSize: '0.85rem',
                fontWeight: 700,
                cursor: 'pointer',
                borderBottom: activeTab === tab.id ? '2px solid #0a84ff' : '2px solid transparent',
              }}
            >
              {tab.label}
            </button>
          ))}
        </div>

        {/* Code Editor Preview */}
        <div
          style={{
            position: 'relative',
            flex: 1,
            padding: '1.2rem 1.8rem',
            overflowY: 'auto',
            backgroundColor: '#0a0b0e',
          }}
        >
          <button
            onClick={handleCopy}
            style={{
              position: 'absolute',
              top: '1.6rem',
              right: '2.4rem',
              display: 'flex',
              alignItems: 'center',
              gap: '0.4rem',
              padding: '0.4rem 0.8rem',
              borderRadius: '12px',
              backgroundColor: 'rgba(255, 255, 255, 0.12)',
              border: '1px solid rgba(255, 255, 255, 0.2)',
              color: '#ffffff',
              fontSize: '0.75rem',
              fontWeight: 700,
              cursor: 'pointer',
            }}
          >
            {copied ? <Check size={14} color="#30d158" /> : <Copy size={14} />}
            <span>{copied ? 'Copied' : 'Copy Code'}</span>
          </button>

          <pre
            style={{
              fontFamily: "'JetBrains Mono', monospace",
              fontSize: '0.82rem',
              lineHeight: 1.6,
              color: '#e5e7eb',
              margin: 0,
              whiteSpace: 'pre-wrap',
            }}
          >
            {getActiveCode()}
          </pre>
        </div>
      </div>
    </div>
  );
};
