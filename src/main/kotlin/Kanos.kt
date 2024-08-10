import org.gradle.api.Plugin
import org.gradle.api.Project


class Kanos: Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("kanos") {
            it.doLast {
                println("Hello, Kanos!")
            }
        }
    }
}