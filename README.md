# Sales-Taxes-Kata

Sales-Taxes-Kata 

## Installation
Clone the project in your workspace

```bash
git clone git@github.com:altrdev/sales-demo.git
```
Go inside project root

```bash
cd sales-demo
```
##### Without Docker

Use Maven [mvn](https://maven.apache.org/download.cgi) to build and execute Sales-Taxes-Kata.

```bash
mvn package && java -jar target/sales-taxes.jar
```

If port 8080 already in use, you can specify another one with `-Dserver.port=9090`
```bash
mvn package && java -jar -Dserver.port=9090 target/sales-taxes.jar
```

If you see an error like `Fatal error compiling: invalid target release: 11`, please make sure you have `jdk 11`

##### With Docker
Starting Docker in your computer and build a docker image

```bash
mvn package && docker build -t sales-taxes:latest .
```

If you see an error like `Fatal error compiling: invalid target release: 11`, please make sure you have `jdk 11`

Starting docker image

```bash
docker run -p 8080:8080 sales-taxes:latest
```

###### If you don't have jdk11, I've published image to DockerHub

Pull image

```bash
docker pull altrdev/sales-taxes
```

Starting docker image

```bash
docker run -p 8080:8080 altrdev/sales-taxes:latest
```


Wait for mongo embedded dependencies to be downloaded before calling the APIs.

## Testing

There are unit tests under `src/test/java`, but I also exposed two REST API for get the results.

## Usage
Import this [Postman Collection](https://www.getpostman.com/collections/c06ec6165bfc83ec13c4).

GET `/api/v1/receipts` come back three documents

GET `/api/v1/receipts/{id}` come back specific document.

The only valid documents are: 

`f0f56086-6f4c-4ede-9700-35bd351f78af`

`4616e590-089c-4794-b265-2702bb079da5` 

`268f1260-0215-4e36-8c4f-dbc0dcd56812`

You can see the call log and response log in terminal console.

## Other
I used MongoDb Embedded as a database.
It is populated every time the application restarted

The first start may be slower, mongo embedded dependencies will be downloaded.



## License
[MIT](https://choosealicense.com/licenses/mit/)
