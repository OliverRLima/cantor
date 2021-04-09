FROM node:13.12.0-alpine
MAINTAINER Oliver Lima

WORKDIR /app

COPY . ./

RUN npm install --silent

ENTRYPOINT ["npm","start"]

EXPOSE 3000
