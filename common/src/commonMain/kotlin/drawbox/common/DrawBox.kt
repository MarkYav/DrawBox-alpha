package drawbox.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged

@Composable
fun DrawBox(
    controller: DrawController,
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    val bitmap by remember { controller.drawnBitmapState }

    Canvas(modifier = modifier
        .onSizeChanged { newSize -> controller.connectToDrawBox(newSize) }
        .pointerInput(Unit) {
            detectTapGestures(
                onTap = { offset -> controller.insertNewPath(offset) }
            )
        }
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = { offset -> controller.insertNewPath(offset) },
                onDrag = { change, _ -> controller.updateLatestPath(change.position) }
            )
        }
    ) {
        bitmap?.let { bitmap ->
            drawImage(bitmap)
        }
    }
}