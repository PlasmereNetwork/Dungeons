package net.plasmere.dungeons.utils.managers.worlds;

public enum Floors {
    FLOOR_ONE("floor1_", 5, 0, 100, 0, 15);

    public String namePre;
    public int maxPlayers;
    public double x;
    public double y;
    public double z;
    public int tiles;

    private Floors(String namePre, int maxPlayers, double x, double y, double z, int tiles){
        this.namePre = namePre;
        this.maxPlayers = maxPlayers;
        this.x = x;
        this.y = y;
        this.z = z;
        this.tiles = tiles;
    }
}
