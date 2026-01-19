## Exercise 1.1

- Application build with Java and Spring Boot.

Here are the commands used:

![Deployment for Exercise 1.1](https://github.com/Junior264/DevOps_with_Kubernetes/blob/main/log_output/Exercise_1.1_Commands.png)


## Exercise 1.3

Changed files [here](https://github.com/Junior264/DevOps_with_Kubernetes/tree/1.2/log_output/manifests)

![Deployment for Exercise 1.1](https://github.com/Junior264/DevOps_with_Kubernetes/blob/1.3/log_output/Exercise_1.3_COMMANDS.png)

## Exercise 1.7

Changed files [here](https://github.com/Junior264/DevOps_with_Kubernetes/tree/1.7/log_output/manifests)

Used following commands to test:

Rebuild image and push the changes to docker hub.
docker build . -t log_output
docker tag log_output junior246/log_output  
docker push junior246/log_output

Open a new k3d cluster, create updated deployment from image log_output and apply the currently made manifests.
k3d cluster create --port 8082:30080@agent:0 -p 8081:80@loadbalancer --agents 2
kubectl create deployment log-output --image=junior246/log_output
kubectl apply -f manifests

## Exercise 1.10

The git repo contains both changes.

Changed manifests configs [here](https://github.com/Junior264/DevOps_with_Kubernetes/tree/1.10/log_output/manifests)

### How i test:
### Step 1:
Comment out the GET endpoint in  log_output/src/main/java/dev/ewald/log_output/controller/LogOutputController.java

\# @GetMapping
\# @ResponseStatus(HttpStatus.OK)
\# public List<String> getTimeStampedRandomString() throws IOException {
\#     return logOutputService.getTimeStampedRandomStrings();
\# }

Create splitted image for generate and push image to k3d:
- docker build . -t log_output_generate --no-cache
- k3d image import log_output_generate:latest

### Step 2:
Comment out the generation on startup in log_output/src/main/java/dev/ewald/log_output/LogOutputApplication.java

![Comment out this](https://github.com/Junior264/DevOps_with_Kubernetes/blob/1.10/log_output/Exercise_1.10_Step2.png)

Create splitted image for read and push image to k3d:
- docker build . -t log_output_read --no-cache
- k3d image import log_output_read:latest

Command after:
-  kubectl apply -f manifests

Can check with FreeLens if the containers shares the generated .txt file in /files (Should be available in log_output_read container if volumes set right)
Also can see if http://localhost:8081 display the random Strings.