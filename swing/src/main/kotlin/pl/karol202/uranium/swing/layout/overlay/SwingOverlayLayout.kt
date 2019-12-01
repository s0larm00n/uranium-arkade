package pl.karol202.uranium.swing.layout.overlay

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.swing.layout.LayoutData
import pl.karol202.uranium.swing.layout.SwingLayout
import pl.karol202.uranium.swing.layout.layout
import pl.karol202.uranium.swing.layout.layoutData
import pl.karol202.uranium.swing.native.SwingNativeComponent
import pl.karol202.uranium.swing.util.*
import java.awt.Container
import java.awt.LayoutManager
import javax.swing.OverlayLayout

class SwingOverlayLayout(props: Props) : SwingAbstractComponent<SwingOverlayLayout.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 override val layoutProps: SwingLayout.Props = SwingLayout.Props()) :
			UProps,
			SwingNativeComponent.PropsProvider<Props>,
			SwingLayout.PropsProvider<Props>,
			PropsProvider<Props>
	{
		override val swingProps = layoutProps.swingProps
		override val overlayLayoutProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(layoutProps = layoutProps.withSwingProps(builder))

		override fun withLayoutProps(builder: Builder<SwingLayout.Props>) = copy(layoutProps = layoutProps.builder())

		override fun withOverlayLayoutProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val overlayLayoutProps: Props

		fun withOverlayLayoutProps(builder: Builder<Props>): S
	}

	data class Data(private val props: Props) : LayoutData<OverlayLayout>
	{
		override fun createLayout(container: Container) = OverlayLayout(container)

		override fun updateLayout(container: Container, layout: LayoutManager) =
				layout as? OverlayLayout ?: createLayout(container)
	}

	override fun SwingRenderBuilder.render()
	{
		+ layout(props = props.layoutProps).layoutData(Data(props))
	}
}

fun SwingRenderScope.overlayLayout(key: Any = AutoKey,
                                   block: SwingRenderBuilder.() -> Unit) =
		component(::SwingOverlayLayout, SwingOverlayLayout.Props(key)).content(block)

internal fun SwingRenderScope.overlayLayout(props: SwingOverlayLayout.Props) = component(::SwingOverlayLayout, props)

private typealias SOLProvider<P> = SwingOverlayLayout.PropsProvider<P>
fun <P : SOLProvider<P>> SwingElement<P>.withOverlayLayoutProps(builder: Builder<SwingOverlayLayout.Props>) =
		withProps { withOverlayLayoutProps(builder) }
fun <P : SOLProvider<P>> SwingElement<P>.content(block: SwingRenderBuilder.() -> Unit) =
		withOverlayLayoutProps { withSwingProps { copy(children = block.render()) } }
