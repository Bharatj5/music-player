package uk.co.music;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");
        Song third = new Song("La la land");
        Song four = new Song("Oh, miles");
        Song five = new Song("Chop Suey!");
        Song six = new Song("Iron man");

        first.setNextSong(second);
        second.setNextSong(third);
        third.setNextSong(four);
        four.setNextSong(first);

        five.setNextSong(six);

        System.out.printf("In the repeating playlist: %s, %s, %s, %s\n",
                first.isInRepeatingPlaylist(),
                second.isInRepeatingPlaylist(),
                third.isInRepeatingPlaylist(),
                four.isInRepeatingPlaylist());
        System.out.printf("Not in the repeating playlist: %s, %s\n",
                five.isInRepeatingPlaylist(),
                six.isInRepeatingPlaylist());
    }

    public boolean isInRepeatingPlaylist() {
        Song currentSong = this;
        final Set<Song> visitedSongs = new HashSet<>();
        do {
            if (Objects.isNull(currentSong.nextSong)) {
                return false;
            }
            if (!visitedSongs.add(currentSong)) {
                // avoid cyclic dependency
                return currentSong.equals(this);
            }
            currentSong = currentSong.nextSong;
        } while (currentSong != this);
        // as we reached back to the seed node
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        // assuming distinct song names
        return Objects.equals(name, song.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

}