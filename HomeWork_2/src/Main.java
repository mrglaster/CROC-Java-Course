/*Players classes Import*/
import Players.CDPlayer;
import Players.UniversalPlayer;
import Players.USBPlayer;
import Players.VinylPlayer;

/*Records classes Import*/
import Records.VinylRecord;
import Records.CDRecord;
import Records.USBRecord;

/*Song import*/
import Song.Song;

/**
 *
 *                      Russian
 *      Необходимо разработать музыкальную систему.

	1) cуществуют несколько звуковоспроизводящих устройств (виниловая вертушка, сд, универсальный плеер и т.д.)
	2) существует несколько носителей музыкальных композиций (пластинка, сд, флешка и т.д)
	3) существуют несколько песен, у которых есть имя исполнителя (группы) и название
	4) Звуковоспроизводящее устройство должно выводить в консоль информацию о том, что за устройство воспроизводит песню, ее исполнителя и название.
	   В случае, если устройство не может воспроизвести музыку с требуемого носителя выводить соответствующее сообщение


                        English
        It is necessary to develop a musical system.

	1) there are several sound reproducing devices (vinyl turntable, cd, universal player, etc.)
    2) there are several carriers of musical compositions (record, cd, flash drive, etc.)
    3) there are several songs that have the name of the artist (group) and the title
    4) The audio device should output to the console information about what device is playing the song, its artist and title.
       If the device cannot play music from the required media, display a corresponding message
 */


public class Main {


    /**Function demonstration Records Playing*/
    public static void demonstratePlaying() throws Exception {

        //Initial input & the Player's initialization
        System.out.println("\n      DEMONSTRATION OF CORRESPONDENCE BETWEEN RECORD TYPE AND PLAYER");
        Song cdSong = new Song("AVICII", "Somewhere in Stokholm", new CDRecord());
        Song vinylSong = new Song("Kraftwerk", "Die Mensch-Maschiene", new VinylRecord());
        Song usbSong = new Song("Kevin Macleod", "Cipher", new USBRecord());

        //Players initialization
        CDPlayer cdPlayer = new CDPlayer();
        VinylPlayer vinylPlayer = new VinylPlayer();
        USBPlayer usbPlayer = new USBPlayer();
        UniversalPlayer universalPlayer = new UniversalPlayer();

        //Playing the songs
        cdPlayer.playSong(cdSong);
        vinylPlayer.playSong(vinylSong);
        usbPlayer.playSong(usbSong);

        //Universal Player Demonstration
        System.out.println("\n      UNIVERSAL PLAYER DEMONSTRATION");
        universalPlayer.playSong(cdSong);
        universalPlayer.playSong(vinylSong);
        universalPlayer.playSong(usbSong);

        System.out.println("\n DEMONSTRATION OF RECORDS AND PLAYERS TYPES DISCREPANCY (An Exception will be invoked)");
        vinylPlayer.playSong(cdSong);
    }


    /**The main function*/
    public static void main(String[] args) throws Exception {
        demonstratePlaying();
    }
}