package pl.karol202.uranium.arkade.htmlcanvas.dom.values

import kotlinx.wasm.jsinterop.*
import pl.karol202.uranium.arkade.htmlcanvas.dom.NativeValue
import pl.karol202.uranium.arkade.htmlcanvas.dom.retrieveObject

@SymbolName("uranium_NativeFillStyle_Companion_fromString")
private external fun NativeFillStyle_Companion_fromString(strPtr: Pointer, strLength: Int, resultArena: Arena): Int

internal actual class NativeFillStyle(override val arena: Arena,
                                      override val index: Object) : NativeValue
{
	actual companion object
	{
		actual fun fromString(string: String) = retrieveObject(::NativeFillStyle) {
			NativeFillStyle_Companion_fromString(stringPointer(string), stringLengthBytes(string), resultArena)
		}
	}
}
