(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CurrentCoursesController', CurrentCoursesController);

    /** @ngInject */
    function CurrentCoursesController($scope, $stateParams, userAPI) {
        var vm = this;
        vm.getCurrentCourses = getCurrentCourses;

        vm.getCurrentCourses();

        function getCurrentCourses() {
            userAPI.getCurrentCourses($stateParams.userId).then(function(data) {
                debugger;
                $scope.$parent.profileInfo.currentCourses = angular.copy(data);
                console.log("Received courses: ");
                console.log($scope.$parent.profileInfo.currentCourses);
            });
        }

    }
})();