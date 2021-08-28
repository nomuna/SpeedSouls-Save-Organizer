package com.speedsouls.organizer.settings;


import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.speedsouls.organizer.data.OrganizerManager;


/**
 * SettingsWindow.
 * <p>
 * Allows the user to configure various settings.
 *
 * @author Kahmul (www.twitch.tv/kahmul78)
 * @date 3 Jun 2016
 */
public class SettingsWindow extends JDialog
{

	private static final long serialVersionUID = -4051069456386856693L;

	private GeneralSettingsPanel generalSettingsPanel;
	private HotkeysSettingsPanel hotkeysSettingsPanel;


	/**
	 * Creates a new SettingsWindow.
	 */
	public SettingsWindow()
	{
		super(null, "Settings", Dialog.ModalityType.APPLICATION_MODAL);

		initLayout();
		initProperties();

		setVisible(true);
	}


	/**
	 * Inits the properties.
	 */
	private void initProperties()
	{
		pack();
		setResizable(false);
		OrganizerManager.getKeyboardHook().unregisterHook();
		setIconImage(OrganizerManager.speedsoulsIcon);
		setLocationRelativeTo(OrganizerManager.getMainWindow());
		setAlwaysOnTop(OrganizerManager.isAlwaysOnTop());
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e)
			{
				super.mouseReleased(e);
				requestFocusInWindow();
			}
		});
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e)
			{
				OrganizerManager.getKeyboardHook().registerHook();
			}
		});
	}


	/**
	 * Inits the layout.
	 */
	private void initLayout()
	{
		JPanel guiPanel = new JPanel();
		guiPanel.setLayout(new BoxLayout(guiPanel, BoxLayout.PAGE_AXIS));
		guiPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		generalSettingsPanel = new GeneralSettingsPanel();
		hotkeysSettingsPanel = new HotkeysSettingsPanel();
		guiPanel.add(generalSettingsPanel);
		guiPanel.add(hotkeysSettingsPanel);
		guiPanel.add(new ButtonsSettingsPanel(this));

		add(guiPanel);
	}


	/**
	 * Saves the changes to the settings.
	 */
	protected void saveSettings()
	{
		generalSettingsPanel.applyChanges();
		hotkeysSettingsPanel.applyChanges();
	}

}
