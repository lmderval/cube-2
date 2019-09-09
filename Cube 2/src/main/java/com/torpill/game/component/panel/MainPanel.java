package com.torpill.game.component.panel;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import com.torpill.game.Game;
import com.torpill.game.component.Window;
import com.torpill.game.component.buttons.CubeButton;

@SuppressWarnings("serial")
public class MainPanel extends CubePanel {

	public MainPanel(Game game, Window window) {

		super(game, window);

		GridBagLayout gbl = new GridBagLayout();

		this.play = new CubeButton("play");
		this.options = new CubeButton("options");
		this.quit = new CubeButton("quit");

		this.play.addActionListener(this);
		this.options.addActionListener(this);
		this.quit.addActionListener(this);

		this.setLayout(gbl);

		int x[] = {
				0, 1, 1, 1, 1, 1, 1, 1, 2
		};
		int y[] = {
				0, 0, 1, 2, 3, 4, 5, 6, 0
		};
		int w[] = {
				1, 1, 1, 1, 1, 1, 1, 1, 1
		};
		int h[] = {
				7, 1, 1, 1, 1, 1, 1, 1, 7
		};
		int wx[] = {
				30, 40, 0, 0, 0, 0, 0, 0, 30
		};
		int wy[] = {
				0, 68, 5, 1, 5, 6, 5, 10, 0
		};

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		for (int i = 0; i < x.length; i++) {

			gbc.gridx = x[i];
			gbc.gridy = y[i];
			gbc.gridwidth = w[i];
			gbc.gridheight = h[i];
			gbc.weightx = wx[i];
			gbc.weighty = wy[i];

			switch (i) {
			case 2:

				this.add(this.play, gbc);
				break;

			case 4:

				this.add(this.options, gbc);
				break;

			case 6:

				this.add(this.quit, gbc);
				break;

			default:

				this.add(new TransparentPanel(), gbc);
			}
		}

// gbc.gridx = 0;
// gbc.gridy = 0;
// gbc.gridwidth = 1;
// gbc.gridheight = 7;
// gbc.weightx = 30;
// gbc.weighty = 100;
// gbc.fill = GridBagConstraints.BOTH;
// this.add(new TransparentPanel(), gbc);
// gbc.gridx = 1;
// gbc.gridy = 0;
// gbc.gridwidth = 1;
// gbc.gridheight = 1;
// gbc.weightx = 40;
// gbc.weighty = 69;
// this.add(new TransparentPanel(), gbc);
// gbc.gridy = 1;
// gbc.weighty = 5;
// this.add(this.play, gbc);
// gbc.gridy = 2;
// gbc.weighty = 1;
// this.add(new TransparentPanel(), gbc);
// gbc.gridy = 3;
// gbc.weighty = 5;
// this.add(this.options, gbc);
// gbc.gridy = 4;
// gbc.weighty = 1;
// this.add(new TransparentPanel(), gbc);
// gbc.gridy = 5;
// gbc.weighty = 5;
// this.add(this.quit, gbc);
// gbc.gridy = 6;
// gbc.gridwidth = 1;
// gbc.gridheight = 1;
// gbc.weighty = 14;
// this.add(new TransparentPanel(), gbc);
// gbc.gridx = 2;
// gbc.gridy = 0;
// gbc.gridwidth = 1;
// gbc.gridheight = 7;
// gbc.weightx = 30;
// gbc.weighty = 100;
// this.add(new TransparentPanel(), gbc);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
	}

	@Override
	protected void paintMain(Graphics g) {

		super.paintMain(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		super.actionPerformed(e);

		switch (e.getActionCommand()) {
		case "play":

			this.game.worlds();
			break;

		case "options":

			this.game.options();
			break;

		case "quit":

			this.game.kill();
			break;
		}
	}

	private CubeButton play;
	private CubeButton options;
	private CubeButton quit;
}
