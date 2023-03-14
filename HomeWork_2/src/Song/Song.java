package Song;

import Records.SoundCarrier;

import java.util.Formatter;

public class Song {
    private final String band;
    private final String name;
    private final SoundCarrier carrier;


    /**The constructor for the Song.Song class*/
    public Song(String band, String name, SoundCarrier carrier) {
        this.band = band;
        this.name = name;
        this.carrier = carrier;
    }

    /**Getter for the Band parameter*/
    public String getBand() {
        return band;
    }

    /**Getter for the Name parameter*/
    public String getName() {
        return name;
    }



    /**Returns the type of the Sound Carrier on which the song was recorded*/
    public String getCarrierName(){
        return carrier.getCarrierType();
    }

    /**To string method override*/
    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format("%s — %s",band, name);
        return formatter.toString();
    }
}
