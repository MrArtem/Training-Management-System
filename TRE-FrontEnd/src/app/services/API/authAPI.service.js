(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('authAPI', userAPI);

    /* @ngInject */
    function userAPI($http, $timeout) {
        var userAPI = {
            login: login,
            logout: logout
        }
    
        return userAPI;
        
        /////
        
        function login (login,password) {
            var headers = {authorization: "Basic " + btoa(login + ":" + password)};
            return $http.get('login', {headers: headers});
        }
        
        function logout () {
            
        }
    }

})();