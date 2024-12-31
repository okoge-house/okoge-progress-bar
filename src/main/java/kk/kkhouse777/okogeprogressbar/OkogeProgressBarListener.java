package kk.kkhouse777.okogeprogressbar;

import com.intellij.util.ui.JBUI;
import kk.kkhouse777.okogeprogressbar.okoge.OkogeIconStream;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class OkogeProgressBarListener extends BasicProgressBarUI {

    private OkogeIconStream okogeIconStream;

    public OkogeProgressBarListener() {
        this.okogeIconStream = new OkogeIconStream();
    }

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
    public static ComponentUI createUI(JComponent c) {
        return new OkogeProgressBarListener();
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(super.getPreferredSize(c).width, JBUI.scale(20));
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        progressBar.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
            }
        });
    }

    @Override
    protected void paintIndeterminate(Graphics g2d, JComponent c) {
        if (!(g2d instanceof Graphics2D)) return;
        Graphics2D graphics2D = (Graphics2D)g2d;
        // TODO
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        // TODO
    }

}
