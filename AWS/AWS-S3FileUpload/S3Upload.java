	//upload file on AWS S3			
	public String s3Upload(String bucketName , String fileName , byte[] bytes) throws Exception{
		ObjectMetadata metaData = new ObjectMetadata();
		// creating S3 client
		AmazonS3Client s3Client = new AmazonS3Client();
		metaData.setContentLength(bytes.length);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		PutObjectRequest request = new PutObjectRequest(bucketName, fileName, byteArrayInputStream , metaData);
		s3Client.putObject(request);
		return "Success";
	}