package com.lladlam.melox.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.lladlam.melox.ui.settings.MeloXSettingsRuntime
import com.lladlam.melox.ui.settings.MeloXThemeStyle

// Optional locally imported assets; no unlicensed third-party art in source patches.
@Composable
private fun hasMikuAsset(path: String): Boolean {
    val context = LocalContext.current
    return remember(context, path) {
        runCatching { context.assets.open("miku/$path").use { } }.isSuccess
    }
}

@Composable
internal fun MikuNavigationIcon(name: String, modifier: Modifier): Boolean {
    val path = "icons/$name.png"
    if (MeloXSettingsRuntime.themeStyle != MeloXThemeStyle.Miku || !hasMikuAsset(path)) return false
    AsyncImage(
        model = "file:///android_asset/miku/$path",
        contentDescription = null, // The navigation control already supplies its label.
        modifier = modifier,
        contentScale = ContentScale.Fit,
    )
    return true
}

/** Fixed, non-interactive background recorded once into the existing glass backdrop. */
@Composable
internal fun MikuPageBackdrop(page: String, modifier: Modifier = Modifier) {
    if (MeloXSettingsRuntime.themeStyle != MeloXThemeStyle.Miku) return
    val path = when (page) {
        "Home", "Explore" -> "artwork/miku-home.jpg"
        "Library" -> "artwork/miku-sky.webp"
        else -> "artwork/miku-bouquet.webp"
    }
    val available = hasMikuAsset(path)
    val dark = isMeloXDarkTheme()
    val base = MaterialTheme.colorScheme.background
    BoxWithConstraints(modifier.background(base)) {
        val compact = maxWidth < 600.dp
        if (available) {
            AsyncImage(
                model = "file:///android_asset/miku/$path",
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop,
                alignment = Alignment.CenterEnd,
            )
            val veil = when (page) {
                "Home", "Explore" -> if (dark) .68f else .54f
                "Library" -> if (compact) { if (dark) .70f else .58f }
                    else { if (dark) .60f else .44f }
                "Settings" -> .53f
                else -> if (compact) { if (dark) .57f else .47f }
                    else { if (dark) .43f else .33f }
            }
            Box(Modifier.matchParentSize().background(base.copy(alpha = veil)))
            Box(Modifier.matchParentSize().background(Brush.horizontalGradient(
                0f to base.copy(alpha = .78f),
                .48f to base.copy(alpha = .30f),
                1f to base.copy(alpha = .18f),
            )))
            Box(Modifier.matchParentSize().background(Brush.verticalGradient(
                0f to base.copy(alpha = .25f),
                .65f to base.copy(alpha = .08f),
                1f to base.copy(alpha = .85f),
            )))
        } else {
            Box(Modifier.matchParentSize().background(Brush.verticalGradient(
                listOf(base, MaterialTheme.colorScheme.primaryContainer.copy(alpha = .35f)),
            )))
        }
    }
}

@Composable
internal fun MikuStateArtwork(state: String, modifier: Modifier = Modifier) {
    if (MeloXSettingsRuntime.themeStyle != MeloXThemeStyle.Miku) return
    val path = "artwork/states/miku-$state.webp"
    if (!hasMikuAsset(path)) return
    AsyncImage(model = "file:///android_asset/miku/$path", contentDescription = null,
        modifier = modifier, contentScale = ContentScale.Fit)
}
