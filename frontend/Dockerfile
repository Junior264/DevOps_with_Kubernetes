FROM node:24.13-alpine3.22

WORKDIR /usr/local/app

COPY . .

EXPOSE 5173

RUN npm install

CMD ["npm", "run", "dev"]