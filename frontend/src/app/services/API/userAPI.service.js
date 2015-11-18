(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('userAPI', userAPI);

    /* @ngInject */
    function userAPI($http, urlProvider) {
        var userAPI = {
            addExCoach: addExCoach,
            addFeedbackOnUser: addFeedbackOnUser,
            changePassword: changePassword,
            getCurrentCourses: getCurrentCourses,
            getFeedbacksOn: getFeedbacksOn,
            getFeedback: getFeedback,
            getPastCourses: getPastCourses,
            getProfileInfo: getProfileInfo,
            getWaitingCourses: getWaitingCourses
        };
        return userAPI;

        function addExCoach(coachInfo) {
            return $http.post(urlProvider.addExCoach(), coachInfo).then(function(result) {
                return result.data;
            });
        }

        function addFeedbackOnUser(courseId, userId, fbInfo) {
            fbInfo.userID = userId;
            fbInfo.trainingID = courseId;
            return $http.post(urlProvider.addFeedback(), fbInfo).then(function(result) {
                return result.data;
            });
        }

        function changePassword(userId, newPassword) {
            var psInfo = {
                id: userId,
                password: newPassword
            };
            return $http.post(urlProvider.changePassword(), psInfo).then(function(result) {
                return result.data;
            });
        }

        function getCurrentCourses(userId) {
            return $http.get(urlProvider.getCurrentCoursesForUser(userId)).then(function(result) {
                return result.data;
            });
        }

        function getFeedbacksOn(userId) {
            return $http.get(urlProvider.getFeedbacksOnUser(userId)).then(function(result) {
                return result.data;
            });
        }

        function getFeedback(fbId) {
            return $http.get(urlProvider.getFeedback(fbId)).then(function(result) {
                return result.data;
            });
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
            return $http.get(urlProvider.getWaitingCoursesForUser(userId)).then(function(result) {
                return result.data;
            });
        }
    }

})();