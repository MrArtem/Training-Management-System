(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('adminAPI', adminAPI);

    /* @ngInject */
    function adminAPI($http, urlProvider) {
        var adminAPI = {
            getApproveList: getApproveList,
            getLessonToApprove: getLessonToApprove,
            getNewsList: getNewsList,
            getStatistics: getStatistics
        };
        return adminAPI;

        function getApproveList() {
            return $http.post(urlProvider.getApproveList(), {
                page_size: 10,
                page: 0
            }).then(function (results) {
                return results.data;
            });
        }

        function getLessonToApprove(actionId) {
            return $http.get(urlProvider.getLessonToApprove(actionId)).then(function(result) {
                return result;
            });
        }

        function getNewsList() {
            var pageModel = {
                page: 0,
                page_size: 10
            };
            return $http.post(urlProvider.getNewsList(), pageModel).then(function (results) {
                return results.data;
            });
        }

        function getStatistics(statisticsModel) {
            return $http.post(urlProvider.getStatistics(), statisticsModel).then(function (result) {
                return result.data;
            });
        }

    }

})();