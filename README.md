# DrawBox
DrawBox is a multipurpose tool to draw anything on canvas, written completely on Compose Multiplatform.
This is the first multiplatform drawing library!

## Features

- Cross-platform!
- Customisable stoke size, color and opacity
- Inbuilt Undo and Redo options
- Reset option
- Background with color/image
- Custom opacity of drawing/background
- Easy Implementations

**Next releases:**
- Different subscriptions (dynamic update/after each drawing)
- Optimizing rendering (convert drawn PATHes)

**Planned:**
- Different image rations
- Migrate from Compose dependencies in DrawController

## Usage

```kotlin
val controller = remember { DrawController() }

DrawBox(drawController = controller, modifier = Modifier.fillMaxSize())
```

## License

Licensed under the Apache License, Version 2.0, [click here for the full license](LICENSE.txt).