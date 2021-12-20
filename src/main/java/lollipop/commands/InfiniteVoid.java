package lollipop.commands;

import lollipop.CONSTANT;
import lollipop.Command;
import lollipop.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class InfiniteVoid implements Command {
    @Override
    public String[] getAliases() {
        return new String[] {"infinitevoid"};
    }

    @Override
    public String getCategory() {
        return "Roleplay";
    }

    @Override
    public String getHelp() {
        return "Domain Expansion: Infinite Void!\nUsage: `" + CONSTANT.PREFIX + getAliases()[0] + " [user]`";
    }

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(args.isEmpty()) { Tools.wrongUsage(event.getTextChannel(), this); return; }
        String[] gifs = {"https://tenor.com/view/infinite-void-gojo-satoru-gojo-jjk-jujutsu-kaisen-gif-19219956", "https://tenor.com/view/satoru-gojo-domain-expansion-infinite-void-unlimited-void-gif-20411471", "https://tenor.com/view/gojo-blindfold-eyes-gojo-eyes-jujutsu-kaisen-gif-19192192", "https://tenor.com/view/sawunn-gif-21249141", "https://tenor.com/view/jjk-gojo-gif-23192148", "https://tenor.com/view/jujustu-kaisen-satoru-gojo-infinite-void-strongest-domain-expansion-gif-19191941", "https://tenor.com/view/anime-gif-19444090"};
        Member target = Tools.getEffectiveMember(event.getGuild(), String.join(" ", args));
        if(target == null) {
            event.getChannel().sendMessageEmbeds(new EmbedBuilder().setDescription("Could not find the specified member!").setColor(Color.red).build()).queue();
            return;
        }
        event.getChannel().sendMessage("**Domain Expansion: Infinite Void**\n" + target.getAsMention() + " is stuck in the infinite void casted by " + event.getMember().getAsMention()).queue();
        event.getChannel().sendMessage(gifs[(int)(Math.random()*gifs.length)]).queue();
    }
}