package lollipop;

import awatch.Anime;
import awatch.Character;
import mread.model.Manga;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class Tools {

    public static Member getEffectiveMember(Guild g, String s) {
        Member m = null;
        try {
            long id = Long.parseLong(s.replaceAll("[<@!>]",""));
            m = g.getMemberById(id);
        } catch(Exception e) {
            try {
                m = g.getMemberByTag(s);
            } catch(Exception e2) {
                try {
                    m = g.getMembersByEffectiveName(s, true).get(0);
                } catch(Exception e3) {
                    try {
                        m = g.getMembersByName(s, true).get(0);
                    } catch(Exception e4) {
                        return null;
                    }
                }
            }
        }
        return m;
    }

    public static void wrongUsage(TextChannel tc, Command c) {
        tc.sendMessageEmbeds(new EmbedBuilder()
                .setTitle("Wrong Command Usage!")
                .setDescription(c.getHelp())
                .setColor(Color.red)
                .build()
        ).queue();
    }

    public static EmbedBuilder mangaToEmbed(Manga m) {
        if(m.summary.length() > 2000) m.summary = m.summary.substring(0, 1000) + "... [Read More!](" + m.url + ")";
        EmbedBuilder msg = new EmbedBuilder()
                .setAuthor(m.title, MangaAPI.apiPath+m.url)
                .setDescription(m.summary.replaceAll("SUMMARY", "").trim())
                .setImage(MangaAPI.apiPath+m.art)
                .addField("Authors",m.author,true)
                .addField("Chapters",m.chapter,true)
                .addField("Rating", m.rating, true)
                .addField("Status",m.status,true)
                .addField("Tags", String.join(", ", m.tags), false);

        return msg;
    }

    public static EmbedBuilder animeToEmbed(Anime a) {
        if(a==null) {
            EmbedBuilder msg = new EmbedBuilder()
                    .setColor(Color.red)
                    .setDescription("Could not find an anime with that search query! Please try again with a valid anime!");
            return msg;
        }
        EmbedBuilder msg = new EmbedBuilder()
                .setAuthor("ID: " + a.malID, a.url)
                .setDescription(a.summary + " [Read More!](" + a.url + ")")
                .setTitle(a.title)
                .addField("Rating", a.rating, true)
                .addField("Score", Integer.toString(a.score), true)
                .addField("Status", a.status, true)
                .addField("Epsiode Count", Integer.toString(a.episodeCount), true)
                .setImage(a.art);
        return msg;
    }

    public static EmbedBuilder characterToEmbed(Character c) {
        if(c==null) {
            EmbedBuilder msg = new EmbedBuilder()
                    .setColor(Color.red)
                    .setDescription("Could not find a character with that search query! Please try again with a valid character!");
            return msg;
        }
        EmbedBuilder msg = new EmbedBuilder()
                .setAuthor("ID: " + c.malID, c.url)
                .setTitle(c.name)
                .addField("Alternative Names", c.alternativeNames, false)
                .addField("Latest Anime", c.anime, false)
                .addField("Latest Manga", c.manga, false)
                .setImage(c.art);
        return msg;
    }

    public static EmbedBuilder pictureEmbed(String url) {
        if(url == null) {
            EmbedBuilder error = new EmbedBuilder()
                    .setColor(Color.red)
                    .setDescription("Could not find any pictures related to that ID! Check for any typos.");
            return error;
        }
        EmbedBuilder msg = new EmbedBuilder()
                .setImage(url);
        return msg;
    }

}
