
/*******************************************************************************
 * Copyright (c) 2019. PKLite
 * @see <a href="https://pklite.xyz>pklite</a>
 *  Redistributions and modifications of this software are permitted as long as this notice remains in its
 *  original unmodified state at the top of this file.  If there are any questions comments, or feedback
 *  about this software, please direct all inquiries directly to the following authors:
 *
 *   PKLite discord: https://discord.gg/Dp3HuFM
 *   Written by PKLite(ST0NEWALL, others) <stonewall@pklite.xyz>, 2019
 *
 ******************************************************************************/

package net.runelite.client.plugins.pvptools;

import com.google.common.base.MoreObjects;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.inject.Inject;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.RuneLiteProperties;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;

@Slf4j
public class PvpToolsPanel extends PluginPanel
{

	private final JLabel loggedLabel = new JLabel();
	public JLabel numCC = new JLabel();
	public JLabel numOther = new JLabel();
	public JLabel numMageJLabel = new JLabel(" ");
	public JLabel numRangeJLabel = new JLabel(" ");
	public JLabel numMeleeJLabel = new JLabel(" ");
	public JLabel totalRiskLabel = new JLabel(" ");
	public JLabel riskProtectingItem = new JLabel(" ");
	public JLabel biggestItemLabel = new JLabel("Protected Item: ");
	public JButton missingPlayers = new JButton("Show missing CC members");
	public JButton currentPlayers = new JButton("Show current CC members");
	@Inject
	private JPanel pvpToolsPanel = new JPanel(new GridLayout(11, 1));
	private JPanel missingPlayersPanel = new JPanel();
	private JPanel currentPlayersPanel = new JPanel();


	public static String htmlLabel(String key, String value)
	{
		return "<html><body style = 'color:#a5a5a5'>" + key + "<span style = 'color:white'>" + value + "</span></body></html>";
	}

	void init()
	{
		setLayout(new BorderLayout());
		setBackground(ColorScheme.DARK_GRAY_COLOR);
		setBorder(new EmptyBorder(10, 10, 10, 10));


		JPanel playersPanel = new JPanel();
		playersPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
		playersPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		playersPanel.setLayout(new GridLayout(0, 1));

		JPanel riskPanel = new JPanel();
		riskPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
		riskPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		riskPanel.setLayout(new GridLayout(0, 1));

		final Font smallFont = FontManager.getRunescapeSmallFont();

		numCC.setText(htmlLabel("Friendly Player Count: ", "0"));
		numOther.setText(htmlLabel("Other Player Count: ", "0"));
		numMageJLabel.setText(htmlLabel("Enemies Praying Mage: ", "0"));
		numMageJLabel.setFont(FontManager.getRunescapeFont());
		numRangeJLabel.setText(htmlLabel("Enemies Praying Range: ", "0"));
		numRangeJLabel.setFont(FontManager.getRunescapeFont());
		numMeleeJLabel.setText(htmlLabel("Enemies Praying Melee: ", "0"));
		numMeleeJLabel.setFont(FontManager.getRunescapeFont());

		totalRiskLabel.setText(htmlLabel("Total risk: ", "0"));
		totalRiskLabel.setFont(FontManager.getRunescapeFont());
		riskProtectingItem.setText(htmlLabel("Risk Protecting Item: ", "0"));
		riskProtectingItem.setFont(FontManager.getRunescapeFont());
		biggestItemLabel.setText("Most Valuable Item: ");


		JLabel revision = new JLabel();
		revision.setFont(smallFont);

		revision.setText("Oldschool revision: ");

		JLabel launcher = new JLabel(htmlLabel("Launcher version: ", MoreObjects
			.firstNonNull(RuneLiteProperties.getLauncherVersion(), "Unknown")));
		launcher.setFont(smallFont);

		loggedLabel.setForeground(ColorScheme.LIGHT_GRAY_COLOR);
		loggedLabel.setFont(smallFont);

		playersPanel.add(numCC);
		playersPanel.add(numOther);
		playersPanel.add(numMageJLabel);
		playersPanel.add(numRangeJLabel);
		playersPanel.add(numMeleeJLabel);

		playersPanel.add(Box.createGlue());
		playersPanel.add(loggedLabel);

		riskPanel.add(totalRiskLabel);
		riskPanel.add(riskProtectingItem);
		riskPanel.add(biggestItemLabel);

		add(playersPanel, BorderLayout.NORTH);
		add(riskPanel, BorderLayout.CENTER);

		currentPlayers.setVisible(false);
		missingPlayers.setVisible(false);
		missingPlayersPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
		missingPlayersPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		missingPlayersPanel.setLayout(new GridLayout(0, 1));
		missingPlayersPanel.add(missingPlayers, BorderLayout.AFTER_LAST_LINE);
		missingPlayersPanel.add(currentPlayers, BorderLayout.AFTER_LAST_LINE);
		add(missingPlayersPanel, BorderLayout.AFTER_LAST_LINE);

	}

	public void disablePlayerCount()
	{
		this.numOther.setText("Disabled");
		this.numCC.setText("Disabled");
		this.numCC.repaint();
		this.numOther.repaint();
	}

	public void disablePrayerCount()
	{
		this.numMageJLabel.setText("disabled");
		this.numRangeJLabel.setText("disabled");
		this.numMeleeJLabel.setText("disabled");
		this.numMageJLabel.repaint();
		this.numRangeJLabel.repaint();
		this.numMeleeJLabel.repaint();
	}

	public void disableRiskCalculator()
	{
		this.totalRiskLabel.setText("disabled");
		this.riskProtectingItem.setText("disabled");
		this.biggestItemLabel.setText("disabled");
		this.totalRiskLabel.repaint();
		this.riskProtectingItem.repaint();
		this.biggestItemLabel.repaint();
	}
}
