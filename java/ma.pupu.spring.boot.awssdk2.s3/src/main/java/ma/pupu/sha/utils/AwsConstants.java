package ma.pupu.sha.utils;

public class AwsConstants {
	public static final String AWS_REGION = System.getenv("aws_region");
	
	public static final String AWS_S3_URL_PREFIX = "https://s3.";
	public static final String AWS_SQS_URL_PREFIX = "https://sqs.";
	
	public static final String AWS_URL_SUFFIX = ".amazonaws.com";
	public static final String AWS_S3_URL = AWS_S3_URL_PREFIX + AWS_REGION + AWS_URL_SUFFIX;
	public static final String AWS_SQS_URL = AWS_SQS_URL_PREFIX + AWS_REGION + AWS_URL_SUFFIX;
}
