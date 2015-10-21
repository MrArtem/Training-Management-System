(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('userAPI', userAPI);

    /* @ngInject */
    function userAPI($http, urlProvider) {
        var userAPI = {
            getCurrentCourses: getCurrentCourses,
            getFeedbacksOn: getFeedbacksOn,
            getPastCourses: getPastCourses,
            getProfileInfo: getProfileInfo,
            getWaitingCourses: getWaitingCourses
        };
        return userAPI;

        function getCurrentCourses(userId) {
            return $http.get(urlProvider.getCurrentCoursesForUser(userId)).then(function(result) {
                return result.data;
            });
        }

        function getFeedbacksOn(userId) {
            //return;
        }

        function getPastCourses(userId) {
            return $http.get(urlProvider.getPastCoursesForUser(userId)).then(function(result) {
                return result.data;
            });
        }

        function getProfileInfo(userId) {
            return $http.get(urlProvider.getProfileInfo(userId)).then(function(result) {
                return result.data;
            });
        }

        function getWaitingCourses(userId) {
            //return;
        }
    }

})();