## Exercise 1.9

Created this new application with an endpoint /pingpong that returns message "pong 1" and increases the count each time you enter this request:

pong 1
pong 2
pong 3
...

Used commands:

Create new build and push it to docker hub, than start it up in kubernetes.
- docker build . -t ping_pong_app
- docker tag ping_pong_app junior246/ping_pong_app
- docker push junior246/ping_pong_app
- kubectl create deployment ping-pong-app --image=junior246/ping_pong_app

Create manifest and add them.
- kubectl apply -f manifests

## Exercise 1.11

Config changes made [here](https://github.com/Junior264/DevOps_with_Kubernetes/tree/1.11/persistendData)
and [here](https://github.com/Junior264/DevOps_with_Kubernetes/tree/1.11/ping-pong-app/manifests)

## Exercise 2.3

Execute command:
kubectl create namespace exercises

then add:

metadata:
  namespace: exercises

to my yaml related to that.