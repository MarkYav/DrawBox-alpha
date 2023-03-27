package drawbox.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import drawbox.common.util.createPath

@Composable
fun DrawBox(
    controller: DrawController,
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    val path = controller.pathToDrawOnCanvas

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
        .clipToBounds()
    ) {
        path?.forEach { pw ->
            drawPath(
                createPath(pw.points),
                color = pw.strokeColor,
                alpha = pw.alpha,
                style = Stroke(
                    width = pw.strokeWidth,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }
    }
}