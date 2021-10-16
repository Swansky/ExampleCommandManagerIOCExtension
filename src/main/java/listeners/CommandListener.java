package listeners;

import fr.swansky.discordCommandIOC.DiscordCommandIOC;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static constants.Constants.TAG;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Message message = event.getMessage();

        if (message.getContentDisplay().startsWith(TAG)) {
            if (!DiscordCommandIOC.getCommandManager().commandUser(message.getAuthor(), message.getContentDisplay().replaceFirst(TAG, ""), message)) {
                event.getMessage().getTextChannel().sendMessage("Error!").queue();
            }
        }

    }
}
