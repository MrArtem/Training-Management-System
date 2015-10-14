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
            var headers = {'Content-Type': 'application/x-www-form-urlencoded'};
            return $http.post('/api/login', "username=" + login + "&password=" + password, {headers: headers});
        }
        
        function logout () {
            
        }
    }

})();