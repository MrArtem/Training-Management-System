(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('ProfileController', ProfileController);

    /** @ngInject */
    function ProfileController($scope, $stateParams, userAPI) {
        var vm = this;
        var userId = $stateParams.userId;
        console.log(userId);
        vm.profileInfo = {};
        vm.isContentLoaded = false;

        vm.profileInfo.currentCourses = [];
        vm.profileInfo.feedbacks = [];
        vm.profileInfo.pastCourses = [];
        vm.profileInfo.waitingCourses = [];

        $scope.profileInfo = vm.profileInfo;

        vm.getProfileInfo = getProfileInfo;

        vm.getProfileInfo();

        function getProfileInfo() {
            userAPI.getProfileInfo(userId).then(function(user) {
                vm.profileInfo = angular.copy(user);
                vm.isContentLoaded = true;
            });
        }

        function getWaitingCourses() {
            userAPI.getWaitingCourses(userId).then(function(data) {
                vm.waitingCourses = angular.copy(data);
            });
        }
    }
})();