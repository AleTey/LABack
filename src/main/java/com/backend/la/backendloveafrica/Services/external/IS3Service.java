package com.backend.la.backendloveafrica.Services.external;

import org.springframework.web.multipart.MultipartFile;

public interface IS3Service {

  String uploadFile(String key, MultipartFile multipartFile) throws Exception;

  String generateFileUrl(String key);

  void deleteFile(String key);

  String keyGenerator(String name, Long id);

}
