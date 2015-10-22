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
        }

        return searchAPI;

        /////

        function findAnything() {

        }

        function findTrainings() {

        }

        function findUsers() {

        }
    }

})();