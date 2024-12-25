package mk.ukim.finki.wp.lab3.service;

import mk.ukim.finki.wp.lab3.model.Album;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AlbumService {

    public List<Album> findAll();
    Album findById(Long id);

}
