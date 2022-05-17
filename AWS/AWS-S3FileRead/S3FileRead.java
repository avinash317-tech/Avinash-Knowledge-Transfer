	//read file from S3
	public InputStream s3FileReadInput(String bucketName , String fileName) {
		AmazonS3Client client = new AmazonS3Client();
		GetObjectRequest request = new GetObjectRequest(bucketName,fileName);
		S3Object object = client.getObject(request);
	   	S3ObjectInputStream objectContent = object.getObjectContent();
	   		
	   	return objectContent;
	}
	// read yaml file from s3
	public Map s3FileReader(String bucketName , String fileName) throws JsonParseException, JsonMappingException, IOException , Exception {
		Map readValue = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		AmazonS3Client client = new AmazonS3Client();
		S3Object s3Object = client.getObject(new GetObjectRequest(bucketName, fileName));
		readValue = mapper.readValue(s3Object.getObjectContent(), Map.class);
		return readValue;
	}    
	// read List file from s3
	public List s3ListFileReader(String bucketName , String fileName) throws JsonParseException, JsonMappingException, IOException , Exception {
		List readValue = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		AmazonS3Client client = new AmazonS3Client();
		S3Object s3Object = client.getObject(new GetObjectRequest(bucketName, fileName));
		readValue = mapper.readValue(s3Object.getObjectContent(), List.class);
		return readValue;
	}