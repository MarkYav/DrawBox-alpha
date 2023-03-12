package drawbox.common

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.IntSize
import drawbox.common.model.PathWrapper
import drawbox.common.util.createPath
import drawbox.common.util.pop

class DrawController {
    private val drawnPaths = mutableStateListOf<PathWrapper>()
    private val canceledPaths = mutableStateListOf<PathWrapper>()

    private var _drawnBitmap: ImageBitmap? = null
    private var canvas: Canvas? = null

    private val _drawnBitmapState = mutableStateOf<ImageBitmap?>(null)
    val drawnBitmapState = _drawnBitmapState as State<ImageBitmap?>

    var opacity by mutableStateOf(1f)
    var strokeWidth by mutableStateOf(10f)
    var color by mutableStateOf(Color.Red)

    val undoCount by derivedStateOf { drawnPaths.size }
    val redoCount by derivedStateOf { canceledPaths.size }

    fun undo() {
        if (drawnPaths.isNotEmpty()) {
            canceledPaths.add(drawnPaths.pop())
            invalidateBitmap()
        }
    }

    fun redo() {
        if (canceledPaths.isNotEmpty()) {
            drawnPaths.add(canceledPaths.pop())
            invalidateBitmap()
        }
    }

    fun reset() {
        drawnPaths.clear()
        canceledPaths.clear()
        invalidateBitmap()
    }

    internal fun updateLatestPath(newPoint: Offset) {
        drawnPaths.last().points.add(newPoint)
        invalidateBitmap()
    }

    internal fun insertNewPath(newPoint: Offset) {
        val pathWrapper = PathWrapper(
            points = mutableStateListOf(newPoint),
            strokeColor = color,
            alpha = opacity,
            strokeWidth = strokeWidth,
        )
        drawnPaths.add(pathWrapper)
        canceledPaths.clear()
    }

    internal fun connectToDrawBox(size: IntSize) {
        if (size.width > 0 && size.height > 0) {
            _drawnBitmap = ImageBitmap(size.width, size.height, ImageBitmapConfig.Argb8888)
            canvas = Canvas(_drawnBitmap!!)
        }
    }

    private fun invalidateBitmap() {
        drawnPaths.forEach { pw ->
            canvas?.drawPath(
                createPath(pw.points),
                paint = Paint().apply {
                    color = pw.strokeColor
                    alpha = pw.alpha
                    style = PaintingStyle.Stroke
                }
            )
        }
        _drawnBitmapState.value = _drawnBitmap
    }
}