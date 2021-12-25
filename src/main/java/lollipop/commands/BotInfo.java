package lollipop.commands;

import lollipop.CONSTANT;
import lollipop.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class BotInfo implements Command {
    @Override
    public String[] getAliases() {
        return new String[] {"botinfo"};
    }

    @Override
    public String getCategory() {
        return "Miscellaneous";
    }

    @Override
    public String getHelp() {
        return "Displays information about the bot!\nUsage: `" + CONSTANT.PREFIX + getAliases()[0] + "`";
    }

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        event.getChannel().sendMessageEmbeds(new EmbedBuilder()
                .setTitle("Bot Information")
                .setDescription("lollipop is an anime/manga discord bot which provides with fun commands!\n" +
                        "> [Bot Invite Link](https://discord.com/api/oauth2/authorize?client_id=919061572649910292&permissions=414467877952&scope=bot)\n" +
                        "> [Github Repository](https://github.com/BooleanCube/lollipop-bot)\n")
                .addField("Support", "Did you encounter a bug? Join this [server](https://discord.gg/3ZDpPyR) and report in a support channel.", false)
                .addField("Author", "**BooleanCube** (" + event.getJDA().getShardManager().getGuildById(740316079523627128l).getOwner().getUser().getAsTag() + ")\n[MyAnimeList](https://myanimelist.net/profile/BooleanCube) - [Playlist](https://open.spotify.com/playlist/4KnWT1hszQuBi4IaKdm8Pk?si=91e0fe7e73b54853) - [Discord](https://discord.gg/3ZDpPyR) - [Github](https://github.com/BooleanCube) - [Youtube](https://www.youtube.com/channel/UCsivrachJyFVLi7V60lrd6g)", false)
                .setFooter("konnichiwa, watashi wa loli desu")
                .setAuthor("lollipop", "https://discord.gg/3ZDpPyR", "https://camo.githubusercontent.com/173ac0fe37e2c35233c6270aebdc23bf48e7e151f6cf685f0e71d18f01be1808/68747470733a2f2f692e696d6775722e636f6d2f4346366f674e4c2e6a706567")
                .setThumbnail("https://media.discordapp.net/attachments/919069979377287188/921538899141091438/lloli.jpg?width=230&height=230")
                .build()
        ).queue();
    }
}
