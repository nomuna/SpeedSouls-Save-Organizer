package com.speedsouls.organizer.profileconfig;


import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.speedsouls.organizer.games.Game;


/**
 * Profile part of the configuration panel.
 * <p>
 * Contains the profile list as well as the buttons to edit it.
 *
 * @author Kahmul (www.twitch.tv/kahmul78)
 * @date 28 Sep 2015
 */
public class GameConfigProfilesPanel extends JPanel
{

	private static final long serialVersionUID = -1327027925194860493L;


	/**
	 * Creates a new profile panel.
	 * 
	 * @param game the game of this panel
	 */
	protected GameConfigProfilesPanel(Game game)
	{
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel profileLabel = new JLabel("Profiles: ");

		ProfileList profileList = createProfileList(game);
		JScrollPane listPane = new JScrollPane(profileList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listPane.setMinimumSize(new Dimension(100, 130));

		JPanel optionsPanel = createOptionsPanel(profileList);

		// Horizontal
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup().addComponent(profileLabel).addComponent(listPane));
		hGroup.addGroup(layout.createParallelGroup().addComponent(optionsPanel));

		layout.setHorizontalGroup(hGroup);

		// Vertical
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(profileLabel));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(listPane).addComponent(optionsPanel));

		layout.setVerticalGroup(vGroup);

		setLayout(layout);
	}


	/**
	 * Creates the profile list.
	 * 
	 * @return the profile list
	 */
	private ProfileList createProfileList(Game game)
	{
		ProfileList profileList = new ProfileList(game);
		return profileList;
	}


	/**
	 * Creates the button panel.
	 * 
	 * @return the panel containing the buttons
	 */
	private JPanel createOptionsPanel(ProfileList profileList)
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 1));
		buttonPanel.setMaximumSize(new Dimension(68, 50));

		JButton newButton = createNewButton(profileList);
		JButton importButton = createImportButton(profileList);
		JButton editButton = createEditButton(profileList);
		JButton deleteButton = createDeleteButton(profileList);

		buttonPanel.add(newButton);
		buttonPanel.add(editButton);
		buttonPanel.add(importButton);
		buttonPanel.add(deleteButton);

		return buttonPanel;
	}


	/**
	 * Creates the 'Import' button.
	 * 
	 * @return the import button
	 */
	private JButton createImportButton(ProfileList profileList)
	{
		JButton importButton = new JButton("Import");
		importButton.setToolTipText("Import profile(s)");
		importButton.addActionListener(event -> {
			profileList.askToImportProfiles();
		});

		return importButton;
	}


	/**
	 * Creates the 'New' button.
	 * 
	 * @param game the game of the current panel
	 * @return the new button
	 */
	private JButton createNewButton(ProfileList profileList)
	{
		JButton newButton = new JButton("New");
		newButton.setToolTipText("Create a new profile");
		newButton.addActionListener(event -> {
			profileList.askToCreateProfile();
		});
		return newButton;
	}


	/**
	 * Creates the 'Edit' button.
	 * 
	 * @param game the game of the current panel
	 * @return the edit button
	 */
	private JButton createEditButton(ProfileList profileList)
	{
		JButton editButton = new JButton("Edit");
		editButton.setToolTipText("Edit the selected profile");
		editButton.addActionListener(event -> {
			profileList.askToEditProfile(profileList.getSelectedValue());
		});
		return editButton;
	}


	/**
	 * Creates the 'Delete' button.
	 * 
	 * @param game the game of the current panel
	 * @return the delete button
	 */
	private JButton createDeleteButton(ProfileList profileList)
	{
		JButton deleteButton = new JButton("Delete");
		deleteButton.setToolTipText("Delete the selected profile");
		deleteButton.addActionListener(event -> {
			profileList.askToDeleteProfiles(profileList.getSelectedValuesList());
		});
		return deleteButton;
	}

}
