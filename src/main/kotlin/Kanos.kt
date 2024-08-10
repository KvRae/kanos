import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction


class Kanos: Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("kanos", KanosTask::class.java)
    }
}

abstract class KanosTask : DefaultTask() {
    @Input
    var snapFingers: Boolean = false

    @Input
    var snapHands: Boolean = false

    @TaskAction
    fun performTask() {
        when {
            snapFingers -> handleFiles(deleteCondition = true)
            snapHands -> handleFiles(deleteCondition = false)
            else -> showHelp()
        }
    }

    private fun showHelp() {
        project.logger.quiet("Kanos is a simple plugin that deletes files in the project directory.")
        project.logger.quiet("Usage: gradle kanos --snapFingers  : Delete half of the files in the project directory.")
        project.logger.quiet("Usage: gradle kanos --snapHands  : Delete all files in the project directory.")
    }

    private fun handleFiles(deleteCondition: Boolean) {
        project.logger.quiet("Kanos is here")
        project.fileTree(project.projectDir).visit { fileVisitDetails ->
            val shouldDelete = if (deleteCondition) {
                fileVisitDetails.file.length() % 2 == 0L
            } else {
                true
            }
            if (shouldDelete) {
                fileVisitDetails.file.delete()
                project.logger.quiet("Deleted file: ${fileVisitDetails.file}")
            }
        }
    }
}