(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('searchAPI', searchAPI);

    /* @ngInject */
    function searchAPI($http, urlProvider) {
        var searchAPI = {
            findAnything: findAnything,
            findTrainings: findTrainings,
            findUsers: findUsers
        };

        return searchAPI;

        /////

        function findAnything(searchQuery) {

        }

        function findTrainings(searchQuery) {
            return $http.post(urlProvider.findTrainings(searchQuery), searchQuery).then(function(result) {
                return result.data;
            });
        }

        function findUsers(searchQuery) {
            return $http.post(urlProvider.findUsers(searchQuery), searchQuery).then(function(result) {
                return result.data;
            });
        }
    }

})();