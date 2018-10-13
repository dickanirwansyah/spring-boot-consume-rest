package com.app.democonsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class KategoriConsumeRest {

    @Autowired
    RestTemplate restTemplate;

    private static final String URI = "http://localhost:8888/api/kategori";

    @GetMapping(value = "/template/kategoris")
    public ResponseEntity<List<Kategori>> getKategoris(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<Kategori>> entity = new HttpEntity<>(httpHeaders);

        return ResponseEntity.ok(
                restTemplate.exchange(URI, HttpMethod.GET, entity, List.class)
                .getBody());
    }

    @GetMapping(value = "/template/kategoris/{kategoriId}")
    public ResponseEntity<Kategori> getKategori(@PathVariable("kategoriId") Long kategoriId){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Kategori> entity = new HttpEntity<>(httpHeaders);


        return Optional.ofNullable(restTemplate.exchange(URI+"/"+kategoriId,
                HttpMethod.GET, entity, Kategori.class).getBody())
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<Kategori>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/newkategori")
    public ResponseEntity<Kategori> newKategori(@RequestBody Kategori kategori){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Kategori> entity = new HttpEntity<>(kategori, httpHeaders);

        return Optional.ofNullable(restTemplate.exchange(URI, HttpMethod.POST, entity, Kategori.class)
                .getBody()).map(result -> new ResponseEntity<>(result, HttpStatus.CREATED))
                .orElse(new ResponseEntity<Kategori>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping(value = "/updatekategori/{kategoriId}")
    public ResponseEntity<Kategori> updateKategori(@PathVariable("kategoriId") Long kategoriId,
                                                   @RequestBody Kategori kategori){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Kategori> entity = new HttpEntity<>(kategori, httpHeaders);

        return Optional.ofNullable(restTemplate.exchange(URI+"/"+kategoriId, HttpMethod.PUT, entity, Kategori.class)
                .getBody()).map(result -> new ResponseEntity<>(result, HttpStatus.CREATED))
                .orElse(new ResponseEntity<Kategori>(HttpStatus.BAD_REQUEST));
    }
}
