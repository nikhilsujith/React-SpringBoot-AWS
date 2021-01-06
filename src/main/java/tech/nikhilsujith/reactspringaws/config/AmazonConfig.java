package tech.nikhilsujith.reactspringaws.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

/*  This class gives use the S3 client class that we can use it to  */
    @Bean //Spring intantiates the AmazonS3 class with the contained configurations which can be injected
            // into other classes
    public AmazonS3 s3(){
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIAXUBPRCRLVTF7EOHW",
                ",FbCqtSgpvdzA5GCppnk9BbyUhc7EmHrnm7+ChckA"
        );
//        Following the builder pattern
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "https://s3.us-east-2.amazonaws.com","us-east-2"))
                .build();
    }

}
