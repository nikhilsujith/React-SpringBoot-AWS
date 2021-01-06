package tech.nikhilsujith.reactspringaws.bucket;

public enum BucketName {

    PROFILE_IMAGE("react-spring-aws--image-upload-download-full-stack");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
