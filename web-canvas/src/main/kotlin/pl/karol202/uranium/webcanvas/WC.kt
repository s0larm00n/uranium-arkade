package pl.karol202.uranium.webcanvas

import pl.karol202.uranium.core.component.UAbstractAppComponent
import pl.karol202.uranium.core.component.UAbstractNativeComponent
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.manager.RenderManager
import pl.karol202.uranium.core.render.URenderScope

object WC

typealias WCElement<P> = UElement<WC, P>

typealias WCAbstractAppComponent<P> = UAbstractAppComponent<WC, P>

typealias WCAbstractNativeComponent<P> = UAbstractNativeComponent<WC, P>

typealias WCRenderScope = URenderScope<WC>

typealias WCRenderManager<P> = RenderManager<WC, P>