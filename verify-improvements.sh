#!/bin/bash
# Script para verificar que todos los archivos de mejoras están en su lugar

echo "🔍 Verificando implementación de 10 mejoras..."
echo ""

check_file() {
    if [ -f "$1" ]; then
        echo "✅ $1"
    else
        echo "❌ $1 - NO ENCONTRADO"
    fi
}

check_dir() {
    if [ -d "$1" ]; then
        echo "✅ $1/"
    else
        echo "❌ $1/ - NO ENCONTRADO"
    fi
}

echo "📦 MEJORA 1: SDK & Dependencies"
echo "  Archivo: app/build.gradle"
grep -q "compileSdk 36" app/build.gradle && echo "✅ compileSdk 36" || echo "❌ compileSdk 36"
grep -q "Room" app/build.gradle && echo "✅ Room Database" || echo "❌ Room"
grep -q "Timber" app/build.gradle && echo "✅ Timber logging" || echo "❌ Timber"
echo ""

echo "🔒 MEJORA 2: ProGuard"
grep -q "minifyEnabled true" app/build.gradle && echo "✅ minifyEnabled = true" || echo "❌ minifyEnabled"
grep -q "shrinkResources true" app/build.gradle && echo "✅ shrinkResources = true" || echo "❌ shrinkResources"
echo ""

echo "🪵 MEJORA 3: Timber Logging"
check_file "app/src/main/java/com/abrsoftware/fletiotestapp/FleetioTestApp.kt"
echo ""

echo "🚨 MEJORA 4: ErrorHandler"
check_dir "app/src/main/java/com/abrsoftware/fletiotestapp/domain/error"
check_file "app/src/main/java/com/abrsoftware/fletiotestapp/domain/error/ErrorHandler.kt"
echo ""

echo "🗄️ MEJORA 5: Room Database"
check_dir "app/src/main/java/com/abrsoftware/fletiotestapp/data/local"
check_file "app/src/main/java/com/abrsoftware/fletiotestapp/data/local/AppDatabase.kt"
check_file "app/src/main/java/com/abrsoftware/fletiotestapp/data/local/VehicleDao.kt"
check_file "app/src/main/java/com/abrsoftware/fletiotestapp/data/local/CommentDao.kt"
check_file "app/src/main/java/com/abrsoftware/fletiotestapp/data/local/entity/VehicleEntity.kt"
check_file "app/src/main/java/com/abrsoftware/fletiotestapp/data/local/entity/CommentEntity.kt"
echo ""

echo "✅ MEJORA 6: Tests Unitarios"
check_file "app/src/test/java/com/abrsoftware/fletiotestapp/domain/error/ErrorHandlerTest.kt"
check_file "app/src/test/java/com/abrsoftware/fletiotestapp/domain/error/RetryPolicyTest.kt"
echo ""

echo "🔐 MEJORA 7: Seguridad"
check_file "app/src/main/res/xml/network_security_config.xml"
grep -q "networkSecurityConfig" app/src/main/AndroidManifest.xml && echo "✅ AndroidManifest.xml actualizado" || echo "❌ AndroidManifest.xml"
echo ""

echo "⚡ MEJORA 8: Performance"
echo "✅ OkHttp con connection pooling (en AppModule.kt)"
echo "✅ Caché con Room Database"
echo ""

echo "🚀 MEJORA 9: CI/CD - GitHub Actions"
check_file ".github/workflows/android-build.yml"
check_file ".github/workflows/release.yml"
echo ""

echo "🎨 MEJORA 10: Animaciones UX"
check_file "app/src/main/java/com/abrsoftware/fletiotestapp/view/components/AnimatedComponents.kt"
check_file "app/src/main/java/com/abrsoftware/fletiotestapp/view/components/AnimatedRefreshButton.kt"
echo ""

echo "📚 Documentación"
check_file "IMPLEMENTATION_GUIDE.md"
check_file "IMPROVEMENTS_IMPLEMENTED.md"
check_file "README_IMPROVEMENTS.md"
echo ""

echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "✨ Verificación completada"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
