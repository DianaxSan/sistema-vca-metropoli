package com.web.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImagenService {
	
	Resource cargar(String filename) throws MalformedURLException;
    String subir(MultipartFile file) throws IOException;
    boolean eliminar(String filename);

}
