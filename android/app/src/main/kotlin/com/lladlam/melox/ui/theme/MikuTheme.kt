package com.lladlam.melox.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.lladlam.melox.ui.settings.MeloXSettingsRuntime
import com.lladlam.melox.ui.settings.MeloXThemeStyle

// Independently implemented Material 3 palette, based on miku-navigation 0.9.4.
// Artwork is optional and is NOT licensed by the app's source-code license.
internal val MikuLightColors = lightColorScheme(
    primary = Color(0xFF087F79), onPrimary = Color.White,
    primaryContainer = Color(0xFFD7EEE8), onPrimaryContainer = Color(0xFF066D68),
    secondary = Color(0xFF137E8C), onSecondary = Color.White,
    secondaryContainer = Color(0xFFE4F2ED), onSecondaryContainer = Color(0xFF183B3D),
    tertiary = Color(0xFFA74870), onTertiary = Color.White,
    tertiaryContainer = Color(0xFFF7E3EB), onTertiaryContainer = Color(0xFF672541),
    background = Color(0xFFEAF4F3), onBackground = Color(0xFF183B3D),
    surface = Color(0xFFF8FCFA), onSurface = Color(0xFF183B3D),
    surfaceVariant = Color(0xFFE4F2ED), onSurfaceVariant = Color(0xFF526C6E),
    surfaceTint = Color(0xFF087F79), inverseSurface = Color(0xFF193137),
    inverseOnSurface = Color(0xFFE7F4EF), inversePrimary = Color(0xFF63D6C8),
    outline = Color(0xFFCADDD7), outlineVariant = Color(0xFFDCEFEB),
    surfaceDim = Color(0xFFDCEFEB), surfaceBright = Color(0xFFF8FCFA),
    surfaceContainerLowest = Color(0xFFF8FCFA), surfaceContainerLow = Color(0xFFEFF7F3),
    surfaceContainer = Color(0xFFEAF4F3), surfaceContainerHigh = Color(0xFFE4F2ED),
    surfaceContainerHighest = Color(0xFFD7EEE8),
    // Keep MeloX's danger semantics, never repaint destructive actions teal.
    error = LightColors.error, onError = LightColors.onError,
    errorContainer = LightColors.errorContainer, onErrorContainer = LightColors.onErrorContainer,
)

internal val MikuDarkColors = darkColorScheme(
    primary = Color(0xFF63D6C8), onPrimary = Color(0xFF102429),
    primaryContainer = Color(0xFF244A49), onPrimaryContainer = Color(0xFF8BE5D8),
    secondary = Color(0xFF78CBD1), onSecondary = Color(0xFF102429),
    secondaryContainer = Color(0xFF244341), onSecondaryContainer = Color(0xFFE7F4EF),
    tertiary = Color(0xFFE69BB7), onTertiary = Color(0xFF3D192A),
    tertiaryContainer = Color(0xFF51303F), onTertiaryContainer = Color(0xFFF7D6E3),
    background = Color(0xFF102429), onBackground = Color(0xFFE7F4EF),
    surface = Color(0xFF193137), onSurface = Color(0xFFE7F4EF),
    surfaceVariant = Color(0xFF244341), onSurfaceVariant = Color(0xFFA5BFBD),
    surfaceTint = Color(0xFF63D6C8), inverseSurface = Color(0xFFF8FCFA),
    inverseOnSurface = Color(0xFF183B3D), inversePrimary = Color(0xFF087F79),
    outline = Color(0xFF355354), outlineVariant = Color(0xFF244341),
    surfaceDim = Color(0xFF102429), surfaceBright = Color(0xFF244341),
    surfaceContainerLowest = Color(0xFF0C1D21), surfaceContainerLow = Color(0xFF142B30),
    surfaceContainer = Color(0xFF193137), surfaceContainerHigh = Color(0xFF203B3C),
    surfaceContainerHighest = Color(0xFF244A49),
    error = DarkColors.error, onError = DarkColors.onError,
    errorContainer = DarkColors.errorContainer, onErrorContainer = DarkColors.onErrorContainer,
)

@Composable
internal fun meloXColorScheme(dark: Boolean): ColorScheme =
    if (MeloXSettingsRuntime.themeStyle == MeloXThemeStyle.Miku) {
        if (dark) MikuDarkColors else MikuLightColors
    } else {
        if (dark) DarkColors else LightColors
    }
