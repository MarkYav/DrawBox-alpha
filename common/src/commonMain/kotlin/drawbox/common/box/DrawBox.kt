package drawbox.common.box

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import drawbox.common.controller.DrawBoxBackground
import drawbox.common.controller.DrawController
import drawbox.common.model.PathWrapper

@Composable
fun DrawBox(
    controller: DrawController,
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    val path: List<PathWrapper>? = controller.pathToDrawOnCanvas
    val background: DrawBoxBackground = controller.background
    val canvasAlpha: Float = controller.canvasOpacity

    Box(modifier = modifier) {
        DrawBoxBackground(
            background = background,
            modifier = Modifier.fillMaxSize(),
        )
        DrawBoxCanvas(
            path = path ?: emptyList(),
            onSizeChanged = controller::connectToDrawBox,
            onTap = controller::insertNewPath,
            onDragStart = controller::insertNewPath,
            onDrag = controller::updateLatestPath,
            alpha = canvasAlpha,
            modifier = Modifier.fillMaxSize(),
        )
    }
}