(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('adminAPI', adminAPI);

    /* @ngInject */
    function adminAPI() {
        var adminAPI = {
            getApproveList: getApproveList
        }
        return adminAPI;

        function getApproveList() {
            return $http.get().then(function (results) {
                return results.data;
            });
        }

    }

})();