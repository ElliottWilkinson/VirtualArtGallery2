package org.example.controllers;

import org.example.dao.ArtDao;
import org.example.model.Art;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping()
public class ArtController
{
    private ArtDao artDao;
    public ArtController(ArtDao artDao)
    {
        this.artDao = artDao;
    }


}
