RuleBasedMatchMaker_RESTful_WS_Maven
====================================

A Maven project that generates the RESTful web-service of the CLOUD4All Rule-based MatchMaker.

To build the RuleBasedMatchMaker web-service:

1) Download [JDK - version 7u67](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) or later and install it

2) Download [Apache Maven](http://maven.apache.org/) and install it

3) Run "mvn clean install" in the root directory of the source code

Usage example using [curl](http://curl.haxx.se/):

	curl -X POST -H "Content-Type: application/json" http://localhost:8080/CLOUD4All_RBMM_Restful_WS/RBMM/runJSONLDRules -d @{MY_INPUT.json}

### Troubleshooting 
	
	If you are encountering problems with test failures, please ensure that the encoding of the debug files (e.g. [5_RBMMJsonOutput.json](https://github.com/NickKaklanis/RuleBasedMatchMaker_RESTful_WS_Maven/blob/JSON-LD/src/main/webapp/WEB-INF/debug/5_RBMMJsonOutput.json)) is similar with the encoding of the files including the tests' expected outcomes (e.g. [expected_output_test1.json](https://github.com/NickKaklanis/RuleBasedMatchMaker_RESTful_WS_Maven/blob/JSON-LD/src/main/webapp/WEB-INF/testData/expectedTestOutcomes/expected_output_test1.json)).
	
### Funding Acknowledgement

The research leading to these results has received funding from the European
Union's Seventh Framework Programme (FP7/2007-2013) under grant agreement No.289016