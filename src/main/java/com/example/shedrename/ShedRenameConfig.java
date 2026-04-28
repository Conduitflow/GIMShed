package com.example.shedrename;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(ShedRenameConfig.GROUP)
public interface ShedRenameConfig extends Config
{
    String GROUP = "shedrename";

    @ConfigItem(
        keyName = "newName",
        name = "Replacement title",
        description = "What to rename 'Group Storage' to"
    )
    default String newName()
    {
        return "The Shed";
    }
}
