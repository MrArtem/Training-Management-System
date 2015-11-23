(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('WaitingCoursesController', WaitingCoursesController);

    /** @ngInject */
    function WaitingCoursesController($scope, $stateParams, userAPI) {
        var vm = this;
        vm.getWaitingCourses = getWaitingCourses;
        vm.isContentLoaded = false;

        vm.getWaitingCourses();

        function getWaitingCourses() {
            vm.isContentLoaded = false;
            userAPI.getWaitingCourses($stateParams.userId).then(function(data) {
                $scope.$parent.profileInfo.waitingCourses = angular.copy(data);
                vm.isContentLoaded = true;
                console.log("Received courses: ");
                console.log($scope.$parent.profileInfo.waitingCourses);
            });
        }
    }
})();