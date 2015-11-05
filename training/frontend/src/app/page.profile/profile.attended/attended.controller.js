(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('AttendedCoursesController', AttendedCoursesController);

    /** @ngInject */
    function AttendedCoursesController($scope, $stateParams, userAPI) {
        var vm = this;
        vm.getPastCourses = getPastCourses;

        vm.getPastCourses();

        function getPastCourses() {
            userAPI.getPastCourses($stateParams.userId).then(function(data) {
                $scope.$parent.profileInfo.pastCourses = angular.copy(data);
                console.log("Received courses: ");
                console.log($scope.$parent.profileInfo.pastCourses);
            });
        }

    }
})();