package com.torpill.game.component.panels;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import com.torpill.game.Game;
import com.torpill.game.component.Window;
import com.torpill.game.component.buttons.CubeButton;

@SuppressWarnings("serial")
public class OptionsPanel extends CubePanel {

	public OptionsPanel(Game game, Window window) {

		super(game, window);

		GridBagLayout gbl = new GridBagLayout();

		this.back = new CubeButton("back");
		this.controls = new CubeButton("controls");
		this.language = new CubeButton("language");

		this.back.addActionListener(this);
		this.controls.addActionListener(this);
		this.language.addActionListener(this);

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

				this.add(this.controls, gbc);
				break;

			case 4:

				this.add(this.language, gbc);
				break;

			case 6:

				this.add(this.back, gbc);
				break;

			default:

				this.add(new TransparentPanel(), gbc);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
	}

	@Override
	protected void paintOptions(Graphics g) {

		super.paintMain(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		super.actionPerformed(e);

		switch (e.getActionCommand()) {
		case "back":

			this.game.main();
			break;

		case "controls":
			break;

		case "language":
			
			this.game.languages();
			break;
		}
	}

	private CubeButton back;
	private CubeButton controls;
	private CubeButton language;
}
