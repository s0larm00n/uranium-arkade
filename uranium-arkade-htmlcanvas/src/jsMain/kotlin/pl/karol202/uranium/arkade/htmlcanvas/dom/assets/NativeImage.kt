package pl.karol202.uranium.arkade.htmlcanvas.dom.assets

import org.w3c.dom.Image

internal actual class NativeImage(val image: Image)
{
	actual companion object
	{
		actual fun load(src: String) = NativeImage(Image().apply { this.src = src })
	}
}
