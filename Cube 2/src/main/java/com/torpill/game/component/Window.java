package com.torpill.game.component;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.torpill.game.Game;
import com.torpill.game.component.panels.LanguagePanel;
import com.torpill.game.component.panels.MainPanel;
import com.torpill.game.component.panels.OptionsPanel;
import com.torpill.game.component.panels.PlayPanel;
import com.torpill.game.util.KeyboardManager;

@SuppressWarnings("serial")
public class Window extends JFrame implements Runnable, ComponentListener, WindowListener {

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
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(820, 540));
		this.addKeyListener(new KeyboardManager());
		this.addWindowListener(this);
		this.addComponentListener(this);

		this.width = this.getGlassPane().getWidth();
		this.height = this.getGlassPane().getHeight();
		this.unit = this.width / this.maxunits;

		this.mainpan = new MainPanel(this.game, this);
		this.playpan = new PlayPanel(this.game, this);
		this.optionspan = new OptionsPanel(this.game, this);
		this.languagespan = new LanguagePanel(this.game, this);
	}

	@Override
	public void run() {

		this.setVisible(true);

		while (this.game.isAlive() && this.isVisible()) {

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

		this.setVisible(false);
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

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

		this.game.kill();
	}

	@Override
	public void windowClosed(WindowEvent e) {

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

		this.playpan.setOffset(x, y);
	}

	public void updatePanel() {

		switch (this.game.state) {
		case INIT:
			break;

		case MAIN:

			this.remove(this.optionspan);
			this.add(this.mainpan);
			this.revalidate();
			break;

		case WORLDS:
			break;

		case LEVELS:

			this.remove(this.mainpan);
			this.revalidate();
			this.requestFocus();
			break;

		case OPTIONS:

			this.remove(this.mainpan);
			this.remove(this.languagespan);
			this.add(this.optionspan);
			this.revalidate();
			break;

		case CONTROLS:
			break;

		case LANGUAGES:
			
			this.remove(this.optionspan);
			this.add(this.languagespan);
			this.revalidate();
			break;

		case PLAY:

			this.add(this.playpan);
			this.revalidate();
			break;

		case DEATH:

			this.remove(this.playpan);
			this.revalidate();
			break;
		}
	}

	private Toolkit tk;
	private MainPanel mainpan;
	private PlayPanel playpan;
	private OptionsPanel optionspan;
	private LanguagePanel languagespan;
	private int width, height;
	private final int maxunits = 100;
	private int unit;
	private Game game;
	private long fps = 60, realfps;
}
