package Players;
import Song.Song;
import java.util.Formatter;

/**Abstract Class for all Music Players. Describes main fields and also the playSong method*/
public abstract class MusicPlayer {
    protected String supportedRecordType = "";

    /**Getter for the supported record type*/
    public String getSupportedRecordType(){
        return supportedRecordType;
    }

    /**The method for songs playing*/
    public void playSong(Song song) throws Exception {
        Formatter formatter = new Formatter();
        if (!song.getCarrierName().equals(supportedRecordType)) {
            formatter.format("Unsupported record type: %s. Expected: %s", song.getCarrierName(), supportedRecordType);
            throw new Exception(formatter.toString());
        }
        formatter.format("Now Playing: %s —— %s via %s Player", song.getBand(), song.getName(), getSupportedRecordType());
        System.out.println(formatter);
    }
}
