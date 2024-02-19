package com.torpill.game.component.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import com.torpill.game.Game;
import com.torpill.game.component.Window;
import com.torpill.game.component.buttons.CubeArrowButton;
import com.torpill.game.component.buttons.CubeButton;
import com.torpill.game.component.editors.combos.CubeComboBoxEditor;
import com.torpill.game.component.renderers.combos.CubeComboBoxRenderer;
import com.torpill.game.util.I18n;

@SuppressWarnings({
		"serial", "unchecked"
})
public class LanguagePanel extends CubePanel {

	public LanguagePanel(Game game, Window window) {

		super(game, window);

		Map<Locale, String> availables = I18n.getLanguages();
		this.locales = new HashMap<String, Locale>();

		for (Locale locale : availables.keySet()) {

			this.locales.put(availables.get(locale), locale);
		}

		GridBagLayout gbl = new GridBagLayout();

		this.finish = new CubeButton("finish");
		this.finish.addActionListener(this);

		this.box = new JComboBox<String>();
		this.box.addActionListener(this);
		this.box.setActionCommand("box");

		this.box.setRenderer(new CubeComboBoxRenderer());
		this.box.setEditable(true);
		this.box.setEditor(new CubeComboBoxEditor());
		this.box.setBorder(BorderFactory.createLineBorder(new Color(0x444444)));
		this.box.setUI(new BasicComboBoxUI() {

			@Override
			protected void installComponents() {

				super.installComponents();

				((JComponent) this.popup).setBorder(BorderFactory.createLineBorder(new Color(0x444444)));
			}

			@Override
			protected JButton createArrowButton() {

				CubeArrowButton arrowButton = new CubeArrowButton(BasicArrowButton.SOUTH, new Color(0x444444), null, Color.BLACK, new Color(0x999999), new Color(0xBBBBBB));
				return arrowButton;
			}
		});

		this.setLayout(gbl);

		int x[] = {
				0, 1, 1, 1, 1, 1, 2
		};
		int y[] = {
				0, 0, 1, 2, 3, 4, 0
		};
		int w[] = {
				1, 1, 1, 1, 1, 1, 1
		};
		int h[] = {
				5, 1, 1, 1, 1, 1, 5
		};
		int wx[] = {
				20, 60, 0, 0, 0, 0, 20
		};
		int wy[] = {
				0, 58, 13, 16, 5, 10, 0
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

				for (String language : this.locales.keySet()) {

					this.box.addItem(language);
				}

				this.add(this.box, gbc);

				break;

			case 4:

				this.add(this.finish, gbc);
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
	protected void paintLanguages(Graphics g) {

		super.paintLanguages(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		super.actionPerformed(e);

		switch (e.getActionCommand()) {
		case "finish":

			this.game.discord();
			this.game.options();
			break;

		case "box":

			String selected = (String) this.box.getSelectedItem();
			I18n.setLocale(this.locales.get(selected));
			break;
		}
	}

	private CubeButton finish;
	private JComboBox<String> box;
	private Map<String, Locale> locales;
}
