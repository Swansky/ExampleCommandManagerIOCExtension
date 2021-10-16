package commands;

import fr.swansky.discordCommandIOC.Commands.annotations.Command;
import fr.swansky.discordCommandIOC.Commands.annotations.CommandsContainer;
import manager.SomethingManager;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

@CommandsContainer
public class SomethingsCommands {


    @Command(name = "addSomething", description = "add something to manager")
    public void addSomething(SomethingManager somethingManager, TextChannel textChannel, String[] args) {
        if (args.length > 0) {
            String arg = args[0];
            somethingManager.addSomething(arg);
            textChannel.sendMessage("Something have been add !").queue();
        } else {
            textChannel.sendMessage("pls specify something").queue();
        }
    }

    @Command(name = "showSomething", description = "show all something")
    public void showSomething(SomethingManager somethingManager, TextChannel textChannel, User user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("All something: \n");
        for (String something : somethingManager.getSomethings()) {
            stringBuilder.append(something).append('\n');
        }
        textChannel.sendMessage(stringBuilder.toString()).queue();
        textChannel.sendMessage(user.getAsMention()).queue();
    }
}
