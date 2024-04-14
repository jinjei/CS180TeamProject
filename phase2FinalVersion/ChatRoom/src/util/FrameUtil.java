package util;

import javax.swing.*;
import java.awt.*;

/**
 * Team Project(Project 05) -- FrameUtil
 * <p>
 * This class makes each GUI frame to be created
 * in the center of the screen by default
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */
public class FrameUtil {
    //center
    public static JFrame center(JFrame jFrame) {

        // 1.Get the width and height of the frame
        int widthFrame = jFrame.getWidth();
        int heightFrame = jFrame.getHeight();
        // 2.Get the width and height of the screen
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
        double widthScreen = screenSize.getWidth();
        double heightScreen = screenSize.getHeight();
        // 3.If the frame's size exceeds the screen,
        // then use the screen's size
        if (widthFrame > widthScreen) {
            widthFrame = (int) widthScreen;
        }
        if (heightFrame > heightScreen) {
            heightFrame = (int) heightScreen;
        }
        // 4.Set position
        int positionX = (int) ((widthScreen - widthFrame) / 2);
        int positionY = (int) ((heightScreen - heightFrame) / 2);

        jFrame.setSize(new Dimension(widthFrame, heightFrame));
        jFrame.setLocation(new Point(positionX, positionY));

        return jFrame;
    }
}
