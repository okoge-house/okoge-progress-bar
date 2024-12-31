package kk.kkhouse777.okogeprogressbar;

import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import kk.kkhouse777.okogeprogressbar.okoge.PaintIndeterminateOkogeState;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

public class OkogeProgressBarUI extends BasicProgressBarUI {

    private final PaintIndeterminateOkogeState paintIndeterminateOkogeState;

    public OkogeProgressBarUI() {
        this.paintIndeterminateOkogeState = new PaintIndeterminateOkogeState();
    }

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
    public static ComponentUI createUI(JComponent c) {
        return new OkogeProgressBarUI();
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(super.getPreferredSize(c).width, JBUI.scale(20));
    }

    @Override
    protected int getBoxLength(int availableLength, int otherDimension) {
        return availableLength;
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
        if (!(g2d instanceof Graphics2D graphics2D)) return;
        int width = c.getWidth();
        int height = c.getPreferredSize().height;
        if (!isEven(c.getHeight() - height)) height++;
        final GraphicsConfig config = GraphicsUtil.setupAAPainting(graphics2D);

        // background color
        graphics2D.setColor(new Color(0x94CB50));
        float arc = 8 * (Toolkit.getDefaultToolkit().getScreenResolution() / 96f);
        graphics2D.fill(new RoundRectangle2D.Float(0, 0, width, height, arc, arc));

        // okoge icon
        paintIndeterminateOkogeState.updateOffset(width - JBUI.scale(15));
        paintIndeterminateOkogeState.currentOkogeIcon().paintIcon(
                progressBar,
                graphics2D,
                paintIndeterminateOkogeState.getOffset() - JBUI.scale(10),
                -JBUI.scale(6)
        );

        config.restore();
    }

    private boolean isEven(int i) {
        return i % 2 == 0;
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        // TODO
        super.paintDeterminate(g,c);
    }

}
