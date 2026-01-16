## Exercise 1.1

- Application build with Java and Spring Boot.

### Commands used:
Build and test app.

docker build . -t todo_app

![Terminal Output for 1.2](https://github.com/Junior264/DevOps_with_Kubernetes/blob/1.2/todo-app/Exercise_1.2_TERMINAL_OUTPUT.png)

Create tag and push app to docker hub.

docker tag todo_app junior246/todo_app
docker push junior246/todo_app

add to kubernetes.
kubectl create deployment todo-app --image=junior246/log_output

check the pods.

![Running pods in 1.2](https://github.com/Junior264/DevOps_with_Kubernetes/blob/1.2/todo-app/Exercise_1.2_RUNNING_PODS.png)


## Exercise 1.4

Changed files [here](https://github.com/Junior264/DevOps_with_Kubernetes/tree/1.4/todo-app/manifests)

![Commands for 1.4](https://github.com/Junior264/DevOps_with_Kubernetes/blob/1.4/todo-app/Exercise_1.4_COMMANDS.png)
