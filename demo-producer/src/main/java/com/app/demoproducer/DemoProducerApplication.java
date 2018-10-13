package com.app.demoproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProducerApplication.class, args);
	}
}

@Component
class CommandLine implements CommandLineRunner{

	@Autowired KategoriRepository kategoriRepository;

	@Override
	public void run(String... args) throws Exception {
		String[] data = {"kategori1", "kategori2", "kategori3", "kategori4", "kategori5"};
		for (int i=1; i < data.length; i++){

			kategoriRepository.save(Kategori.builder()
					.name(data[i])
					.build());
		}
	}
}