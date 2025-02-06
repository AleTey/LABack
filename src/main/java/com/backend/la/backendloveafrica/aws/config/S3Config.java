package com.backend.la.backendloveafrica.aws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {

  @Value("${aws.s3.access.key}")
  private String awsS3AccessKey;

  @Value("${aws.s3.secret.key}")
  private String awsS3PrivateKey;

  @Value("${aws.region}")
  private String awsS3Region;

  @Bean
  public AmazonS3 amazonS3() {
    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3AccessKey, awsS3PrivateKey);
    return AmazonS3ClientBuilder.standard()
        .withRegion(awsS3Region)
        .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
        .build();
  }

}
