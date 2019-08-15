package com.torpill.game.component;

import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.torpill.game.Game;
import com.torpill.game.util.KeyboardManager;

@SuppressWarnings("serial")
public class Window extends JFrame implements Runnable, ComponentListener {

	public Window(Game game) {

		super();

		this.tk = Toolkit.getDefaultToolkit();
		this.width = (int) (this.tk.getScreenSize().getWidth() * 80 / 100);
		this.height = this.width * 60 / 100;

		this.game = game;

		this.setTitle("Game");
		this.setSize(this.width, this.height);
		try {

			this.setIconImage(ImageIO.read(Window.class.getResource("/textures/icons/icon.png")));

		} catch (IOException e) {

			e.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.addKeyListener(new KeyboardManager());
		this.addComponentListener(this);

		this.width = this.getGlassPane().getWidth();
		this.height = this.getGlassPane().getHeight();
		this.unit = this.width / this.maxunits;

		this.pan = new Panel(this.game, this);
		this.add(this.pan);
		this.setContentPane(this.pan);
	}

	@Override
	public void run() {

		this.setVisible(true);

		while (this.game.isAlive()) {

			long now = System.nanoTime();
			int frame = 0;

			while (now + 1000000000 > System.nanoTime()) {

				long nano = System.nanoTime();
				long delay = 1000000000 / this.fps;

				while (nano + delay > System.nanoTime());

				this.repaint();
				frame++;
			}

			this.realfps = frame;
			System.out.println(this.realfps + " FPS");
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {

		this.width = this.getGlassPane().getWidth();
		this.height = this.getGlassPane().getHeight();
		this.unit = this.width / this.maxunits;
	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}

	public long getFPS() {

		return this.realfps;
	}

	public int getUnit() {

		return this.unit;
	}

	public int getGlassPaneWidth() {

		return this.width;
	}

	public int getGlassPaneHeigth() {

		return this.height;
	}

	public void setOffset(int x, int y) {

		this.pan.setOffset(x, y);
	}

	private Toolkit tk;
	private Panel pan;
	private int width, height;
	private final int maxunits = 100;
	private int unit;
	private Game game;
	private long fps = 60, realfps;
}
