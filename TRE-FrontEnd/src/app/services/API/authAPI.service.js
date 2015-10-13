(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('authAPI', userAPI);

    /* @ngInject */
    function userAPI($q, $timeout) {
        var userAPI = {
            login: login,
            logout: logout
        }

        return userAPI;

        /////

        function login(login, password) {
            var defer = $q.defer();
            $timeout(function () {

                defer.resolve({login: login, password: password});

            }, 1000)


            return defer.promise
        }

        function logout() {

        }
    }

})();