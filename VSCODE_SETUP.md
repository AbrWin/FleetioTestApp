# 🚀 Ejecutar FleetioTestApp desde VS Code

## Requisitos Previos

### 1. Android SDK
Desde que tienes Android Studio instalado, el SDK ya está disponible. Verifica:

```bash
echo $ANDROID_HOME
```

Si no aparece nada, configura la variable de entorno en `~/.zshrc`:

```bash
# Agrega al final de ~/.zshrc
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools:$PATH
```

Aplica los cambios:
```bash
source ~/.zshrc
```

### 2. Emulador Android
Abre Android Studio y crea un emulador:
- **Android Studio** → **Device Manager** → **Create Device**
- Selecciona un dispositivo (ej: Pixel 4)
- Selecciona una versión de Android (ej: API 31)

### 3. Java
Verifica que tengas Java instalado:
```bash
java -version
# Debe mostrar Java 11 o superior
```

---

## 📋 Tareas Disponibles

Presiona `Ctrl+Shift+B` (o Cmd+Shift+B en Mac) para ver todas las tareas:

### **Principal**
- **Build Debug APK** - Compila la app en modo debug
- **Build and Run** - Compila e instala en emulador

### **Desarrollo**
- **Clean Project** - Limpia archivos de build
- **Start Emulator** - Inicia el emulador Android
- **Launch App (Debug)** - Ejecuta la app en el dispositivo/emulador

### **Testing**
- **Run Unit Tests** - Ejecuta tests unitarios
- **Run Android Tests** - Ejecuta tests en emulador (Instrumented)

---

## 🎮 Cómo Ejecutar

### Opción 1: Build y Run Rápido
```bash
# Accede a Terminal → Run Task (Cmd+Shift+P)
# Busca: "Build and Run"
# Enter
```

Esto:
1. ✅ Compila la app
2. ✅ Instala en el emulador
3. ✅ Abre la app automáticamente

### Opción 2: Paso a Paso
```bash
# 1. Inicia el emulador (desde Android Studio o VS Code)
# 2. Cmd+Shift+P → "Tasks: Run Task" → "Build Debug APK"
# 3. Cmd+Shift+P → "Tasks: Run Task" → "Launch App (Debug)"
```

### Opción 3: Terminal Manual
```bash
# Compilar
./gradlew assembleDebug

# Instalar en emulador
./gradlew installDebug

# Ejecutar
adb shell am start -n com.abrsoftware.fletiotestapp/.MainActivity

# Ver logs
adb logcat | grep "FleetioTestApp"
```

---

## 🔍 Debugging

### Ver Logs en Tiempo Real
```bash
# Opción 1: Command Palette
Cmd+Shift+P → "Logcat Monitor"

# Opción 2: Terminal
adb logcat -s FleetioTestApp
```

### Usando Breakpoints (teoricamente funciona con extensiones)
1. Instala: **Android Studio Tools**
2. Establece breakpoints en el código
3. Abre Debug con `F5`

---

## 🛠️ Troubleshooting

### "No se encuentra emulador"
```bash
# Lista emuladores disponibles
emulator -list-avds

# Inicia un emulador específico
emulator -avd Pixel_4_API_31
```

### "error: device not found"
```bash
# Reinicia el servidor ADB
adb kill-server
adb start-server
```

### "ANDROID_HOME not found"
```bash
# Verifica la instalación de Android SDK
ls ~/Library/Android/sdk

# Si no existe, ajusta en ~/.zshrc
export ANDROID_HOME=$HOME/Library/Android/sdk
```

### Gradle compilation error
```bash
# Limpia y reconstruye
./gradlew clean
./gradlew build
```

---

## 📚 Extensiones Recomendadas

Para mejor experiencia en VS Code:

- **Kotlin Language** ✅ (ya instalada)
- **Gradle for Java** ✅ (ya instalada)
- **C/C++** - Si el proyecto usa código nativo
- **Material Icon Theme** - Tema visual
- **Error Lens** - Muestra errores inline

---

## 🚀 Próximos Pasos

1. Asegúrate que `ANDROID_HOME` esté configurado
2. Inicia un emulador desde Android Studio
3. Abre VS Code y presiona `Cmd+Shift+B`
4. Selecciona **"Build and Run"**
5. ¡A disfrutar! 🎉

---

## 📞 Notas

- El proyecto usa **Jetpack Compose**, por eso es importante tener Android 5.0+ (API 21)
- Los archivos de configuración están en `.vscode/`
- Las tareas usan `./gradlew` que es multiplataforma (Mac, Linux, Windows)
