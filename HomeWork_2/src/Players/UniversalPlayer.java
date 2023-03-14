package Players;
import Song.Song;
import java.util.Formatter;

/**The Universal Player Class*/
public class UniversalPlayer extends MusicPlayer{
    public UniversalPlayer(){
        super.supportedRecordType = "Universal";
    }

    /**Song Playing Function Override: every record type is supported now*/
    @Override
    public void playSong(Song song){
        Formatter formatter = new Formatter();
        formatter.format("Now Playing: %s —— %s via %s Player", song.getBand(), song.getName(), super.getSupportedRecordType());
        System.out.println(formatter);
    }
}
