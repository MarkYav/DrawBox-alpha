package drawbox.common

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.IntSize
import drawbox.common.model.PathWrapper
import drawbox.common.util.createPath
import drawbox.common.util.pop

/**
 * DrawController interacts with [DrawBox] and it allows you to control the canvas and all the components with it.
 */
class DrawController {
    /** A stateful list of [Path] that is drawn on the [Canvas]. */
    private val drawnPaths = mutableStateListOf<PathWrapper>()

    /** A stateful list of [Path] that was drawn on the [Canvas] but user retracted his action. */
    private val canceledPaths = mutableStateListOf<PathWrapper>()

    /** An [ImageBitmap] to draw paths on the [canvas]. */
    private var _drawnBitmap: ImageBitmap? = null

    /** A [Canvas] to change the [_drawnBitmap] while we are drawing on it. */
    private var canvas: Canvas? = null

    /** A private state of the bitmap to be drawn */
    private val _drawnBitmapState = mutableStateOf<ImageBitmap?>(null)

    /** A public state of the bitmap to be drawn */
    val drawnBitmapState = _drawnBitmapState as State<ImageBitmap?>

    /** An [opacity] of the stroke */
    var opacity by mutableStateOf(1f)

    /** A [strokeWidth] of the stroke */
    var strokeWidth by mutableStateOf(10f)

    /** A [color] of the stroke */
    var color by mutableStateOf(Color.Red)

    /** Indicate how many redos it is possible to do. */
    val undoCount by derivedStateOf { drawnPaths.size }

    /** Indicate how many undos it is possible to do. */
    val redoCount by derivedStateOf { canceledPaths.size }

    /** Executes undo the drawn path if possible. */
    fun undo() {
        if (drawnPaths.isNotEmpty()) {
            canceledPaths.add(drawnPaths.pop())
            invalidateBitmap()
        }
    }

    /** Executes redo the drawn path if possible. */
    fun redo() {
        if (canceledPaths.isNotEmpty()) {
            drawnPaths.add(canceledPaths.pop())
            invalidateBitmap()
        }
    }

    /** Clear drawn paths and the bitmap image. */
    fun reset() {
        drawnPaths.clear()
        canceledPaths.clear()
        invalidateBitmap()
    }

    /** Call this function when user starts drawing a path. */
    internal fun updateLatestPath(newPoint: Offset) {
        drawnPaths.last().points.add(newPoint)
        invalidateBitmap()
    }

    /** When dragging call this function to update the last path. */
    internal fun insertNewPath(newPoint: Offset) {
        val pathWrapper = PathWrapper(
            points = mutableStateListOf(newPoint),
            strokeColor = color,
            alpha = opacity,
            strokeWidth = strokeWidth,
        )
        drawnPaths.add(pathWrapper)
        canceledPaths.clear()
        invalidateBitmap()
    }

    /** Call this function to connect to the [DrawBox]. */
    internal fun connectToDrawBox(size: IntSize) {
        if (size.width > 0 && size.height > 0) {
            _drawnBitmap = ImageBitmap(size.width, size.height, ImageBitmapConfig.Argb8888)
            canvas = Canvas(_drawnBitmap!!)
        }
    }

    /** Updates the [_drawnBitmapState] and [_drawnBitmap] based on the [drawnPaths]. */
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