package mk.ukim.finki.wp.lab3.service.impl;


import mk.ukim.finki.wp.lab3.model.Album;
import mk.ukim.finki.wp.lab3.repository.AlbumRepository;
import mk.ukim.finki.wp.lab3.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl  implements AlbumService {

    private final AlbumRepository albumrepository;

    public AlbumServiceImpl(AlbumRepository albumrepository) {
        this.albumrepository = albumrepository;
    }


    @Override
    public List<Album> findAll(){
        return albumrepository.findAll();
    }

    @Override
    public Album findById(Long id) {
        return albumrepository.findById(id).orElse(null);
    }
}
