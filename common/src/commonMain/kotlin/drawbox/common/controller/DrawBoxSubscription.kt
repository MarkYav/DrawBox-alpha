package drawbox.common.controller

sealed interface DrawBoxSubscription {
    object DynamicUpdate : DrawBoxSubscription
    object FinishDrawingUpdate : DrawBoxSubscription
}