(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('userAPI', userAPI);

    /* @ngInject */
    function userAPI() {
        var userAPI = {
            getCurrentCourses: getCurrentCourses,
            getFeedbacksOn: getFeedbacksOn,
            getPastCourses: getPastCourses,
            getWaitingCourses: getWaitingCourses
        };
        return userAPI;

        function getCurrentCourses(userId) {
            //return;
        }

        function getFeedbacksOn(userId) {
            //return;
        }

        function getPastCourses(userId) {
            //return;
        }

        function getWaitingCourses(userId) {
            //return;
        }
    }

})();