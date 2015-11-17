(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('authAPI', authAPI);

    /* @ngInject */
    function authAPI($http, urlProvider) {
        var authAPI = {
            login: login,
            logout: logout
        };

        return authAPI;

        /////

        function login(login, password) {
            var headers = {'Content-Type': 'application/x-www-form-urlencoded'};
            return $http.post(urlProvider.login(), "username=" + login + "&password=" + password, {headers: headers}).then(function(data){
                return data.data;
            });

        }

        function logout() {
            return $http.get(urlProvider.logout());
        }
    }

})();