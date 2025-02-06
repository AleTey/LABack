package com.backend.la.backendloveafrica.Services.external;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3ServiceImp implements IS3Service {

  @Autowired
  private AmazonS3 amazonS3;

  @Value("${aws.region}")
  private String region;

  @Override
  public String uploadFile(String key, MultipartFile multipartFile) throws Exception {

    try (InputStream inputStream = multipartFile.getInputStream()) {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType("image/jpeg");
      // metadata.setContentLength(multipartFile.getSize());
      amazonS3.putObject(new PutObjectRequest("file-uploads-la", key, inputStream, metadata));
      return generateFileUrl(key);

    } catch (Exception e) {
      throw new Exception("Error Uploading file to Cloud :(", e);
    }

    // throw new UnsupportedOperationException("Unimplemented method 'uploadFile'");
  }

  @Override
  public String generateFileUrl(String key) {
    // return String.format("https://%s.s3.%s.amazonaws.com/%s", "file-uploads-la",
    // region, key );
    // return String.format("https://file-uploads-la.s3.", region,
    // ".amazonaws.com/", key);
    return "https://file-uploads-la.s3." + region + ".amazonaws.com/" + key;
  }

  @Override
  public void deleteFile(String key) {
    amazonS3.deleteObject("file-uploads-la", key);
  }

  @Override
  public String keyGenerator(String name, Long id) {
    return name + id.toString();
  }

}
