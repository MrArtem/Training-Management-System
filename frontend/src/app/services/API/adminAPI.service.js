(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('adminAPI', adminAPI);

    /* @ngInject */
    function adminAPI($http, urlProvider) {
        var adminAPI = {
            getApproveList: getApproveList,
            getNewsList: getNewsList,
            getStatistics: getStatistics
        }
        return adminAPI;

        function getApproveList() {
            return $http.get(urlProvider.getApproveList(), {
                params: {
                    page_size: 10,
                    page: 0
                }
            }).then(function (results) {
                return results.data;
            });
        }

        function getNewsList() {
            return $http.get(urlProvider.getNewsList(), {
                params: {
                    page: 0,
                    page_size: 10
                }
            }).then(function (results) {
                return results.data;
            });
        }

        function getStatistics(statisticsModel) {
            return $http.post(urlProvider.getStatistics(), statisticsModel).then(function(result) {
                return result.data;
            });
        }

    }

})();