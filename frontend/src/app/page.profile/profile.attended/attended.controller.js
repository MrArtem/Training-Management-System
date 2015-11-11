(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('AttendedCoursesController', AttendedCoursesController);

    /** @ngInject */
    function AttendedCoursesController($scope, $stateParams, userAPI) {
        var vm = this;
        vm.isContentLoaded = false;
        vm.getPastCourses = getPastCourses;

        vm.getPastCourses();

        function getPastCourses() {
            userAPI.getPastCourses($stateParams.userId).then(function(data) {
                $scope.$parent.profileInfo.pastCourses = angular.copy(data);
                vm.isContentLoaded = true;
                console.log("Received courses: ");
                console.log($scope.$parent.profileInfo.pastCourses);
            });
        }
    }
})();