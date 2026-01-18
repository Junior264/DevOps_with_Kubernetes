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