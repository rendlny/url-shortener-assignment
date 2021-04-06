'use strict';
angular.module('myAppApp')

.component('appUsers', {
    templateUrl: 'views/users.html',
    controller: function($http, APP, $state){
      let vm = this;
      vm.users = [];
      vm.$onInit = function(){
        getData();
      }

      function getData(){
        $http.get(APP.API_URL + '/links').then(function(response){
          vm.links = response.data;
        })
      }

      vm.addLink = function(){
        $state.go('userAdd');
      }

      vm.getLink = function(id){
        $http.get(APP.API_URL + '/links/' + id).then(function(response){
            vm.links = response.data;
        })
      }

    }
  })