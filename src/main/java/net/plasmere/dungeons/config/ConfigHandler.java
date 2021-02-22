package net.plasmere.dungeons.config;

import net.plasmere.dungeons.Dungeons;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;

public class ConfigHandler {
    private FileConfiguration configuration = new YamlConfiguration();
    private FileConfiguration messaguration = new YamlConfiguration();

    private Configuration conf;
    private Configuration oConf;
    private Configuration mess;
    private Configuration oMess;
    private String configVer = "";
    private String messagesVer = "";

    private final Dungeons inst = Dungeons.getInstance();
    private final File cfile = new File(inst.getDataFolder(), "config.yml");
    private final File mfile = new File(inst.getDataFolder(), "messages.yml");

    public ConfigHandler(){
        if (! this.inst.getDataFolder().exists()) {
            if (this.inst.getDataFolder().mkdir()) {
                this.inst.getLogger().info("Made folder: " + this.inst.getDataFolder().getName());
            }
        }

        this.configVer = this.inst.getDescription().getVersion();
        this.messagesVer = this.inst.getDescription().getVersion();

        this.conf = this.loadConf();
        this.mess = this.loadMess();
    }

    public Configuration getConf() { return this.conf; }
    public Configuration getMess() { return this.mess; }
    public Configuration getoConf() { return this.oConf; }
    public Configuration getoMess() { return this.oMess; }

    public void reloadConfig(){
        try {
            this.configuration.load(cfile);

            this.conf = this.configuration;

            ConfigUtils.updateConfig(this.conf);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void reloadMessages(){
        try {
            this.messaguration.load(mfile);

            this.mess = this.messaguration;

            MessageConfUtils.updateMess(this.mess);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Configuration loadConf(){
        if (! this.cfile.exists()){
            try	(InputStream in = this.inst.getResource("config.yml")){
                Files.copy(Objects.requireNonNull(in), this.cfile.toPath());
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        try {
            this.configuration.load(cfile);

            this.conf = this.configuration;

            if (! this.configVer.equals(ConfigUtils.version)){
                this.conf = this.iterateConfigs("oldconfig.yml");

                this.inst.getLogger().severe("----------------------------------------------------------");
                this.inst.getLogger().severe("YOU NEED TO UPDATE THE VALUES IN YOUR NEW CONFIG FILE AS");
                this.inst.getLogger().severe("YOUR OLD ONE WAS OUTDATED. I IMPORTED THE NEW ONE FOR YOU.");
                this.inst.getLogger().severe("----------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.inst.getLogger().info("Loaded configuration!");

        return this.conf;
    }

    public Configuration loadMess(){
        if (! this.mfile.exists()){
            try	(InputStream in = this.inst.getResource("messages.yml")){
                Files.copy(Objects.requireNonNull(in), this.mfile.toPath());
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        try {
            this.messaguration.load(mfile);

            this.mess = this.messaguration;

            if (! this.messagesVer.equals(MessageConfUtils.version)){
                this.mess = this.iterateMessagesConf("oldmessages.yml");

                this.inst.getLogger().severe("----------------------------------------------------------");
                this.inst.getLogger().severe("YOU NEED TO UPDATE THE VALUES IN YOUR NEW MESSAGES FILE AS");
                this.inst.getLogger().severe("YOUR OLD ONE WAS OUTDATED. I IMPORTED THE NEW ONE FOR YOU.");
                this.inst.getLogger().severe("----------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.inst.getLogger().info("Loaded messages!");

        return this.mess;
    }

    private Configuration iterateConfigs(String old) {
        File oldfile = new File(this.inst.getDataFolder(), old);
        if (oldfile.exists()) {
            this.iterateConfigs("new" + old);
        } else {
            try (InputStream in = inst.getResource("config.yml")) {
                Files.move(this.cfile.toPath(), oldfile.toPath());
                Files.copy(Objects.requireNonNull(in), this.cfile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.oConf = this.conf;

            try {
                this.configuration.load(cfile);
            } catch (Exception e){
                e.printStackTrace();
            }

            this.conf = this.configuration;
        }

        return this.conf;
    }

    private Configuration iterateMessagesConf(String old) {
        File oldfile = new File(this.inst.getDataFolder(), old);
        if (oldfile.exists()) {
            this.iterateMessagesConf("new" + old);
        } else {
            try (InputStream in = this.inst.getResource("messages.yml")) {
                Files.move(this.mfile.toPath(), oldfile.toPath());
                Files.copy(Objects.requireNonNull(in), this.mfile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.oMess = this.mess;
            try {
                this.messaguration.load(this.mfile);
            } catch (Exception e){
                e.printStackTrace();
            }

            this.mess = this.messaguration;
        }

        return this.mess;
    }
}
