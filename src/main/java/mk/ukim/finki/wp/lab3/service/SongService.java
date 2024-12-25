package mk.ukim.finki.wp.lab3.service;

import mk.ukim.finki.wp.lab3.model.Artist;
import mk.ukim.finki.wp.lab3.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public interface SongService{
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
     Song findById(Long Id);
    void save(Song song);
    void update(Long id, Song updatedSong);
    void delete(Long id);
    List<Song> findAllByAlbumId(Long albumId);
}
