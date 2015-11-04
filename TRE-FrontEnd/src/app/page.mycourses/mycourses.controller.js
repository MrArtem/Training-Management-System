(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('MyCoursesController', MyCoursesController);

    /** @ngInject */
    function MyCoursesController(courseAPI) {
        var vm = this;

        vm.isContentLoaded = false;
        vm.userCourses = [];
        vm.getMyCourses = getMyCourses;

        vm.filter = {isCoach: "", search: ""};

        vm.getMyCourses();

        function getMyCourses() {
            courseAPI.getCourseList(true, []).then(function(data) {
                vm.userCourses = angular.copy(data);
                vm.isContentLoaded = true;
            })
        }

    }
})();