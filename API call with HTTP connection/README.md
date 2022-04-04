# HTTP connections for API Call....

	Function parameter:- 
		
		 url  // api endpoint
		 payload  // request body to pass
		method  // HTTP method (like POST, GET, ....)
		contentType // format in which request passed
		flagForPayloy // true for payload(POST) and false for without payload....
		
		ex:- 
		
		APICall("https://.......", payload, "POST", "application/json", true)