# DrawBox
DrawBox is a multipurpose tool to draw anything on canvas, written completely on Compose Multiplatform.
This is the first multiplatform drawing library!

## Features

- Cross-platform!
- Customisable stoke size and color
- Inbuilt Undo and Redo options
- Reset option
- Easy Implementations

**Next releases:**
- Adding background (color or background image)
- Transparency of the drawing (to see the background image)

**Planned:**
- Different subscriptions (dynamic update/after each drawing)
- Optimizing rendering (convert drawn PATHes)
- Erase tool
- Different image rations

## Usage

```kotlin
val controller = remember { DrawController() }

DrawBox(drawController = controller, modifier = Modifier.fillMaxSize())
```

## License

Licensed under the Apache License, Version 2.0, [click here for the full license](LICENSE.txt).