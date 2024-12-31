package kk.kkhouse777.okogeprogressbar.listeners

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.openapi.application.ApplicationActivationListener
import com.intellij.openapi.wm.IdeFrame
import kk.kkhouse777.okogeprogressbar.OkogeProgressBarUI
import javax.swing.UIManager

class OkogeProgressListener :
    LafManagerListener,
    ApplicationActivationListener {
    override fun applicationActivated(ideFrame: IdeFrame) {
        updateProgressBar()
    }

    override fun lookAndFeelChanged(p0: LafManager) {
        updateProgressBar()
    }

    companion object {
        private const val PROGRESS_BAR_UI_KEY = "ProgressBarUI"
        private const val PROGRESS_BAR_UI_CLASS_NAME = "kk.kkhouse777.okogeprogressbar.ui.OkogeProgressBarUI"

        private fun updateProgressBar() {
            UIManager.put(PROGRESS_BAR_UI_KEY, PROGRESS_BAR_UI_CLASS_NAME)
            UIManager.getDefaults().put(PROGRESS_BAR_UI_CLASS_NAME, OkogeProgressBarUI::class.java)
        }
    }
}
