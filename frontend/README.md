# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Babel](https://babeljs.io/) (or [oxc](https://oxc.rs) when used in [rolldown-vite](https://vite.dev/guide/rolldown)) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.

## Exercise 1.5:

Used commands: 

![Deployment for Exercise 1.1](https://github.com/Junior264/DevOps_with_Kubernetes/blob/1.5/frontend/Exercise_1.5_COMMANDS.png)

Browser output: 

![Deployment for Exercise 1.1](https://github.com/Junior264/DevOps_with_Kubernetes/blob/1.5/frontend/Exercise_1.5_BROWSER.png)

## Exercise 1.6:

Used commands:

Delete old cluster.
k3d cluster delete

Create new cluster
k3d cluster create --port 3000:30080@agent:0 -p 8081:80@loadbalancer --agents 2

Changed files [here](https://github.com/Junior264/DevOps_with_Kubernetes/tree/1.6/frontend/manifests)

Create new-frontend deployment and apply NodePort Service from yaml.
kubectl create deployment new-frontend --image=junior246/new_frontend
cd frontend && kubectl apply -f manifests/deployment.yaml