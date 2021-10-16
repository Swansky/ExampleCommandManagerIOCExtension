import fr.swansky.discordCommandIOC.Commands.EventHandler;
import fr.swansky.discordCommandIOC.config.DiscordCommandIOCConfig;
import fr.swansky.ioccontainer.SwansIOC;
import fr.swansky.swansAPI.exception.InstanceCreationException;
import listeners.CommandListener;
import manager.SomethingManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import javax.security.auth.login.LoginException;

import static constants.Constants.TOKEN;

public class Program {

    public Program() throws LoginException, InstanceCreationException {
        DiscordCommandIOCConfig discordCommandIOCConfig = new DiscordCommandIOCConfig();
        discordCommandIOCConfig.addObjectToAutoInjects(new SomethingManager());
        discordCommandIOCConfig.setPreCommandEvent(new EventHandler() {
            @Override
            public boolean execute(User user, String s, Message message) {
                message.getTextChannel().sendMessage(user.getName() + " tries to make this command: " + s).queue();
                return true;
            }
        });
        SwansIOC swansIOC = SwansIOC.CreateIOC(Program.class);
        swansIOC.getConfigExtensionManager().addConfigExtension(discordCommandIOCConfig);
        swansIOC.CreateIOC();
        JDA jda = JDABuilder.createDefault(TOKEN).addEventListeners(new CommandListener()).build();
    }

    public static void main(String[] args) {
        try {
            new Program();
        } catch (LoginException | InstanceCreationException e) {
            e.printStackTrace();
        }
    }
}
