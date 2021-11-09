import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.audio.AudioSourceBase;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        String token = " YOUR BOT TOKEN HERE ";

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        Server server = api.getServerById("753673525415641229").get();

        System.out.println();

        AtomicBoolean connectedStatus = new AtomicBoolean(false);

        server.updateNickname(api.getYourself(), "Kyber");
        String song = "";
        while (true) {
            String[] ajat;
            String localTime = java.time.LocalTime.now().toString();
            ajat = localTime.split("\\.|:");

            TimeUnit.MILLISECONDS.sleep(600);

            String tunti = ajat[0];
            String min = ajat[1];
            String sek = ajat[2];

            if (!connectedStatus.get()) {
                if (tunti.equals("01") && min.equals("00") && sek.equals("00") || tunti.equals("13") && min.equals("00") && sek.equals("00")) {
                    song = "1kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("02") && min.equals("00") && sek.equals("00") || tunti.equals("14") && min.equals("00") && sek.equals("00")) {
                    song = "2kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("03") && min.equals("00") && sek.equals("00") || tunti.equals("15") && min.equals("00") && sek.equals("00")) {
                    song = "3kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("04") && min.equals("00") && sek.equals("00") || tunti.equals("16") && min.equals("00") && sek.equals("00")) {
                    song = "4kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("05") && min.equals("00") && sek.equals("00") || tunti.equals("17") && min.equals("00") && sek.equals("00")) {
                    song = "5kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("06") && min.equals("00") && sek.equals("00") || tunti.equals("18") && min.equals("00") && sek.equals("00")) {
                    song = "6kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("07") && min.equals("00") && sek.equals("00") || tunti.equals("19") && min.equals("00") && sek.equals("00")) {
                    song = "7kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("08") && min.equals("00") && sek.equals("00") || tunti.equals("20") && min.equals("00") && sek.equals("00")) {
                    song = "8kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("09") && min.equals("00") && sek.equals("00") || tunti.equals("21") && min.equals("00") && sek.equals("00")) {
                    song = "9kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("10") && min.equals("00") && sek.equals("00") || tunti.equals("22") && min.equals("00") && sek.equals("00")) {
                    song = "10kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("11") && min.equals("00") && sek.equals("00") || tunti.equals("23") && min.equals("00") && sek.equals("00")) {
                    song = "11kyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("12") && min.equals("00") && sek.equals("00") || tunti.equals("00") && min.equals("00") && sek.equals("00")) {
                    song = "12kyber.mp3";
                    connectedStatus.set(true);
                } else if (min.equals("30") && sek.equals("00")) {
                    song = "puolikyber.mp3";
                    connectedStatus.set(true);
                } else if (tunti.equals("04") && min.equals("20") && sek.equals("00") || tunti.equals("16") && min.equals("20") && sek.equals("00")) {
                    song = "420kyber.mp3";
                    connectedStatus.set(true);
                }
            } else {

            }

            if (connectedStatus.get()) {

                ServerVoiceChannel channel = server.getVoiceChannelById("765992335980888164").get();

                String finalSong = song;
                System.out.println(song);
                channel.connect().thenAccept(audioConnection -> {

                    connectedStatus.set(false);

                    AudioPlayerManager playerManager = new DefaultAudioPlayerManager();

                    playerManager.registerSourceManager(new LocalAudioSourceManager());

                    AudioPlayer player = playerManager.createPlayer();

                    AudioSource source = new LavaplayerAudioSource(api, player);

                    audioConnection.setAudioSource(source);

                    playerManager.loadItem(finalSong, new AudioLoadResultHandler() {
                        @Override
                        public void trackLoaded(AudioTrack track) {
                            player.playTrack(track);
                        }

                        @Override
                        public void playlistLoaded(AudioPlaylist playlist) {
                            for (AudioTrack track : playlist.getTracks()) {
                                player.playTrack(track);
                            }
                        }

                        @Override
                        public void noMatches() {
                            System.out.println("File not found");
                            ;
                        }

                        @Override
                        public void loadFailed(FriendlyException throwable) {
                            System.out.println("Load failed");
                        }
                    });

                    try {
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println("TOIMII after 10sek");
                        connectedStatus.set(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        audioConnection.close();
                        connectedStatus.set(false);
                    }
                    audioConnection.close();
                    connectedStatus.set(false);
                }).exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
            }
        }
    }

    static class LavaplayerAudioSource extends AudioSourceBase {

        private final AudioPlayer audioPlayer;
        private AudioFrame lastFrame;

        /**
         * Creates a new lavaplayer audio source.
         *
         * @param api         A discord api instance.
         * @param audioPlayer An audio player from Lavaplayer.
         */
        public LavaplayerAudioSource(DiscordApi api, AudioPlayer audioPlayer) {
            super(api);
            this.audioPlayer = audioPlayer;
        }

        @Override
        public byte[] getNextFrame() {
            if (lastFrame == null) {
                return null;
            }
            return applyTransformers(lastFrame.getData());
        }

        @Override
        public boolean hasFinished() {
            return false;
        }

        @Override
        public boolean hasNextFrame() {
            lastFrame = audioPlayer.provide();
            return lastFrame != null;
        }

        @Override
        public AudioSource copy() {
            return new LavaplayerAudioSource(getApi(), audioPlayer);
        }
    }
}