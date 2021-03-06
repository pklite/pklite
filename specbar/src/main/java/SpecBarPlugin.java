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


import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.WidgetHiddenChanged;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import static net.runelite.api.widgets.WidgetInfo.COMBAT_SPECIAL_ATTACK;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;


@PluginDescriptor(
	name = "Spec Bar",
	description = "Adds a spec bar to every weapon",
	tags = {"spec bar", "special attack", "spec", "bar", "pklite"},
	enabledByDefault = false
)
public class SpecBarPlugin extends Plugin
{

	@Inject
	private Client client;

	@Override
	protected void startUp()
	{
	}

	@Override
	protected void shutDown()
	{
	}

	@Subscribe
	private void onWidgetHiddenChanged(WidgetHiddenChanged event)
	{
		if (WidgetInfo.TO_GROUP(event.getWidget().getId()) ==  WidgetID.COMBAT_GROUP_ID)
		{
			hideSpecBar();
		}
	}

	@Subscribe
	private void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState().equals(GameState.LOGGING_IN))
		{
			hideSpecBar();
		}

	}

	/**
	 * Displays the special attack bar
	 */
	private void hideSpecBar()
	{
		try
		{
			Widget specBar = client.getWidget(COMBAT_SPECIAL_ATTACK);
			specBar.setHidden(false);
			specBar.revalidate();
		}
		catch (NullPointerException e)
		{
			// Stops the nulls that occur before the spec bar is loaded by player equipping a spec wep
		}
	}
}