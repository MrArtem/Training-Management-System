(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('userAPI', userAPI);

    /* @ngInject */
    function userAPI() {
        var userAPI = {}
        return userAPI;
    }

})();