(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CurrentCoursesController', CurrentCoursesController);

    /** @ngInject */
    function CurrentCoursesController($scope, $stateParams, userAPI) {
        var vm = this;
        vm.isContentLoaded = false;
        vm.getCurrentCourses = getCurrentCourses;

        vm.getCurrentCourses();

        function getCurrentCourses() {
            vm.isContentLoaded = false;
            userAPI.getCurrentCourses($stateParams.userId).then(function(data) {
                $scope.$parent.profileInfo.currentCourses = angular.copy(data);
                vm.isContentLoaded = true;
                console.log("Received courses: ");
                console.log($scope.$parent.profileInfo.currentCourses);
            });
        }

    }
})();