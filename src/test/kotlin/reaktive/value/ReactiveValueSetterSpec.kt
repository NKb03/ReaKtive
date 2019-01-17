package reaktive.value

import com.natpryce.hamkrest.absent
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.should.shouldMatch
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import org.nikok.kref.weak
import reaktive.help.`true`
import reaktive.help.shouldBe
import reaktive.value.help.value

internal object ReactiveValueSetterSpec: Spek({
    var rv = reactiveVariable(23)
    given("the setter of a reactive variable") {
        val s = rv.setter
        on("setting to 34") {
            s.set(34)
            it("should set the reactive variable to 34") {
                rv shouldMatch value(equalTo(34))
            }
        }
        val bindTo = reactiveValue(45)
        on("binding it to a reactive value") {
            val obs = s.bind(bindTo)
            it("should bind to the reactive value") {
                rv.isBound shouldBe `true`
                rv shouldMatch value(equalTo(45))
                obs.kill()
            }
        }
        on("letting the reactive variable go out of scope") {
            val old by weak(rv)
            rv = reactiveVariable(10000)
            it("finalize it on garbage collection") {
                System.gc()
                old shouldBe absent()
            }
        }
    }
})