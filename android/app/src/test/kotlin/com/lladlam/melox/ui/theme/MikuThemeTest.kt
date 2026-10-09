package com.lladlam.melox.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MikuThemeTest {
    @Test fun sourcePaletteIsPreserved() {
        assertEquals(Color(0xFF087F79), MikuLightColors.primary)
        assertEquals(Color(0xFFEAF4F3), MikuLightColors.background)
        assertEquals(Color(0xFF63D6C8), MikuDarkColors.primary)
        assertEquals(Color(0xFF102429), MikuDarkColors.background)
    }

    @Test fun bodyTextHasAccessibleContrast() {
        listOf(MikuLightColors, MikuDarkColors).forEach { scheme ->
            listOf(scheme.onBackground to scheme.background,
                scheme.onSurface to scheme.surface,
                scheme.onPrimary to scheme.primary).forEach { (fg, bg) ->
                val a = fg.luminance(); val b = bg.luminance()
                assertTrue((maxOf(a, b) + .05f) / (minOf(a, b) + .05f) >= 4.5f)
            }
        }
    }

    @Test fun dangerColorIsNotRepainted() {
        assertEquals(LightColors.error, MikuLightColors.error)
        assertEquals(DarkColors.error, MikuDarkColors.error)
    }
}
