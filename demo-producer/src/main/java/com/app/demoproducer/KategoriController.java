package com.app.demoproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/kategori")
public class KategoriController {

    @Autowired KategoriRepository kategoriRepository;

    @GetMapping
    public ResponseEntity<List<Kategori>> listKategori(){
        return ResponseEntity.ok(
                kategoriRepository.findAll());
    }

    @GetMapping(value = "/{kategoriId}")
    public ResponseEntity<Kategori> getKategoriId(@PathVariable("kategoriId") Long kategoriId){
        return kategoriRepository.findById(kategoriId)
                .map(kategori -> {
                    return ResponseEntity.ok(kategori);
                }).orElseThrow(() -> new ResourceNotFound("kategori : "+kategoriId+" tidak ada"));
    }

    @PostMapping
    public ResponseEntity<Kategori> newKategori(@RequestBody Kategori kategori){
        return Optional.ofNullable(kategoriRepository.save(kategori))
                .map(result -> new ResponseEntity<>(kategori, HttpStatus.CREATED))
                .orElse(new ResponseEntity<Kategori>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping(value = "/{kategoriId}")
    public ResponseEntity<Kategori> updateKategori(@PathVariable("kategoriId") Long kategoriId,
                                                   @RequestBody Kategori kategori){
        return kategoriRepository.findById(kategoriId)
                .map(currentKategori -> {
                    currentKategori.setName(kategori.getName());
                    return ResponseEntity.ok(kategoriRepository.save(currentKategori));
                }).orElseThrow(() -> new ResourceNotFound("kategori "+kategoriId+" tidak ada"));
    }
}
