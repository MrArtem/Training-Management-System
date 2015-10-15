(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('authAPI', userAPI);

    /* @ngInject */
    function userAPI($http, urlProvider) {
        var userAPI = {
            login: login,
            logout: logout
        }

        return userAPI;

        /////

        function login(login, password) {
            console.log(login, password);
            var headers = {'Content-Type': 'application/x-www-form-urlencoded'};
            return $http.post(urlProvider.login(), "username=" + login + "&password=" + password, {headers: headers}).then(function(result) {
                return result.data;
            });
        }

        function logout() {
            return $http.get(urlProvider.logout());
        }
    }

})();