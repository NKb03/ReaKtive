package org.nikok.reaktive.event

import org.nikok.reaktive.event.impl.EventHandlerManager
import org.nikok.reaktive.value.ReactiveValue
import org.nikok.reaktive.value.binding.binding

/**
 * Skeletal implementation for [EventStream]
 * @constructor
 * @param description the description of this [EventStream]
*/
abstract class AbstractEventStream<T>(override val description: String): EventStream<T> {
    private val handlerManager by lazy { EventHandlerManager(this) }

    override fun subscribe(handler: EventHandler<T>): Subscription {
        return handlerManager.subscribe(handler)
    }

    override val lastFired: ReactiveValue<T?> = lastFired()

    /**
     * Emit the specified [value] from this event stream
    */
    protected fun doEmit(value: T) {
        handlerManager.fire(value)
    }

    private fun lastFired(): ReactiveValue<T?> {
        return binding<T?>("Last fired of $this", null) {
            val subscription = subscribe("Last fired of $this updater") { fired ->
                set(fired)
            }
            addObserver(subscription.asObserver())
        }
    }
}
