package com.example.shedrename;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
    name = "GIM Shed",
    description = "Renames the Group Storage interface title",
    tags = {"group", "ironman", "storage", "shed", "rename", "gim"}
)
public class ShedRenamePlugin extends Plugin
{
    private static final String ORIGINAL_TITLE = "Group Storage";

    @Inject private Client client;
    @Inject private ClientThread clientThread;
    @Inject private ShedRenameConfig config;

    @Provides
    ShedRenameConfig provideConfig(ConfigManager cm)
    {
        return cm.getConfig(ShedRenameConfig.class);
    }

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded e)
    {
        if (e.getGroupId() == InterfaceID.SHARED_BANK)
        {
            clientThread.invokeLater(this::applyRename);
        }
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged e)
    {
        if (ShedRenameConfig.GROUP.equals(e.getGroup()))
        {
            clientThread.invokeLater(this::applyRename);
        }
    }

    private void applyRename()
    {
        Widget root = client.getWidget(InterfaceID.SHARED_BANK, 0);
        if (root == null)
        {
            return;
        }
        renameRecursive(root, config.newName());
    }

    private void renameRecursive(Widget w, String newName)
    {
        if (w == null)
        {
            return;
        }

        String text = w.getText();
        if (text != null && text.contains(ORIGINAL_TITLE))
        {
            w.setText(text.replace(ORIGINAL_TITLE, newName));
        }

        Widget[] children = w.getDynamicChildren();
        if (children != null)
        {
            for (Widget c : children) renameRecursive(c, newName);
        }
        children = w.getStaticChildren();
        if (children != null)
        {
            for (Widget c : children) renameRecursive(c, newName);
        }
        children = w.getNestedChildren();
        if (children != null)
        {
            for (Widget c : children) renameRecursive(c, newName);
        }
    }
}
