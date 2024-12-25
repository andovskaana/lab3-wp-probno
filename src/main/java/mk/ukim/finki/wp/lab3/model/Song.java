package mk.ukim.finki.wp.lab3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    @ManyToOne
    // @JoinColumn(name = "album_id")
    private Album album;

    @ManyToMany
    @JoinTable(
            name = "song_artist", // Name of the join table
            joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"), // Maps to Song.id
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id") // Maps to Artist.id
    )
    private List<Artist> performers;

    public Song(String title, String genre, int releaseYear, Album album, List<Artist> performers) {

      //  this.id = (long) (Math.random() * 10000); // Generate unique ID
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album = album;
        this.performers = performers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artist> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Artist> performers) {
        this.performers = performers;
    }
}
