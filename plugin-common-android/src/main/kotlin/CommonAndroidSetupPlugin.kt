package com.github.liminal.kliche.plugin.android.common

import com.android.build.gradle.BaseExtension
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project

import org.gradle.kotlin.dsl.*


open class CommonAndroidSetupExtension {
    internal var baseAndroidConfig: Action<BaseExtension>? = null

    fun android(conf: Action<BaseExtension>) {
        baseAndroidConfig = conf
    }

}

class CommonAndroidSetupPlugin : Plugin<Project> {


    override fun apply(project: Project): Unit = project.run {

        val extension = extensions.create<CommonAndroidSetupExtension>("commonAndroidSetup")

        allprojects {
            pluginManager.withPlugin("com.android.application") {
                extension.baseAndroidConfig?.let { setupFn ->
                    configure<com.android.build.gradle.BaseExtension> {
                        setupFn.execute(this)
                    }
                }
            }
            pluginManager.withPlugin("com.android.library") {
                extension.baseAndroidConfig?.let { setupFn ->
                    configure<com.android.build.gradle.BaseExtension> {
                        setupFn.execute(this)
                    }
                }
            }
        }
    }

}
