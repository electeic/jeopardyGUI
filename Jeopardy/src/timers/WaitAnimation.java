package timers;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WaitAnimation extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// found this code on
	// http://www.java2s.com/Code/Java/2D-Graphics-GUI/Makeyourownanimationfromaseriesofimages.htm
	ImageIcon images[];
	Image image1[];
	int totalImages = 8, currentImage = 0, animationDelay = 100;
	Timer animationTimer;
	public JLabel myLabel;

	public WaitAnimation() {
		setSize(50, 50);
		myLabel = new JLabel();
		setLayout(new BorderLayout());
		images = new ImageIcon[totalImages];
		for (int i = 0; i < images.length; ++i) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("images/waitingAnimation/frame_" + i + "_delay-0.1s.jpg"));
			    images[i] = new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		startAnimation();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (images[currentImage].getImageLoadStatus() == MediaTracker.COMPLETE) {
			images[currentImage].paintIcon(this, g, 0, 0);
//			currentImage = (currentImage + 1) % totalImages;
		}
	}

	public void actionPerformed(ActionEvent e) {
		this.removeAll();
		myLabel.setIcon(images[currentImage]);
		this.add(myLabel);
		currentImage = (currentImage + 1) % totalImages;
		repaint();
		revalidate();
	}

	public void startAnimation() {
		if (animationTimer == null) {
			currentImage = 0;
			animationTimer = new Timer(animationDelay, this);
			animationTimer.start();
		} else if (!animationTimer.isRunning())
			animationTimer.restart();
	}

	public void stopAnimation() {
		animationTimer.stop();
	}
	
//	public static void main(String[] args) {
//		ClockSpin anim = new ClockSpin();
//	    JFrame app = new JFrame("Animator test");
//	    app.add(anim, BorderLayout.CENTER);
//	    app.setSize(300,300);
//	    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    app.setSize(anim.getPreferredSize().width + 10, anim.getPreferredSize().height + 30);
//	    app.setVisible(true);
//	}
}