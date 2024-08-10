import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task


class Kanos: Plugin<Project> {
    override fun apply(target: Project) {
        // Register the tasks
        target.tasks.register("kanos --snap-fingers") {
            it.doLast { task ->
                handleFiles(task, target, deleteCondition = true)
            }
        }

        target.tasks.register("kanos --snap-hands") {
            it.doLast { task ->
                handleFiles(task, target, deleteCondition = false)
            }
        }
    }

    /**
     * Handles files in the project directory based on the provided conditions.
     *
     * @param task The task that is being executed.
     * @param target The target project.
     * @param deleteCondition Determines whether to use the delete condition.
     */
    private fun handleFiles(task: Task, target: Project, deleteCondition: Boolean) {
        task.project.logger.quiet("Kanos is here")
        target.fileTree(target.projectDir).visit { fileVisitDetails ->
            val dirSize = fileVisitDetails.size
            if (dirSize > 0) {
                val shouldDelete = if (deleteCondition) {
                    (dirSize % 2).toInt() == 0
                } else {
                    true
                }
                if (shouldDelete) {
                    fileVisitDetails.file.delete()
                    target.logger.quiet("Deleted file: ${fileVisitDetails.file}")
                }
            }
        }
    }
}