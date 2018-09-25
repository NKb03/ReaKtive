/**
 *@author Nikolaus Knop
 */

package org.nikok.reaktive.set.base

import org.nikok.reaktive.collection.ReactiveCollection
import org.nikok.reaktive.collection.base.AbstractReactiveCollection
import org.nikok.reaktive.set.ReactiveSet
import org.nikok.reaktive.set.SetChange
import org.nikok.reaktive.value.ReactiveValue

abstract class AbstractReactiveSet<E> : ReactiveSet<E>, AbstractReactiveCollection<E, SetChange<E>>() {
    override fun <F> map(newName: String, f: (E) -> F): ReactiveSet<F> {
        TODO("not implemented")
    }

    override fun filter(newName: String, predicate: (E) -> Boolean): ReactiveSet<E> {
        TODO("not implemented")
    }

    override fun <F> flatMap(newName: String, f: (E) -> ReactiveCollection<F, *>): ReactiveSet<F> {
        TODO("not implemented")
    }

    override fun minus(other: Collection<@UnsafeVariance E>): ReactiveSet<@UnsafeVariance E> {
        TODO("not implemented")
    }

    override fun minus(other: ReactiveCollection<@UnsafeVariance E, *>): ReactiveSet<@UnsafeVariance E> {
        TODO("not implemented")
    }

    override fun plus(other: Collection<@UnsafeVariance E>): ReactiveSet<@UnsafeVariance E> {
        TODO("not implemented")
    }

    override fun plus(other: ReactiveCollection<@UnsafeVariance E, *>): ReactiveSet<@UnsafeVariance E> {
        TODO("not implemented")
    }

    override fun <T> fold(name: String, initial: T, op: (T, @UnsafeVariance E) -> T): ReactiveValue<T> {
        TODO("not implemented")
    }

    protected fun fireAdded(element: E) {
        fireChange(SetChange.Added(element, this))
    }

    protected fun fireRemoved(element: E) {
        fireChange(SetChange.Removed(element, this))
    }
}