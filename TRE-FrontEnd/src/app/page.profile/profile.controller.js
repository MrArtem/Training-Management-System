(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('ProfileController', ProfileController);

    /** @ngInject */
    function ProfileController($stateParams, userAPI) {
        var vm = this;
        var userId = $stateParams.userId;
        vm.profileInfo = {};

        vm.currentCourses = [];
        vm.feedbacks = [];
        vm.pastCourses = [];
        vm.waitingCourses = [];

        vm.getCurrentCourses = getCurrentCourses;
        vm.getFeedbacks = getFeedbacks;
        vm.getPastCourses = getPastCourses;
        vm.getProfileInfo = getProfileInfo;
        vm.getWaitingCourses = getWaitingCourses;



        function getCurrentCourses() {
            userAPI.getCurrentCourses(userId).then(function(data) {
                vm.currentCourses = angular.copy(data);
            });
        }

        function getFeedbacks() {
            userAPI.getFeedbacksOn(userId).then(function(feedbacks) {
                vm.feedbacks = angular.copy(feedbacks);
            });
        }

        function getPastCourses() {
            userAPI.getPastCourses(userId).then(function(data) {
                vm.pastCourses = angular.copy(data);
            });
        }

        function getProfileInfo() {
            userAPI.getProfileInfo(userId).then(function(user) {
                vm.profileInfo = angular.copy(user);
            });
        }

        function getWaitingCourses() {
            userAPI.getWaitingCourses(userId).then(function(data) {
                vm.waitingCourses = angular.copy(data);
            });
        }
    }
})();