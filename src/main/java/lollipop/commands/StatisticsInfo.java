package lollipop.commands;

import lollipop.CONSTANT;
import lollipop.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

import javax.annotation.processing.SupportedSourceVersion;
import java.lang.management.RuntimeMXBean;
import java.text.NumberFormat;
import java.util.List;

public class StatisticsInfo implements Command {

    public Runtime runtime = Runtime.getRuntime();

    @Override
    public String[] getAliases() {
        return new String[] {"statinfo"};
    }

    @Override
    public String getCategory() {
        return "Owner";
    }

    @Override
    public String getHelp() {
        return "Shows you the statistics and resource usage of the bot!\nUsage: `" + CONSTANT.PREFIX + getAliases()[0] + "`";
    }

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(event.getAuthor().getIdLong() != CONSTANT.OWNERID) return;

        EmbedBuilder msg = new EmbedBuilder()
                .setTitle("Lollipop Statistics")
                .addField("System", osInfo(), true)
                .addField("Memory", memInfo(), false)
                .addField("CPU", cpuInfo(), true)
                .addField("Uptime", uptimeInfo(), false);
        event.getChannel().sendMessageEmbeds(msg.build()).queue();
    }

    public String memInfo() {
        NumberFormat format = NumberFormat.getInstance();
        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        sb.append("Free memory: `").append(format.format(freeMemory / 1024)).append("`\nAllocated memory: `")
        .append(format.format(allocatedMemory / 1024)).append("`\nMax memory: `").append(format.format(maxMemory / 1024))
        .append("`\nTotal free memory: `").append(format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024)).append("`");
        return sb.toString();
    }
    public String osInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("OS: `").append(System.getProperty("os.name")).append("`\nVersion: `").append(System.getProperty("os.version"))
        .append("`\nArchitecture: `").append(System.getProperty("os.arch")).append("`");
        return sb.toString();
    }
    public String cpuInfo() {
        OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        int availableProcessors = operatingSystemMXBean.getAvailableProcessors();
        long processCpuTime = operatingSystemMXBean.getProcessCpuTime();
        double cpuProcLoad = operatingSystemMXBean.getProcessCpuLoad();
        double cpuSysLoad = operatingSystemMXBean.getSystemCpuLoad();
        double systemLoad = operatingSystemMXBean.getSystemLoadAverage();
        StringBuilder sb = new StringBuilder();
        sb.append("CPU Process Time: `").append(processCpuTime).append("`\nProcess CPU Load: `").append(cpuProcLoad)
        .append("`\nSystem Load Average: `").append(systemLoad).append("`\nSystem CPU Load: `").append(cpuSysLoad).append("`\n")
        .append("Available processors (cores): `").append(runtime.availableProcessors()).append("`");
        return sb.toString();
    }
    public String uptimeInfo() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long uptime = runtimeMXBean.getUptime();
        long uptimeInSeconds = uptime / 1000;
        long numberOfHours = uptimeInSeconds / (60 * 60);
        long numberOfMinutes = (uptimeInSeconds / 60) - (numberOfHours * 60);
        long numberOfSeconds = uptimeInSeconds % 60;
        StringBuilder sb = new StringBuilder();
        sb.append(numberOfHours).append(" hour(s), ").append(numberOfMinutes).append(" minute(s), ").append(numberOfSeconds).append(" second(s)");
        return sb.toString();
    }

}