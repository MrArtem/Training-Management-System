(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('ProfileController', ProfileController);

    /** @ngInject */
    function ProfileController($scope, $location, $stateParams, userAPI) {
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
        vm.isActive = isActive;

        vm.getProfileInfo();

        function getProfileInfo() {
            console.log(userId);
            userAPI.getProfileInfo(userId).then(function(user) {
                vm.profileInfo = angular.copy(user);
                console.log('User', user);
                vm.isContentLoaded = true;
            });
        }

        function isActive(state) {
            return $location.absUrl().search(state) === -1 ? false : true
        }
    }
})();