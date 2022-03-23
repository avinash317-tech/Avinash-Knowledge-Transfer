# Sam-Template for deploy Lambda and Rest-API (POST method) without proxy 
	- create samTemplate.yaml file in project root directory.
		- make sure indentation should be correct.
		- file name should be same as given or if you change the template name, make sure it is uplated in buildspec file.
	- add the samTemplate.yaml file name in buildSpec file under srtifects section.
	- create codePipe Line for the deployment of lambda and Api gateway.
	- Find the example for develop the samTemplate file.
		
## samTemplate.yaml file Template (POST method)
		
		
			AWSTemplateFormatVersion : '2010-09-09'
			Transform: AWS::Serverless-2016-10-31
			Description: A sample SAM template for deploying Lambda functions.
			Resources:

			  ApiGatewayApi:
				Type: 'AWS::Serverless::Api'
				Properties:
				  Name: **<name of API >**
				  StageName: **<stage name>**
				  DefinitionBody:
					openapi: 3.0.1
					info:
					  title: **< title>**
					paths:
					  /**<path value>**:
						post:
						  consumes:
						  - "application/json"
						  produces:
						  - "application/json"
						  responses:
							'200':
							  description: 200 response
							  schema:
								$ref: "#/definitions/Empty"
						  x-amazon-apigateway-integration:
							uri: !Sub >-
							  arn:aws:apigateway:${AWS::Region}:lambda:path/2015-03-31/functions/${MyFunction.Arn}/invocations
							responses:
							  default:
								statusCode: '200'
								responseTemplates:
								  application/json: >
									##set($allParams = $input.params()){
									 $input.json('$')
									  
									 #foreach($type in $allParams.keySet())
									  #set($params = $allParams.get($type))
									 "$type" : {
									 #foreach($paramName in $params.keySet())
									   "$paramName" : "$util.escapeJavaScript($params.get($paramName))"
									   #if($foreach.hasNext)#end
										#end
										
									   #if($foreach.hasNext)#end
									   #end    
								 
							passthroughBehavior: WHEN_NO_MATCH
							httpMethod: POST
							type: aws
				  EndpointConfiguration: REGIONAL
						  
			  MyFunction:
				Type: AWS::Serverless::Function
				Properties:
				  FunctionName: **<lambda function name>**
				  Handler: **<lambda handler path>**
				  CodeUri: **<jar/war path>**
				  Runtime: **<java version>**
				  Role: **<role arn>**
				  MemorySize: **<memory size allocated to lambda>**
				  Timeout: **<time out value in ms , max = 900>**
				  VpcConfig:
					SecurityGroupIds:
					  - **<value>**
					SubnetIds:
					  - **<value>**   # here you can add multiple SubnetIds
				  Events:
					ApiEvent:
					  Type: Api
					  Properties:
						Path: /**<path value>**
						Method: post
						RestApiId:
						  Ref: ApiGatewayApi
						  
			  InvokePermission:
				Type: 'AWS::Lambda::Permission'
				Properties:
				  FunctionName: !Ref MyFunction
				  Action: 'lambda:InvokeFunction'
				  Principal: apigateway.amazonaws.com
				  SourceArn: !Sub 
					- 'arn:aws:execute-api:${AWS::Region}:${AWS::AccountId}:${restApiId}/*/*'
					- restApiId: !Ref ApiGatewayApi            
			Outputs:
			  ProductionURL:
				Description: Deployment URL
				Value: !Sub 
				  - >-
					https://${restApiId}.execute-api.${AWS::Region}.amazonaws.com/production/**<path value>**
				  - restApiId: !Ref ApiGatewayApi
		
		
