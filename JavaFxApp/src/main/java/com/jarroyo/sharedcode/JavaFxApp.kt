package com.jarroyo.sharedcode

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

fun launchApp(primaryStage: Stage, classLoader: ClassLoader) {
    val root = FXMLLoader.load<Parent>(classLoader.getResource("app.fxml")!!)
    primaryStage.title = "Your Weather"
    primaryStage.scene = Scene(root, 1280.0, 720.0)
    primaryStage.show()
}
