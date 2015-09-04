package core;

import component.VComponentGlobals;
import gui.ConnectionScreenController;
import misc.Logger;
import net.sf.image4j.codec.ico.ICODecoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class Driver {
    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        frame.setTitle(Globals.getLocalizedString("programName"));
        frame.setBackground(VComponentGlobals.BACKGROUND_COLOR);

        try {
            frame.setIconImage(ICODecoder.read(ClassLoader.getSystemResourceAsStream("Art Assets/Icons/Frame_Icon.ico")).get(0));
        } catch (final IOException e) {
            Logger.writeLog(Globals.getLocalizedString("DC_error_UnableToLoadIcon"), Logger.LOG_TYPE_ERROR);
        }

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                final Dimension currentDimensions = frame.getSize();
                final Dimension minimumDimensions = frame.getMinimumSize();

                if(currentDimensions.width < minimumDimensions.width) {
                    currentDimensions.width = minimumDimensions.width;
                }

                if(currentDimensions.height < minimumDimensions.height) {
                    currentDimensions.height = minimumDimensions.height;
                }

                frame.setSize(currentDimensions);
            }
        });

        frame.add(new ConnectionScreenController(frame).getView());
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
