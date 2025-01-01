package kk.kkhouse777.okogeprogressbar;

import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import kk.kkhouse777.okogeprogressbar.okoge.OkogeProgressBarState;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

public class OkogeProgressBarUI extends BasicProgressBarUI {

    private static final Color PROGRESS_INDICATOR_COLOR = new Color(0x94CB50);
    private final OkogeProgressBarState okogeProgressBarState;

    public OkogeProgressBarUI() {
        this.okogeProgressBarState = new OkogeProgressBarState();
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
        graphics2D.setColor(PROGRESS_INDICATOR_COLOR);
        float arc = 8 * (Toolkit.getDefaultToolkit().getScreenResolution() / 96f);
        graphics2D.fill(new RoundRectangle2D.Float(0, 0, width, height, arc, arc));

        // okoge icon
        okogeProgressBarState.updateOffset(width - JBUI.scale(15));
        okogeProgressBarState.currentOkogeIcon().paintIcon(
                progressBar,
                graphics2D,
                okogeProgressBarState.getOffset() - JBUI.scale(10),
                -JBUI.scale(6)
        );

        config.restore();
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        if (!(g instanceof Graphics2D graphics2D)) return;
        if (progressBar.getOrientation() != SwingConstants.HORIZONTAL || !c.getComponentOrientation().isLeftToRight()) {
            super.paintDeterminate(g, c);
            return;
        }
        final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);
        Insets b = progressBar.getInsets(); // area for border
        int w = progressBar.getWidth();
        int h = progressBar.getPreferredSize().height;
        if (!isEven(c.getHeight() - h)) h++;
        int barRectWidth = w - (b.right + b.left);
        int barRectHeight = h - (b.top + b.bottom);
        if (barRectWidth <= 0 || barRectHeight <= 0) return;

        int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

        final float R2 = JBUI.scale(9f);
        final float off = JBUI.scale(1f);
        graphics2D.setColor(progressBar.getForeground());
        graphics2D.fill(new RoundRectangle2D.Float(0, 0, w - off, h - off, R2, R2));

        graphics2D.setColor(PROGRESS_INDICATOR_COLOR);
        graphics2D.fill(new RoundRectangle2D.Float(2f*off,2f*off, amountFull - JBUI.scale(5f), h - JBUI.scale(5f), JBUI.scale(7f), JBUI.scale(7f)));
        okogeProgressBarState.getCurrentOkogeForwardIcon().paintIcon(
                progressBar,
                graphics2D,
                amountFull - JBUI.scale(10),
                -JBUI.scale(6)
        );

        config.restore();
    }

    private boolean isEven(int i) {
        return i % 2 == 0;
    }

}
