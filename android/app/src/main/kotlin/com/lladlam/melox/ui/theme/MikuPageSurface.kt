package com.lladlam.melox.ui.theme

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import coil3.compose.rememberAsyncImagePainter
import com.lladlam.melox.ui.settings.MeloXSettingsRuntime
import com.lladlam.melox.ui.settings.MeloXThemeStyle

/** Decorate existing opaque page surfaces without changing layout or touch handling. */
internal fun Modifier.mikuPageSurface(page: String): Modifier = composed {
    val base = MaterialTheme.colorScheme.background
    if (MeloXSettingsRuntime.themeStyle != MeloXThemeStyle.Miku) {
        background(base)
    } else {
        val file = if (page == "library" || page == "collection") "miku-sky.webp" else "miku-bouquet.webp"
        val painter = rememberAsyncImagePainter("file:///android_asset/miku/artwork/$file")
        val dark = isMeloXDarkTheme()
        background(base)
            .paint(painter, sizeToIntrinsics = false, contentScale = ContentScale.Crop)
            .drawWithContent {
                drawRect(base.copy(alpha = if (dark) .76f else .68f))
                drawRect(Brush.horizontalGradient(listOf(base.copy(alpha = .55f), base.copy(alpha = .12f))))
                drawContent()
            }
    }
}
