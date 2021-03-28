package net.plasmere.dungeons.utils.managers.stats;

import net.plasmere.dungeons.Dungeons;
import org.bukkit.entity.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Stat {
    private final String filePrePath = StatsManager.folder;
    private TreeMap<String, String> info = new TreeMap<>();
    private File file;

    public Player player;
    public UUID uuid;
    public int slaying_exp;
    public int forging_exp;
    public int slaying_lvl;
    public int forging_lvl;
    public int play_seconds;
    public String latest_name;
    public boolean online;

    public Stat(String name){
        this(name, true);
    }

    public Stat(String name, boolean create){
        this.online = onlineCheck(name);
        try {
            this.player = Dungeons.getInstance().getServer().getPlayer(name);
        } catch (Exception e) {
            this.player = null;
        }
        this.uuid = StatsManager.getCachedUUID(name);
        if (this.player != null) this.latest_name = name;

        construct(uuid, create);
    }

    private void construct(UUID uuid, boolean createNew){
        this.file = new File(filePrePath + uuid.toString() + ".properties");

        if (createNew || file.exists()) {

            //StreamLine.getInstance().getLogger().info("Player file: " + file.getName() + " (In the \"players\" folder.)");

            try {
                getFromConfigFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public TreeMap<String, String> getInfo() {
        return info;
    }

    public void remKey(String key){
        info.remove(key);
    }
    public String getFromKey(String key){
        return info.get(key);
    }
    public void updateKey(String key, Object value) {
        info.put(key, String.valueOf(value));
        loadVars();
    }
    public File getFile() { return file; }

    public boolean hasProperty(String property) {
        for (String info : getInfoAsPropertyList()) {
            if (info.startsWith(property)) return true;
        }

        return false;
    }

    public TreeSet<String> getInfoAsPropertyList() {
        TreeSet<String> infoList = new TreeSet<>();
        for (String key : info.keySet()){
            infoList.add(key + "=" + getFromKey(key));
        }

        return infoList;
    }

    public String getFullProperty(String key) throws Exception {
        if (hasProperty(key)) {
            return key + "=" + getFromKey(key);
        } else {
            throw new Exception("No property saved!");
        }
    }

    public void flushInfo(){
        this.info = new TreeMap<>();
    }

    public void addKeyValuePair(String key, String value){
        info.put(key, value);
    }

    public String stringifyList(List<String> list, String splitter){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= list.size(); i++) {
            if (i < list.size()) {
                stringBuilder.append(list.get(i - 1)).append(splitter);
            } else {
                stringBuilder.append(list.get(i - 1));
            }
        }

        return stringBuilder.toString();
    }

    public void getFromConfigFile() throws IOException {
        flushInfo();

        if (file.exists()){
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                while (data.startsWith("#")) {
                    data = reader.nextLine();
                }
                String[] dataSplit = data.split("=", 2);
                addKeyValuePair(dataSplit[0], dataSplit[1]);
            }

            reader.close();

            if (needUpdate()) {
                updateWithNewDefaults();
            }

            loadVars();
        } else {
            updateWithNewDefaults();
        }
    }

    public boolean needUpdate() {
        if (info.size() != propertiesDefaults().size()) return true;

        int i = 0;
        for (String p : getInfoAsPropertyList()) {
            if (! p.startsWith(propertiesDefaults().toArray(new String[0])[i].split("=", 2)[0])) return true;
            i++;
        }

        return false;
    }

    public void updateWithNewDefaults() throws IOException {
        file.delete();
        FileWriter writer = new FileWriter(file);

        for (String p : propertiesDefaults()) {
            String[] propSplit = p.split("=", 2);

            String property = propSplit[0];

            String write = "";
            try {
                write = getFullProperty(property);
            } catch (Exception e) {
                write = p;
            }

            writer.write(write + "\n");
        }

        writer.close();

        flushInfo();

        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            while (data.startsWith("#")) {
                data = reader.nextLine();
            }
            String[] dataSplit = data.split("=", 2);
            addKeyValuePair(dataSplit[0], dataSplit[1]);
        }

        reader.close();

        loadVars();
    }

    public TreeSet<String> propertiesDefaults() {
        TreeSet<String> defaults = new TreeSet<>();
        defaults.add("uuid=" + uuid);
        defaults.add("slaying_exp=0");
        defaults.add("forging_exp=0");
        defaults.add("slaying_lvl=0");
        defaults.add("forging_lvl=0");
        defaults.add("latest_name=" + latest_name);
        defaults.add("play_seconds=0");
        //defaults.add("");
        return defaults;
    }

    public void loadVars() {
        this.uuid = UUID.fromString(getFromKey("uuid"));
        this.slaying_exp = Integer.parseInt(getFromKey("slaying_exp"));
        this.forging_exp = Integer.parseInt(getFromKey("forging_exp"));
        this.slaying_lvl = Integer.parseInt(getFromKey("slaying_lvl"));
        this.forging_lvl = Integer.parseInt(getFromKey("forging_lvl"));
        this.latest_name = getFromKey("latest_name");
        this.play_seconds = Integer.parseInt(getFromKey("play_seconds"));
    }

    public void addPlaySeconds(int seconds){
        setPlaySeconds(play_seconds + seconds);
    }

    public void setPlaySeconds(int seconds) {
        updateKey("play_seconds", seconds);
    }

    public float getPlayHours(){
        return play_seconds / 3600f;
    }

    public int getLevelFromExp(int exp){
        if (exp >= 500) {
            return ((exp - 500) / 500) + 1;
        } else {
            return 0;
        }
    }

    public void addExpToSlaying(Float amount){
        int a = amount.intValue();
        if (a < 1) a = 1;
        setExpSlaying(slaying_exp + a);
    }

    public void setExpSlaying(int amount){
        updateKey("slaying_exp", amount);
        updateLevelSlaying();
    }

    public void updateLevelSlaying(){
        updateKey("slaying_lvl", getLevelFromExp(slaying_exp));
    }

    public void addExpToForging(Float amount){
        int a = amount.intValue();
        if (a < 1) a = 1;
        setExpForging(slaying_exp + a);
    }

    public void setExpForging(int amount){
        updateKey("forging_exp", amount);
        updateLevelForging();
    }

    public void updateLevelForging(){
        updateKey("forging_lvl", getLevelFromExp(forging_exp));
    }

    public enum Bonuses {
        SLAYING,
        FORGING
    }

    public int getStatBonus(Bonuses bonus){
        switch (bonus){
            case SLAYING:
                return (slaying_lvl * 10) - (slaying_lvl * 2);
            case FORGING:
                return (forging_lvl * 10) - (forging_lvl * 2);
            default:
                return 0;
        }
    }

    public boolean onlineCheck(String name){
        return Dungeons.getInstance().getServer().getPlayer(name) != null;
    }

    public void saveInfo() throws IOException {
        file.delete();

        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for (String s : getInfoAsPropertyList()){
            writer.write(s + "\n");
        }
        writer.close();
    }
}
