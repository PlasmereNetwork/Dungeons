package net.plasmere.dungeons.objects;

import net.plasmere.dungeons.Dungeons;
import net.plasmere.dungeons.objects.entities.ClickableEntity;
import net.plasmere.dungeons.objects.entities.enums.ClickableEntityTypes;

import java.io.File;
import java.util.*;

public class Storage {
    private HashMap<String, String> info = new HashMap<>();
    private final String filePrePath = Dungeons.getInstance().getDataFolder() + File.separator + "configs" + File.separator;

    public File file;
    public List<ClickableEntity> clickables;

    public Storage() {
        construct(true);
    }

    public Storage(boolean create){
        construct(create);
    }

    private void construct(boolean createNew){
        this.file = new File(filePrePath + "storage" + ".yml");

        if (createNew || file.exists()) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile() { return file; }

}
