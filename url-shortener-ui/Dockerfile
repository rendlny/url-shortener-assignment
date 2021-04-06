# copy code into app folder
#FROM node:14-alpine as build-step
#RUN mkdir -p /app

# set working directory
#WORKDIR /app

#install dependencies 
#COPY package.json /app
#RUN npm install
#RUN npm install -g @angular/cli@7.3.9

# add app
#COPY . /app
#CMD ng serve --host 0.0.0.0
#RUN npm run build --prod

# create nginx server, deploy app onto it
FROM nginx:1.17.1-alpine
# copy files from dist to nginxs html folder
COPY /dist/url-shortener-ui /usr/share/nginx/html
# Copy the default nginx.conf
COPY /config/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80