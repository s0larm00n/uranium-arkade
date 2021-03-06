package pl.karol202.uranium.arkade.htmlcanvas.dom.values

import kotlinx.wasm.jsinterop.*
import pl.karol202.uranium.arkade.htmlcanvas.dom.NativeValue

@SymbolName("uranium_NativeGradient_addColorStop")
private external fun NativeGradient_addColorStop(arena: Arena, index: Object,
                                                 offset: Double, colorPtr: Pointer, colorLength: Int)

internal actual class NativeGradient(override val arena: Arena,
                                     override val index: Object) : NativeValue
{
	actual val asNativeFillStyle get() = NativeFillStyle(arena, index)

	actual fun addColorStop(offset: Double, color: String) =
			NativeGradient_addColorStop(arena, index, offset, stringPointer(color), stringLengthBytes(color))
}
