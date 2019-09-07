package pl.karol202.uranium.swing

import javax.swing.JFrame

abstract class UraniumSwingFrame
{
	companion object
	{
		fun withRoot(rootSupplier: SwingBuilder.() -> SwingElement<*>) = UraniumSwingFrameBuilder(rootSupplier)
	}

	protected abstract val SwingBuilder.rootElement: SwingElement<*>

	private val frame = JFrame()

	fun show()
	{
		render()
		frame.initFrame()
	}

	private fun render() = SwingRenderer().renderRoot(createRootElement(), createContext())

	private fun createRootElement() = SwingBuilder().rootElement

	private fun createContext() = SwingContextImpl(frame)

	protected abstract fun JFrame.initFrame()
}
