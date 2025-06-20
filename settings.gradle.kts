pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "My Application"
include(":app")
include(":core")
include(":feature")
include(":core:ui")
include(":core:network")
include(":core:datastore")
include(":core:auth")
include(":core:analytics")
include(":feature:login")
include(":feature:dashboard")
include(":feature:userdetail")
include(":feature:splash")
include(":feature:mylibrary")
include(":feature:onboarding")
include(":core:navigation")
